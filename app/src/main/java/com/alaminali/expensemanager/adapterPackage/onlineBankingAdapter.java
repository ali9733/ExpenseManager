package com.alaminali.expensemanager.adapterPackage;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class onlineBankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
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
    public static class noOnlineBankingViewHolder extends RecyclerView.ViewHolder
    {
        public noOnlineBankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
    public static class onlineBankingViewHolder extends RecyclerView.ViewHolder
    {
        public onlineBankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
