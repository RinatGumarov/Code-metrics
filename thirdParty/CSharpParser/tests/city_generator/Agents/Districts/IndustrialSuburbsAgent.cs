using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class IndustrialSuburbsAgent : AbstractAgent
{
    public float radiusMultiplier = 0.9f;
    public float outOfRadiusProb = 0.5f;
    private static List<District> residentialDistricts = null;

    private void createResidentialDistrictsList()
    {
        residentialDistricts = new List<District>();
        foreach (District district in generator.districtsMap)
        {
            int count = 0;
            Vector2 position = new Vector2();
            foreach (DistrictCell cell in district.cells)
            {
                count++;
                position.x += cell.x;
                position.y += cell.y;
            }
            position /= count;
            float distanceToCenter = Vector2.Distance(position, generator.cityCenter);
            if (distanceToCenter > generator.cityRadius * radiusMultiplier)
                residentialDistricts.Add(district);
        }
    }
    public override void agentAction()
    {
        if (residentialDistricts == null)
            createResidentialDistrictsList();
        if (Random.value < outOfRadiusProb)
        {
            if (residentialDistricts.Count > 0)
                residentialDistricts[(int)Random.Range(0.0f, residentialDistricts.Count - 1)].type = DistrictType.INDUSTRIAL;
        }
    }
}
