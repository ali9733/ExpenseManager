package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.SingleItemNotFoundBinding;
import com.alaminali.expensemanager.databinding.TransctionSingleItemBinding;
import com.alaminali.expensemanager.dbUtils.transctionModel;

import java.util.ArrayList;

public class transctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    ArrayList<transctionModel>  transctions;
    LayoutInflater inflater;
    Context context;
    public static int NO_DATA_FOUND=0,DATA_FOUND=1;

    public transctionAdapter(ArrayList<transctionModel> transctions, Context context)
    {
        this.transctions = transctions;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position)
    {
         if (transctions.size()!=0)
         {
             return DATA_FOUND;
         }
        else
        {
            return NO_DATA_FOUND;

         }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
         if (viewType==DATA_FOUND)
         {
             return new transctionItemViewHolder(inflater.inflate(R.layout.transction_single_item,parent,false));
         }
         else
         {
           return new noTransctionDataViewHolder(inflater.inflate(R.layout.single_item_not_found,parent,false));
         }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
                 if (holder instanceof transctionItemViewHolder)
                 {

                 }
                 else {
                     if (holder instanceof noTransctionDataViewHolder)
                     {

                     }
                 }
    }

    @Override
    public int getItemCount()
    {
        if (transctions.size()==0)
        {
            return 1;
        }
        else {
            return transctions.size();
        }
    }

    public static class transctionItemViewHolder extends RecyclerView.ViewHolder
    {
        TransctionSingleItemBinding singleItemBinding;
        public transctionItemViewHolder(@NonNull View itemView)
        {
            super(itemView);
            singleItemBinding=TransctionSingleItemBinding.bind(itemView);
        }
    }

    public static class noTransctionDataViewHolder extends RecyclerView.ViewHolder
    {
        SingleItemNotFoundBinding notFoundBinding;

        public noTransctionDataViewHolder(@NonNull View itemView)
        {
            super(itemView);
            notFoundBinding=SingleItemNotFoundBinding.bind(itemView);

        }
    }


}
