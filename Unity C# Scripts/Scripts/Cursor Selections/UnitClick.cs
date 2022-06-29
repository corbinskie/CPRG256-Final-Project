using UnityEngine;

public class UnitClick : MonoBehaviour
{
    private Camera cam;

    public LayerMask clickable;
    public LayerMask ground;

    // Start is called before the first frame update
    void Start()
    {
        cam = Camera.main;
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {
            RaycastHit hit;
            Ray ray = cam.ScreenPointToRay(Input.mousePosition);

            // If we hit a clickable object
            if (Physics.Raycast(ray, out hit, Mathf.Infinity, clickable))
            {
                // If we used shift-click
                if (Input.GetKey(KeyCode.LeftShift))
                {
                    UnitSelections.Instance.ShiftClickSelect(hit.collider.gameObject);
                }
                // If we used a normal click
                else
                {
                    UnitSelections.Instance.ClickSelect(hit.collider.gameObject);
                }
            }
            // If we don't hit a clickable object
            else
            {
                if (!Input.GetKey(KeyCode.LeftShift))
                {
                    UnitSelections.Instance.DeselectAll();
                }

            }
        }
    }
}
