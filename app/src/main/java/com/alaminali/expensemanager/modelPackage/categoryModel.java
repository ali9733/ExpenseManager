package com.alaminali.expensemanager.modelPackage;

public class categoryModel
{
    /* MODEL OBJECT IS USED IN CATEGORY ADAPTER TO SHOW ITEM IN RECYCLER VIEW ON THE DIALOG FRAGMENT'S CATEGORY EDITTEXT  */

    private String categoryName;
    private int categoryImage;


    public categoryModel() // empty constructor for creating an empty object and after then set data to it
    {

    }

    public categoryModel(String categoryName, int categoryImage) // non-empty constructor for creating an non-empty object and set data at a time
    {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    // now create setter and getter to set and get data from the object


    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public int getCategoryImage()
    {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage)
    {
        this.categoryImage = categoryImage;
    }
}
