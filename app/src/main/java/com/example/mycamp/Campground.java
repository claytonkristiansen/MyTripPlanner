package com.example.mycamp;

public class Campground extends Location
{
    private boolean m_fee;
    private float m_cost;
    Campground(String name, String address, boolean needDocs, LOCATION_TYPE type, boolean fee, float cost)
    {
        super(name, address, needDocs);
        m_fee = fee;
        m_cost = cost;
        m_type = LOCATION_TYPE.CAMPGROUND;
    }
}
