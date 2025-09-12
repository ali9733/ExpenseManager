package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.modelPackage.categoryModel;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder>
{
    ArrayList<categoryModel> categories;
    Context context;
    LayoutInflater inflater;


    public categoryAdapter(ArrayList<categoryModel> categories, Context context)
    {
        this.categories = categories;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       // View view=inflater.inflate(R.layout.)
        return new categoryViewHolder(inflater.inflate(R.layout.single_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }


    public class categoryViewHolder extends RecyclerView.ViewHolder
    {
        public categoryViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

}
