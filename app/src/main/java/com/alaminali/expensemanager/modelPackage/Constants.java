package com.alaminali.expensemanager.modelPackage;

import android.provider.ContactsContract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Constants
{
    public static String INCOME="income";
    public static String EXPENSE="expense";

    /* HERE I CREATE A SIMPLE DATE FORMAT TO SHOW  DATE FORMATTED WAY  IN DIALOG FRAGMENT'S "SELECT DATE" EDIT TEXT AND FOR USES IN  OTHER PART OF THE CODE
     BEACUSE IT WILL BE USED IN MANY PART OF THE CODE  */
    public static String getDateFormat(Calendar calendar)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/YY");
        return dateFormat.format(calendar.getTime());
    }

    /* HERE I CREATE A LIST OBJECT FOR CATEGORY ADAPTER TO SHOW ITEM IN DIALOG FRAGMENT'S "SELECT CATEGORY" EDIT TEXT */
    public static ArrayList<categoryModel> getAllCategoryDatas()
    {
        ArrayList<categoryModel> categories=new ArrayList<>();
        categories.add(new categoryModel());
        categories.add(new categoryModel());
        categories.add(new categoryModel());
        categories.add(new categoryModel());
        categories.add(new categoryModel());
        categories.add(new categoryModel());


        return categories;
    }

    /* HERE I CREATE A LIST OBJECT FOR ACCOUNT ADAPTER TO SHOW ITEM IN DIALOG FRAGMENT'S "SELECT ACCOUNT" EDIT TEXT */
    public static ArrayList<accountModel> getAllAccountDatas()
    {
        ArrayList<accountModel> accounts=new ArrayList<>();
        accounts.add(new accountModel());
        accounts.add(new accountModel());
        accounts.add(new accountModel());
        accounts.add(new accountModel());
        accounts.add(new accountModel());
        accounts.add(new accountModel());


        return accounts;
    }


}
