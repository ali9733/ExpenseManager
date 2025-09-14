package com.alaminali.expensemanager.adapterPackage;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class bankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public static class bankingViewHolder extends RecyclerView.ViewHolder
    {
        public bankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

    public static class noBankingViewHolder extends RecyclerView.ViewHolder
    {
        public noBankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }


}
