package com.example.mycamp;

public class Trip
{
    @com.google.gson.annotations.SerializedName("id")
    private String m_tripId;

    @com.google.gson.annotations.SerializedName("name")
    private String m_name;

    @com.google.gson.annotations.SerializedName("startDate")
    private String m_startDate;

    @com.google.gson.annotations.SerializedName("endDate")
    private String m_endDate;

    @com.google.gson.annotations.SerializedName("location")
    private String m_location;

    @com.google.gson.annotations.SerializedName("complete")
    private boolean m_complete;

    @com.google.gson.annotations.SerializedName("GuestListId")
    private boolean m_guestListId;

    @com.google.gson.annotations.SerializedName("ownerId")
    private boolean m_ownerId;

    @com.google.gson.annotations.SerializedName("campList")
    private boolean m_campList;



    public Trip(String tripId, String name, String startDate, String endDate, String location)
    {
        m_tripId = tripId;
        m_name = name;
        m_complete = false;
        m_startDate = startDate;
        m_endDate = endDate;
        m_location = location;
    }

    public Trip(String name, String startDate, String endDate, String location)
    {
        m_name = name;
        m_complete = false;
        m_startDate = startDate;
        m_endDate = endDate;
        m_location = location;
    }

    public Trip() {}

    public String getName() {
        return m_name;
    }

    /**
     * Sets the item text
     *
     * @param name
     *            text to set
     */
    public final void setText(String name) {
        m_name = name;
    }

    /**
     * Returns the item id
     */
    public String getId() {
        return m_tripId;
    }

    /**
     * Sets the item id
     *
     * @param id
     *            id to set
     */
    public final void setId(String id) {
        m_tripId = id;
    }

    /**
     * Indicates if the item is marked as completed
     */
    public boolean isComplete() {
        return m_complete;
    }

    /**
     * Marks the item as completed or incompleted
     */
    public void setComplete(boolean complete) {
        m_complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        return  ((Trip) o).m_tripId == m_tripId;
    }

}
