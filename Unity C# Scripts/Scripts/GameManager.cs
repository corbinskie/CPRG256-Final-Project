using UnityEngine;
using TMPro;

public class GameManager : MonoBehaviour
{

    public GameObject damageTextPrefab;

    public void UnitTakeDamage(UnitController giver, UnitController receiver)
    {
        var damage = giver.unitCurrentStats.attackDamage;
        receiver.ReceiveDamage(giver, damage);

        GameObject DamageTextInstance = Instantiate(damageTextPrefab, receiver.transform);

        if ((damage - receiver.unitCurrentStats.armour) <= 0)
        {
            DamageTextInstance.GetComponent<TextMeshPro>().SetText("-1");
        }
        else
        {
            DamageTextInstance.GetComponent<TextMeshPro>().SetText("-" + (damage - receiver.unitCurrentStats.armour).ToString());
        }
    }
}
