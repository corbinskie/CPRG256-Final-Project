using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpriteBillboarding : MonoBehaviour
{

    void LateUpdate()
    {
        transform.forward = Camera.main.transform.forward;
    }
}
