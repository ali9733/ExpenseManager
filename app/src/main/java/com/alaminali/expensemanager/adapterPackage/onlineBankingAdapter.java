package com.alaminali.expensemanager.adapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.BankingItemBinding;
import com.alaminali.expensemanager.databinding.SingleItemNotFoundBinding;
import com.alaminali.expensemanager.dbUtils.onlineBanking;
import com.alaminali.expensemanager.interfacePackage.setOperationOnLongClickListener;
import com.alaminali.expensemanager.interfacePackage.setOperationOnLongClickListenerTwo;

import org.apache.commons.text.WordUtils;

import java.util.ArrayList;

public class onlineBankingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
     Context context;
     LayoutInflater inflater;
     ArrayList<onlineBanking> bankings;
      public static int ONLINE_BANK_DATA_FOUND=1,ONLINE_BANK_DATA_NOT_FOUND=0;

    setOperationOnLongClickListenerTwo onLongClickListener;

    public onlineBankingAdapter(Context context, ArrayList<onlineBanking> bankings, setOperationOnLongClickListenerTwo onLongClickListener)
    {
        this.context = context;
        this.bankings = bankings;
        this.onLongClickListener=onLongClickListener;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position)
    {
       if (bankings.size()!=0)
       {
           return ONLINE_BANK_DATA_FOUND;
       }
       else
       {
           return ONLINE_BANK_DATA_NOT_FOUND;
       }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
          if (viewType==ONLINE_BANK_DATA_FOUND)
          {
              return new onlineBankingViewHolder(inflater.inflate(R.layout.banking_item,parent,false));
          }
          else
          {
              return new noOnlineBankingViewHolder(inflater.inflate(R.layout.single_item_not_found,parent,false));
          }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

          if (holder instanceof onlineBankingViewHolder)
          {  final onlineBanking model=bankings.get(position);
              String fourDigitNumber=null;
              if (model!=null&&model.getCardNumber()!=null)
              {
                fourDigitNumber=model.getCardNumber().substring(12);
              }
              ((onlineBankingViewHolder)holder).bankingItemBinding.bankingNameId.setText(WordUtils.capitalizeFully(model.getBankingName()));
              ((onlineBankingViewHolder)holder).bankingItemBinding.bankingNumberId.setText("xxxxxxxxxxxx"+fourDigitNumber);
              ((onlineBankingViewHolder)holder).bankingItemBinding.bankingAmountId.setText(String.valueOf(model.getBankAmount()));


              if (model.getBankingName().toLowerCase().contains("phone pay"))
              {
                  ((onlineBankingViewHolder)holder).bankingItemBinding.bankingImageId.setImageResource(R.drawable.phonepay);
              }
              else if (model.getBankingName().toLowerCase().contains("paypal"))
              {
                  ((onlineBankingViewHolder)holder).bankingItemBinding.bankingImageId.setImageResource(R.drawable.paypal);
              }
              else if (model.getBankingName().toLowerCase().contains("paytm"))
              {
                  ((onlineBankingViewHolder)holder).bankingItemBinding.bankingImageId.setImageResource(R.drawable.paytm);
              }
              else if (model.getBankingName().toLowerCase().contains("debit card"))
              {
                  ((onlineBankingViewHolder)holder).bankingItemBinding.bankingImageId.setImageResource(R.drawable.credit_card);
              }


              ((onlineBankingViewHolder)holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
                  @Override
                  public boolean onLongClick(View v)
                  {
                      onLongClickListener.onOperationOnLongClickListenerTwo(model,holder.getAdapterPosition());
                      return true;
                  }
              });


          }
          else if (holder instanceof noOnlineBankingViewHolder)
          {
              ((noOnlineBankingViewHolder)holder).itemNotFoundBinding.itemNotFoundId.setText("BANK DATA NOT FOUND!!");
          }

    }

    @Override
    public int getItemCount()
    {
        if (bankings.size()!=0)
        {
            return bankings.size();
        }
        else {
            return 1;
        }
    }
    public static class noOnlineBankingViewHolder extends RecyclerView.ViewHolder
    {
         SingleItemNotFoundBinding itemNotFoundBinding;
        public noOnlineBankingViewHolder(@NonNull View itemView)
        {

            super(itemView);
            itemNotFoundBinding=SingleItemNotFoundBinding.bind(itemView);
        }
    }
    public static class onlineBankingViewHolder extends RecyclerView.ViewHolder
    {
        BankingItemBinding bankingItemBinding;
        public onlineBankingViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bankingItemBinding=BankingItemBinding.bind(itemView);
        }
    }
}
