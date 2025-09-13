package com.alaminali.expensemanager.modelPackage;

import android.provider.ContactsContract;

import com.alaminali.expensemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Constants
{
    public static String INCOME="income";
    public static String EXPENSE="expense";

    /* HERE I CREATE A SIMPLE DATE FORMAT TO SHOW  DATE FORMATTED WAY  IN DIALOG FRAGMENT'S "SELECT DATE" EDIT TEXT AND FOR USES IN  OTHER PART OF THE CODE
     BEACUSE IT WILL BE USED IN MANY PART OF THE CODE  */
    public static String getFullDateFormat(Calendar calendar)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/YY", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static String getShortDateFormat(Calendar calendar)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM/YY", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    /* HERE I CREATE A LIST OBJECT FOR CATEGORY ADAPTER TO SHOW ITEM IN DIALOG FRAGMENT'S "SELECT CATEGORY" EDIT TEXT */
    public static ArrayList<categoryModel> getAllCategoryDatas()
    {
        ArrayList<categoryModel> categories=new ArrayList<>();
        categories.add(new categoryModel("Investment", R.drawable.graph,R.color.text_0ne));
        categories.add(new categoryModel("Business",R.drawable.cooperation,R.color.text_five));
        categories.add(new categoryModel("Rent",R.drawable.key,R.color.red));
        categories.add(new categoryModel("Loan",R.drawable.personal,R.color.purple_500));
        categories.add(new categoryModel("Debt",R.drawable.money_bag,R.color.text_three));
        categories.add(new categoryModel("Salary",R.drawable.wages,R.color.text_five));


        return categories;
    }

    /* HERE I CREATE A LIST OBJECT FOR ACCOUNT ADAPTER TO SHOW ITEM IN DIALOG FRAGMENT'S "SELECT ACCOUNT" EDIT TEXT */
    public static ArrayList<accountModel> getAllAccountDatas()
    {
        ArrayList<accountModel> accounts=new ArrayList<>();
        accounts.add(new accountModel("Phonepay",R.drawable.phonepay,R.color.text_five));
        accounts.add(new accountModel("Paytm",R.drawable.paytm,R.color.purple_500));
        accounts.add(new accountModel("Cash",R.drawable.cash,R.color.text_three));
        accounts.add(new accountModel("Bank",R.drawable.bank,R.color.text_0ne));
        accounts.add(new accountModel("Paypal",R.drawable.paypal,R.color.text_four));
        accounts.add(new accountModel("Debit",R.drawable.credit_card,R.color.text_six));


        return accounts;
    }


}
