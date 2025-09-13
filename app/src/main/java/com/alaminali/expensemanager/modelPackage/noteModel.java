package com.alaminali.expensemanager.modelPackage;

public class noteModel
{
    private int uid;
    private String notes;

    public noteModel(int uid, String notes)
    {
        this.uid = uid;
        this.notes = notes;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
