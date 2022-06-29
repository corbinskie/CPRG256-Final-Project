using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(menuName = "Units/UnitStats")]
public class UnitBaseStats : ScriptableObject
{
    public int healthPoints;
    public int manaPoints;

    public int armour;

    public int attackDamage;
    public float attackSpeed;
    public float attackRange;

    public float movementSpeed;
}
