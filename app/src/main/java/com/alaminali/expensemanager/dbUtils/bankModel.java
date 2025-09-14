package com.alaminali.expensemanager.dbUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bankTable")
public class bankModel
{
   @PrimaryKey(autoGenerate = true)
   public int uid;

   @ColumnInfo(name = "bank_name")
   public String bankName;

    @ColumnInfo(name = "bank_image")
    public int bankImage;

    @ColumnInfo(name = "branch_name")
    public String branchName;

    @ColumnInfo(name = "bank_ifsc")
    public String ifscCode;

    @ColumnInfo(name = "bank_number")
    public String bankNumber;


    @ColumnInfo(name = "bank_amount")
    public double bankAmount;


    public bankModel()
    {

    }

    public bankModel(int uid, String bankName, int bankImage, String branchName, String ifscCode, String bankNumber, double bankAmount)
    {
        this.uid = uid;
        this.bankName = bankName;
        this.bankImage = bankImage;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.bankNumber = bankNumber;
        this.bankAmount = bankAmount;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public int getBankImage()
    {
        return bankImage;
    }

    public void setBankImage(int bankImage)
    {
        this.bankImage = bankImage;
    }

    public String getBranchName()
    {
        return branchName;
    }

    public void setBranchName(String branchName)
    {
        this.branchName = branchName;
    }

    public String getIfscCode()
    {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode)
    {
        this.ifscCode = ifscCode;
    }

    public String getBankNumber()
    {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber)
    {
        this.bankNumber = bankNumber;
    }

    public double getBankAmount()
    {
        return bankAmount;
    }

    public void setBankAmount(double bankAmount)
    {
        this.bankAmount = bankAmount;
    }
}
