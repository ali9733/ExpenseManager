package com.alaminali.expensemanager.modelPackage;

public class accountModel
{
    /* MODEL OBJECT IS USED IN ACCOUNT ADAPTER TO SHOW ITEM IN RECYCLER VIEW ON THE DIALOG FRAGMENT'S ACCOUNT EDITTEXT  */

    private String accountName;
    private int accountImage,txtColor;

    public accountModel() // empty constructor for creating an empty object and after then set data to it
    {

    }

    public accountModel(String accountName, int accountImage,int txtColor) // non-empty constructor for creating an non-empty object and set data at a time
    {
        this.accountName = accountName;
        this.accountImage = accountImage;
        this.txtColor=txtColor;
    }

    // now create setter and getter to set and get data from the object


    public int getTxtColor()
    {
        return txtColor;
    }

    public void setTxtColor(int txtColor)
    {
        this.txtColor = txtColor;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public int getAccountImage()
    {
        return accountImage;
    }

    public void setAccountImage(int accountImage)
    {
        this.accountImage = accountImage;
    }
}
