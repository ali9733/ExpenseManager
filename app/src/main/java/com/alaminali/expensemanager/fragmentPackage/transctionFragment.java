package com.alaminali.expensemanager.fragmentPackage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.adapterPackage.transctionAdapter;
import com.alaminali.expensemanager.databinding.FragmentTransctionBinding;
import com.alaminali.expensemanager.dbUtils.transctionModel;
import com.alaminali.expensemanager.dbUtils.transctionViewModel;
import com.alaminali.expensemanager.interfacePackage.triggerToOpenDialogOnLongClickListener;
import com.alaminali.expensemanager.modelPackage.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;


public class transctionFragment extends Fragment implements triggerToOpenDialogOnLongClickListener {



    public transctionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    FragmentTransctionBinding binding;
    ArrayList<transctionModel> transctions;
    Calendar calendar;
    public int DAILY=1;
    public int MONTHLY=0;
    transctionViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding=FragmentTransctionBinding.inflate(inflater,container,false);

        //------------------------------CODING START FROM HERE--------------------------------

        //-----CREATE CALENDER INSTANCE------------
        calendar=Calendar.getInstance();
        binding.calenderShowDateId.setText(Constants.getFullDateFormat(calendar));
        setDataInTransctionAdapter(Constants.getFullDateFormat(calendar),"app_launch");

        //----------LEFT BUTTON CLICK TO DATE CHANGE--------
        binding.leftBtn.setOnClickListener(v -> {

            if (DAILY==1)
            {
              calendar.add(Calendar.DAY_OF_MONTH,-1);
                updateDateWithData(calendar);
            }
            else if (MONTHLY==2)
            {
                calendar.add(Calendar.MONTH,-1);
                updateDateWithData(calendar);
            }

        });

        //----------RIGHT BUTTON CLICK TO DATE CHANGE-------
        binding.rightBtn.setOnClickListener(v -> {
            if (DAILY==1)
            {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                updateDateWithData(calendar);
            }
            else if (MONTHLY==2)
            {
                calendar.add(Calendar.MONTH,1);
                updateDateWithData(calendar);
            }

        });




        /* AFTER CLICKING FLOATING BUTTON DIALOG WILL BE OPENED */

        binding.floatingActionButton.setOnClickListener(v -> {

            dialogFragment fragmentDialog=new dialogFragment();
            fragmentDialog.show(getActivity().getSupportFragmentManager(), fragmentDialog.getTag());

        });

        //setDataInTransctionAdapter();
        //------------TABLAYOUT TO SELECT DAILY/MONTHLY/CALENDER/NOTES---------------

        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if (tab.getText().toString().equals("Daily"))
                {
                    DAILY=1;
                    MONTHLY=0;
                    updateDaily(calendar);
                }
                else if (tab.getText().toString().equals("monthly"))
                {
                  MONTHLY=2;
                  DAILY=0;
                  updateMonthly(calendar);
                }
                else if (tab.getText().toString().equals("notes"))
                {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





       //----------------------------CODING END HERE-------------------------------------------
        return binding.getRoot();
    }

    //-----------DATE SET AND DATE  UPDATION -------------------------------
    private void updateMonthly(Calendar calendar)
    {
        binding.calenderShowDateId.setText(Constants.getShortDateFormat(calendar));
        setDataInTransctionAdapter(Constants.getShortDateFormat(calendar),"updateMonthly");
    }

    private void updateDaily(Calendar calendar)
    {
        binding.calenderShowDateId.setText(Constants.getFullDateFormat(calendar));
        setDataInTransctionAdapter(Constants.getFullDateFormat(calendar),"updateDaily");
    }

    private void updateDateWithData(Calendar calendar)
    {
       String fullDate= Constants.getFullDateFormat(calendar);
       String shortDate=Constants.getShortDateFormat(calendar);
        if (DAILY==1)
        {
            binding.calenderShowDateId.setText(fullDate);
            setDataInTransctionAdapter(fullDate,"daily");
        }
        else if (MONTHLY==2)
        {
            binding.calenderShowDateId.setText(shortDate);
            setDataInTransctionAdapter(shortDate,"monthly");
        }

    }

    //-----------DATE SET AND DATE  UPDATION END HERE -------------------------------

