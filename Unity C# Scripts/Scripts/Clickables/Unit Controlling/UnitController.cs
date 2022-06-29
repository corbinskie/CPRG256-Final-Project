using System.Collections;
using UnityEngine;
using UnityEngine.AI;
using UnityEngine.UI;

public class UnitController : MonoBehaviour
{
    Camera cam;

    public Animator animator;
    public GameManager gameManager;
    public NavMeshAgent agent;
    public SpriteRenderer spriteRenderer;
    public UnitController unitController;
    public UnitCurrentStats unitCurrentStats;
    public UnitIsSelected unitIsSelected;
    public UnitSelections unitSelections;
    public HealthBar healthBar;

    public float autoAttackTimer; // The automatic time counter to check if the unit is ready to attack
    public Vector3 currentPosition; // The unit's current position
    public Transform currentTarget; // The unit's current target
    public Vector3 destination; // Where the unit is headed
    public float currentMovementSpeed; // Used for saving the unit's movement speed so it can move again after attacking

    // Start is called before the first frame update
    void Start()
    {
        cam = Camera.main;

        autoAttackTimer = unitCurrentStats.attackSpeed;
        currentMovementSpeed = unitCurrentStats.movementSpeed;

        StartCoroutine("ProximityTarget");
    }

    // Update is called once per unframe
    void Update()
    {
        autoAttackTimer += 0.1f;
        currentPosition = gameObject.transform.position; // Used for the AnimatorChecker() to check which way unit is traveling

        AnimationChecker();
        OnRightClick();
        ChaseTarget();
    }

    // Function for checking which animations should be playing, primarily used for movement
    // Called in Update()
    public void AnimationChecker()
    {
        // This if-else block is for checking which way the unit sprite needs to be facing
        if (agent.destination.x > currentPosition.x) // If the unit's destination is higher on the X-axis than current position
        {
            spriteRenderer.flipX = false;
        }
        else if (agent.destination.x < currentPosition.x) // If the unit's destination is loswer on the X-axis than current position
        {
            spriteRenderer.flipX = true;
        }

        // This if-else block is for checking if the unit sprite is walking or idle
        if (agent.velocity.sqrMagnitude > 0.01f) // If the unit is moving
        {
            animator.SetBool("isMoving", true);
        }
        else if (agent.velocity.sqrMagnitude < 0.01f) // If the unit isn't moving
        {
            animator.SetBool("isMoving", false);
        }

        // This if-else block is for checking if the unit sprite is in the attack animation so we can diable unit movement during attacks 
        if (animator.GetCurrentAnimatorStateInfo(0).IsTag("Attack")) // If the current animation is tagged with "Attack"
        {
            unitCurrentStats.movementSpeed = 0f;
            animator.speed = (200f / unitCurrentStats.attackSpeed);
        }
        else if (animator.GetCurrentAnimatorStateInfo(0).IsTag("Attack") == false) // If the current animation isn't tagged with "Attack" (duh)
        {
            unitCurrentStats.movementSpeed = currentMovementSpeed;
            animator.speed = 1f;
        }
    }

    // Function for chasing down enemy units
    // Called in Update()
    public void ChaseTarget()
    {
        if (currentTarget != null) // If unit has a target, continue to follow and attack
        {
            agent.destination = currentTarget.transform.position;

            var distanceFromTarget = (transform.position - currentTarget.transform.position).sqrMagnitude; // The distance in between the unit and it's target. Used in calculating if the unit is in range to attack.

            // This if-else block is for checking if the unit is within attack range of it's target, and if the target is actually alive
            if (distanceFromTarget <= unitCurrentStats.attackRange && currentTarget.GetComponent<UnitCurrentStats>().healthPoints > 0)
            {
                unitController.InflictDamage();
            }
            else if (currentTarget.GetComponent<UnitCurrentStats>().healthPoints <= 0) // This else-if is probably not needed but it's just an extra check incase the target is dead
            {
                currentTarget = null;
            }
        }
    }

    // Function for handling when the unit inflicts damage
    // Called in ChaseTarget()
    public void InflictDamage()
    {
        // This if-else block is for checking if the unit is ready to attack based on it's attack speed, and if the enemy is alive
        if (autoAttackTimer >= unitCurrentStats.attackSpeed && currentTarget.GetComponent<UnitCurrentStats>().healthPoints > 0) // If the unit is ready to attack and the enemy is alive
        {
                animator.SetTrigger("Attack"); // Triggers the attack animation

                gameManager.UnitTakeDamage(unitController, currentTarget.GetComponent<UnitController>()); // Sends the attack through the game manager
                autoAttackTimer = 0f; // Refreshes the auto attack timer
        }
    }

