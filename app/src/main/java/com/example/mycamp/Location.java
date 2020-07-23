package com.example.mycamp;

enum LOCATION_TYPE {DEFAULT, CAMPGROUND, BACKPACKING_TRAIL, HIKING_TRAIL};

public class   Location
{
    private String m_name;
    private String m_address;
    private boolean m_needDocs;
    private int m_imageId;
    public int m_myIndex;
    protected LOCATION_TYPE m_type = LOCATION_TYPE.DEFAULT;
    Location(String name, int imageId, String address, boolean needDocs, int myIndex)
    {
        m_name = name;
        m_address = address;
        m_needDocs = needDocs;
        m_imageId = imageId;
        m_myIndex = myIndex;
    }

    String GetName()
    {
        return m_name;
    }

    String GetAddress()
    {
        return m_address;
    }

    int GetImageId() { return m_imageId; }

    boolean NeedDocs()
    {
        return m_needDocs;
    }

    void SetImageId(int id) { m_imageId = id; }
}