    private void setDataInTransctionAdapter(String date,String types)
    {
        transctions=new ArrayList<>();
        viewModel=new  ViewModelProvider(getActivity()).get(transctionViewModel.class);

        if (types.equals("daily"))
        {

           transctions.clear();
           viewModel.getDailyTransctionViewData(date).observe(getActivity(),transctionModels -> {
           transctions.clear();
           transctions.addAll(transctionModels);

               finallySetDataToAdapter(transctions);


           });

        }
        else if (types.equals("monthly"))
        {

            transctions.clear();
            viewModel.getMonthlyTransctionViewData(date).observe(getActivity(),transctionModels -> {
                transctions.clear();
                transctions.addAll(transctionModels);
                finallySetDataToAdapter(transctions);

            });

        }
        else if (types.equals("updateMonthly"))
        {

            transctions.clear();
            viewModel.getMonthlyTransctionViewData(date).observe(getActivity(),transctionModels -> {
                transctions.clear();
                transctions.addAll(transctionModels);
                finallySetDataToAdapter(transctions);

            });


        }
        else if (types.equals("updateDaily"))
        {

            transctions.clear();
            viewModel.getDailyTransctionViewData(date).observe(getActivity(),transctionModels -> {
                transctions.clear();
                transctions.addAll(transctionModels);

                finallySetDataToAdapter(transctions);

            });

        }
        else if (types.equals("app_launch"))
        {

            transctions.clear();
            viewModel.getDailyTransctionViewData(date).observe(getActivity(),transctionModels -> {
                transctions.clear();
                transctions.addAll(transctionModels);

                finallySetDataToAdapter(transctions);

            });

        }



    }

    private void finallySetDataToAdapter(ArrayList<transctionModel> transctionsRecord)
    {
        /* calling this function for set total income, total expense and rest total */
        setIncomeAndExpense(transctionsRecord);

        /* setting list of data to adapter */
        transctionAdapter transAdapter=new transctionAdapter(transctionsRecord,getContext(),this);
        binding.transctionRecyclerId.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.transctionRecyclerId.setAdapter(transAdapter);


    }

    private void setIncomeAndExpense(ArrayList<transctionModel> transctionsRecords)
    {
       double totalIncome=0.0,totalExpense=0.0,totalAmount=0.0;

        for (transctionModel model:transctionsRecords)
        {
            if (model.getType().equals(Constants.INCOME))
            {
                totalIncome+=model.getAmount();
            }
            else if (model.getType().equals(Constants.EXPENSE))
            {
                totalExpense+= model.getAmount();
            }
        }

        totalAmount=totalIncome+(totalExpense*-1);

    binding.transExpenseId.setText("-"+totalExpense);
    binding.transctionIncomeId.setText(String.valueOf(totalIncome));
    binding.transTotlatMoneyId.setText(String.format("%.2f",totalAmount));

    }

    @Override
    public void toOpenDialogOnLongClickListener(transctionModel model, int position)
    {
        LinearLayout showNote,deleteRecord;

        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        showNote=dialog.findViewById(R.id.shownotes_id);
        deleteRecord=dialog.findViewById(R.id.delete_id);

        /* SHOW NOTES ALERT DIALOG WILL BE OPENED TO SHOW */

        showNote.setOnClickListener(v -> {
            dialog.dismiss();
            AlertDialog.Builder shownoteDialog=new AlertDialog.Builder(getContext());
            shownoteDialog.setTitle("Category: "+model.getCategoryName());
            shownoteDialog.setMessage("Notes: "+model.getNotes());
            shownoteDialog.setCancelable(false);
            shownoteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });


            shownoteDialog.show();

        });

        /* DELETE RECORDS ALERT DIALOG WILL BE OPENED TO SHOW */

        deleteRecord.setOnClickListener(v -> {
            dialog.dismiss();
          AlertDialog.Builder deleteDialog=new AlertDialog.Builder(getContext());
          deleteDialog.setCancelable(false);
          deleteDialog.setTitle("Delete "+model.getCategoryName());
          deleteDialog.setMessage("Are You Sure to Delete ?");
          deleteDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which)
              {
                    viewModel=new ViewModelProvider(getActivity()).get(transctionViewModel.class);
                    viewModel.deleteTransctionViewData(model.getUid());
              }
          });

          deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which)
              {

              }
          });

          deleteDialog.show();
        });

        dialog.show();

    }
}