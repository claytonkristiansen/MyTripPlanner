package com.example.mycamp;

enum LOCATION_TYPE {DEFAULT, CAMPGROUND, BACKPACKING_TRAIL, HIKING_TRAIL};

public class Location
{
    private String m_name;
    private String m_address;
    private boolean m_needDocs;
    protected LOCATION_TYPE m_type = LOCATION_TYPE.DEFAULT;
    Location(String name, String address, boolean needDocs)
    {
        m_name = name;
        m_address = address;
        m_needDocs = needDocs;
    }

    String GetName()
    {
        return m_name;
    }

    String GetAddress()
    {
        return m_address;
    }

    boolean NeedDocs()
    {
        return m_needDocs;
    }
}
