package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.SingleCategoryItemBinding;
import com.alaminali.expensemanager.modelPackage.categoryModel;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder>
{
    ArrayList<categoryModel> categories;
    Context context;
    LayoutInflater inflater;
  public interface setOnCategoryDataListener
  {
      public void onCategoryDataListener(categoryModel model,int position);
  }

  setOnCategoryDataListener listener;

    public categoryAdapter(ArrayList<categoryModel> categories, Context context,setOnCategoryDataListener listener)
    {
        this.categories = categories;
        this.context = context;
        this.listener=listener;
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
           final categoryModel model=categories.get(position);
           holder.categoryItemBinding.categoryImageId.setImageResource(model.getCategoryImage());
           holder.categoryItemBinding.categoryNameId.setText(model.getCategoryName());
           holder.categoryItemBinding.categoryNameId.setTextColor(context.getColor(model.getTxtColor()));

           holder.itemView.setOnClickListener(v -> {
              listener.onCategoryDataListener(model, holder.getAdapterPosition());
           });
    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }


    public class categoryViewHolder extends RecyclerView.ViewHolder
    {
        SingleCategoryItemBinding categoryItemBinding;
        public categoryViewHolder(@NonNull View itemView)
        {
            super(itemView);
            categoryItemBinding=SingleCategoryItemBinding.bind(itemView);
        }
    }

}
