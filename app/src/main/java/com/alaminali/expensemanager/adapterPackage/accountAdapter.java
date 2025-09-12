package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.SingleAccountItemBinding;
import com.alaminali.expensemanager.modelPackage.accountModel;

import java.util.ArrayList;

public class accountAdapter extends RecyclerView.Adapter<accountAdapter.accountViewHolder>
{
    Context context;
    LayoutInflater inflater;
    ArrayList<accountModel> accounts;

    public interface setOnAccountDataListener
    {
        public void onAccountDataListener(accountModel model,int position);
    }
    setOnAccountDataListener listener;
    public accountAdapter(Context context, ArrayList<accountModel> accounts,setOnAccountDataListener listener)
    {
        this.context = context;
        this.accounts = accounts;
        this.listener=listener;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public accountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new accountViewHolder(inflater.inflate(R.layout.single_account_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull accountViewHolder holder, int position)
    {
        final accountModel model=accounts.get(position);
        holder.binding.paymentImageId.setImageResource(model.getAccountImage());
        holder.binding.paymentNameId.setText(model.getAccountName());
        holder.binding.paymentNameId.setTextColor(context.getColor(model.getTxtColor()));
        holder.itemView.setOnClickListener(v -> {
            listener.onAccountDataListener(model, holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount()
    {
        return accounts.size();
    }

    public class accountViewHolder extends RecyclerView.ViewHolder
    {
        SingleAccountItemBinding binding;
        public accountViewHolder(@NonNull View itemView)
        {
            super(itemView);
            binding=SingleAccountItemBinding.bind(itemView);
        }
    }
}
