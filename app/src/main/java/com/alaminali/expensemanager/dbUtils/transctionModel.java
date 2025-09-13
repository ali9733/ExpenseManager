package com.alaminali.expensemanager.dbUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
/* ENTITY MODEL OBJECT IS USED IN TRANSCTION ADAPTER TO SHOW ITEM IN RECYCLER VIEW ON THE transctionFrgment  */
@Entity(tableName = "transctionTable")

public class transctionModel
{


    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "category_image")
    public int categoryImage;
    @ColumnInfo(name = "transction_amount")
    public double amount;

    @ColumnInfo(name = "category_name")
    public String categoryName;

    @ColumnInfo(name = "account_name")
    public String accountName;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "fullDate")
    public String full_date;

    @ColumnInfo(name = "shortDate")
    public String short_date;
     @ColumnInfo(name = "Notes")
    public String notes;

    public transctionModel()  // empty constructor for creating an empty object and after then set data to it
    {

    }

    public transctionModel(int categoryImage, int uid, double amount, String categoryName, String accountName, String type, String short_date,String full_date,String notes)
    {
        this.categoryImage = categoryImage;
        this.uid = uid;
        this.amount = amount;                   // non-empty constructor for creating an non-empty object and set data at a time
        this.categoryName = categoryName;
        this.accountName = accountName;
        this.type = type;
        this.short_date =short_date;
        this.full_date=full_date;
        this.notes=notes;
    }

    // now create setter and getter to set and get data from the object
    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }


    public String getFull_date()
    {
        return full_date;
    }

    public void setFull_date(String full_date)
    {
        this.full_date = full_date;
    }

    public String getShort_date()
    {
        return short_date;
    }

    public void setShort_date(String short_date)
    {
        this.short_date = short_date;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }


    public int getCategoryImage()
    {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage)
    {
        this.categoryImage = categoryImage;
    }


    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }


}
