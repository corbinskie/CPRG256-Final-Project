using UnityEngine;
using UnityEngine.AI;
using UnityEngine.UI;

public class UnitCurrentStats : MonoBehaviour
{
    public UnitBaseStats unitBaseStats;
    public NavMeshAgent agent;
    public HealthBar healthBar;

    public int healthPoints;
    public int manaPoints;

    public int armour;

    public int attackDamage;
    public float attackSpeed;
    public float attackRange;

    public float movementSpeed;

    // Start is called before the first frame update
    void Start()
    {
        healthPoints = unitBaseStats.healthPoints;
        healthBar.SetMaxHealth(healthPoints);

        manaPoints = unitBaseStats.manaPoints;


        attackDamage = unitBaseStats.attackDamage;
        attackSpeed = unitBaseStats.attackSpeed;

        attackRange = unitBaseStats.attackRange;

        armour = unitBaseStats.armour;

        movementSpeed = unitBaseStats.movementSpeed;
    }

    // Update is called once per frame
    void Update()
    {
        agent.speed = movementSpeed;
    }
}
