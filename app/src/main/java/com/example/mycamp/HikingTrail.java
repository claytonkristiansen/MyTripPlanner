package com.example.mycamp;

public class HikingTrail extends Location
{
    private boolean m_pass;
    private float m_cost;
    HikingTrail(String name, String address, boolean needDocs, boolean pass, float cost)
    {
        super(name, address, needDocs);
        m_pass = pass;
        m_cost = cost;
        m_type = LOCATION_TYPE.HIKING_TRAIL;
    }

    boolean GetPass()
    {
        return m_pass;
    }

    float GetCost()
    {
        return m_cost;
    }
}
