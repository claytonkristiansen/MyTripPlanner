package com.example.mycamp;

public class BackpackingTrail extends Location
{
    private boolean m_fee;
    private float m_cost;
    BackpackingTrail(String name, int imageId, String address, boolean needDocs, LOCATION_TYPE type, boolean fee, float cost, int myIndex)
    {
        super(name, imageId, address, needDocs, myIndex);
        m_fee = fee;
        m_cost = cost;
        m_type = LOCATION_TYPE.BACKPACKING_TRAIL;
    }
}
