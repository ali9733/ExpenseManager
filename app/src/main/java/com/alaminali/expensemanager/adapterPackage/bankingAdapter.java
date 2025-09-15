package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.apache.commons.text.WordUtils;
import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.BankingItemBinding;
import com.alaminali.expensemanager.databinding.SingleItemNotFoundBinding;
import com.alaminali.expensemanager.dbUtils.bankModel;
import com.alaminali.expensemanager.interfacePackage.setOperationOnLongClickListener;

import java.util.List;

public class bankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
     List<bankModel> bankLists;
     Context context;
     LayoutInflater inflater;

     public static final int BANK_FOUND=1,BANK_NOT_FOUND=0;

    setOperationOnLongClickListener longClickListener;

    public bankingAdapter(List<bankModel> bankLists, Context context,setOperationOnLongClickListener longClickListener)
    {
        this.bankLists = bankLists;
        this.context = context;
        this.longClickListener=longClickListener;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position)
    {
        if (bankLists.size()!=0)
        {
            return BANK_FOUND;
        }
        else {
         return    BANK_NOT_FOUND;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       if (viewType==BANK_FOUND)
       {
           return new bankingViewHolder(inflater.inflate(R.layout.banking_item,parent,false));
       }
       else
       {
          return new noBankingViewHolder(inflater.inflate(R.layout.single_item_not_found,parent,false));
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
          if (holder instanceof bankingViewHolder)
          {
              final bankModel model=bankLists.get(position);
              String bankNumber= null;
              if (model!=null&&model.getBankNumber()!=null) {
                  bankNumber = model.getBankNumber().substring(12);
              }
              ((bankingViewHolder)holder).bankingItemBinding.bankingAmountId.setText(String.valueOf(model.getBankAmount()));
              ((bankingViewHolder)holder).bankingItemBinding.bankingNameId.setText(WordUtils.capitalizeFully(model.getBankName()));
              ((bankingViewHolder)holder).bankingItemBinding.bankingNumberId.setText("xxxxxxxxxxxx"+bankNumber);

              ((bankingViewHolder)holder).bankingItemBinding.bankingImageId.setImageResource(R.drawable.bank);



              ((bankingViewHolder)holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                  @Override
                  public boolean onLongClick(View v)
                  {
                      longClickListener.onOperationOnLongClickListener(model, holder.getAdapterPosition());
                      return true;
                  }
              });

          }
          else if (holder instanceof noBankingViewHolder)
          {
              ((noBankingViewHolder)holder).noDataBinding.itemNotFoundId.setText("NO BANK DATA FOUND !!");
          }
    }

    @Override
    public int getItemCount()
    {
        if (bankLists.size()!=0)
        {
            return bankLists.size();
        }
        else {
            return 1;
        }

    }

    public static class bankingViewHolder extends RecyclerView.ViewHolder
    {
        BankingItemBinding bankingItemBinding;
        public bankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bankingItemBinding=BankingItemBinding.bind(itemView);
        }
    }


    public static class noBankingViewHolder extends RecyclerView.ViewHolder
    {
        SingleItemNotFoundBinding noDataBinding;
        public noBankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
            noDataBinding=SingleItemNotFoundBinding.bind(itemView);
        }
    }


}
