package com.alaminali.expensemanager.dbUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "eBanking")
public class onlineBanking
{

   @PrimaryKey(autoGenerate = true)
   public int uid;

   @ColumnInfo(name = "bankingName")
    public String bankingName;


    @ColumnInfo(name = "bankingImage")
    public int bankingImage;

    @ColumnInfo(name = "bankingType")
    public String bankingType;


    @ColumnInfo(name = "cardNumber")
    public String cardNumber;

    @ColumnInfo(name = "cardCvvNumber")
    public String cardCvv;


    @ColumnInfo(name = "expiryDate")
    public String cardExpiry;


    @ColumnInfo(name = "userPhone")
    public String phoneNumber;

    @ColumnInfo(name = "userPassword")
    public String userPassword;

    @ColumnInfo(name = "bankingAmount")
    public double bankAmount;

    public onlineBanking()
    {

    }

    public onlineBanking(int uid, String bankingName, int bankingImage, String bankingType, String cardNumber, String cardCvv, String cardExpiry, String phoneNumber, String userPassword, double bankAmount)
    {
        this.uid = uid;
        this.bankingName = bankingName;
        this.bankingImage = bankingImage;
        this.bankingType = bankingType;
        this.cardNumber = cardNumber;
        this.cardCvv = cardCvv;
        this.cardExpiry = cardExpiry;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
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

    public String getBankingName()
    {
        return bankingName;
    }

    public void setBankingName(String bankingName)
    {
        this.bankingName = bankingName;
    }

    public int getBankingImage()
    {
        return bankingImage;
    }

    public void setBankingImage(int bankingImage)
    {
        this.bankingImage = bankingImage;
    }

    public String getBankingType()
    {
        return bankingType;
    }

    public void setBankingType(String bankingType)
    {
        this.bankingType = bankingType;
    }

    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    public String getCardCvv()
    {
        return cardCvv;
    }

    public void setCardCvv(String cardCvv)
    {
        this.cardCvv = cardCvv;
    }

    public String getCardExpiry()
    {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry)
    {
        this.cardExpiry = cardExpiry;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
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