    // Function for handling when the unit receives damage
    // Called in GameManager.UnitTakeDamage
    public void ReceiveDamage(UnitController enemy, int damage)
    {
        if((damage - unitCurrentStats.armour) <= 0)
        {
            unitCurrentStats.healthPoints = unitCurrentStats.healthPoints -= 1;
            healthBar.SetHealth(unitCurrentStats.healthPoints);
        }
        else
        {
            unitCurrentStats.healthPoints = unitCurrentStats.healthPoints - (damage - unitCurrentStats.armour); // Unit's health = health - (enemy damage - unit's defence)
            healthBar.SetHealth(unitCurrentStats.healthPoints);
        }
        Debug.Log(unitCurrentStats.healthPoints); // ***REMOVE LATER***

        // If this unit has no target and is being attacked, retaliate
        if (currentTarget == null && unitCurrentStats.healthPoints > 0 && agent.velocity.sqrMagnitude == 0f) // If this was a traditional RTS game, also check to make sure this unit is not moving before retaliating
        {
            currentTarget = enemy.transform;
        }

        // If this unit's health reaches zero
        if (unitCurrentStats.healthPoints <= 0f)
        {
            animator.SetBool("isDead", true);
            currentTarget = null;
            this.transform.GetChild(1).GetComponent<SpriteRenderer>().sortingOrder = 0; // Moves sprite corpse behind alive units

            // These agent settings are to ensure than when a unit dies, it stops moving, and its body is no longer a physical object in the way of other units
            agent.isStopped = true;
            agent.radius = 0; 
            agent.height = 0;
            agent.avoidancePriority = 100;
            agent.obstacleAvoidanceType = ObstacleAvoidanceType.NoObstacleAvoidance;

            this.GetComponent<UnitIsSelected>().enabled = false; // Disables the parts of the unit we don't want active after it dies
            this.GetComponent<CapsuleCollider>().enabled = false; // Disables the parts of the unit we don't want active after it dies
            this.transform.GetChild(0).gameObject.SetActive(false); // Disables the selection circle of the unit after it dies
            this.transform.GetChild(2).gameObject.SetActive(false); // Disables the health bar of the unit after it dies
            UnitSelections.Instance.unitList.Remove(this.gameObject); // Removes the unit from the master list of units

            StopCoroutine("ProximityTarget"); // This must be stopped when a unit dies or the corpse starts attacking nearby targets
            StartCoroutine("FadeAndDestroyObject"); // This fades the unit's sprite then deletes the whole unit object
        }
    }

    // Coroutine for fading the corspe of the unit after it dies, then deletes it
    // Called in ReceiveDamage()
    IEnumerator FadeAndDestroyObject()
    {
        float alpha = spriteRenderer.color.a;
        Color tmp = spriteRenderer.color;

        while (this.spriteRenderer.color.a > 0) // While the sprite's alpha is greater than 0
        {
            alpha -= 0.01f;
            tmp.a = alpha;
            this.spriteRenderer.color = tmp;

            yield return new WaitForSeconds(0.10f); // Update interval
        }

        Destroy(this.gameObject);
    }

    // Function for targetting nearby enemies automatically
    // Called in Start()
    IEnumerator ProximityTarget()
    {
        while (true) // Infinite loop that will loop every 1 second due to WaitForSeconds at bottom
        {
            Collider[] collidersInProximity = Physics.OverlapSphere(currentPosition, 3f); // Collects all of the colliders hit in a sphere around unit
            float distance = Mathf.Infinity;
            GameObject closestTarget;

            foreach (var colliderHit in collidersInProximity)
            {
                // If the collider hit is a CapsuleCollider, the collider hit has more than 0hp, the collider hit isn't the unit's own collider, and the unit currently has no target.
                if (colliderHit is CapsuleCollider && colliderHit.GetComponent<UnitCurrentStats>().healthPoints > 0f && colliderHit != gameObject.GetComponent<CapsuleCollider>() && currentTarget == null && agent.velocity.sqrMagnitude == 0f) // Check velocity of unit here in a regular RTS type game so that they don't auto target when given a movement order
                {
                    Vector3 difference = colliderHit.transform.position - currentPosition;
                    float distanceFromHit = difference.sqrMagnitude;

                    if (distanceFromHit < distance)
                    {
                        closestTarget = colliderHit.gameObject;
                        currentTarget = closestTarget.transform;
                    }
                }
            }
            yield return new WaitForSeconds(1f);
        }
    }

    // Function for when pressing right click while unit(s) selected
    // Called in Update()
    public void OnRightClick()
    {
        if (Input.GetMouseButtonDown(1)) // If right mouse button is clicked
        {
            RaycastHit hit;
            Ray ray = cam.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit)) // If the raycast hits something
            {
                if (hit.transform.CompareTag("Terrain") && unitIsSelected.enabled) // If the raycast hit Terrain
                {
                    destination = hit.point;
                    agent.SetDestination(destination);
                    currentTarget = null;
                }
                else if (hit.transform.CompareTag("Clickable") && unitIsSelected.enabled && hit.transform.gameObject != this.gameObject) // If the raycast hit a Clickable (a unit or building)
                {
                    destination = hit.point;
                    agent.SetDestination(destination);
                    currentTarget = hit.transform;
                }

            }
        }
    }
}
