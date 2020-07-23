package com.example.mycamp;

public class HikingTrail extends Location
{
    private boolean m_pass;
    private float m_cost;
    public int number = 0;
    HikingTrail(String name, int imageId, String address, boolean needDocs, boolean pass, float cost, int myIndex)
    {
        super(name, imageId, address, needDocs, myIndex);
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
