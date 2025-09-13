package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.adapterPackage.transctionAdapter;
import com.alaminali.expensemanager.databinding.FragmentTransctionBinding;
import com.alaminali.expensemanager.dbUtils.transctionModel;
import com.alaminali.expensemanager.modelPackage.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;


public class transctionFragment extends Fragment {



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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding=FragmentTransctionBinding.inflate(inflater,container,false);

        //------------------------------CODING START FROM HERE--------------------------------

        //-----CREATE CALENDER INSTANCE------------
        calendar=Calendar.getInstance();

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

        setDataInTransctionAdapter();
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
    }

    private void updateDaily(Calendar calendar)
    {
        binding.calenderShowDateId.setText(Constants.getFullDateFormat(calendar));
    }

    private void updateDateWithData(Calendar calendar)
    {
       String fullDate= Constants.getFullDateFormat(calendar);
       String shortDate=Constants.getShortDateFormat(calendar);
        if (DAILY==1)
        {
            binding.calenderShowDateId.setText(fullDate);
        }
        else if (MONTHLY==2)
        {
            binding.calenderShowDateId.setText(shortDate);
        }

    }

    //-----------DATE SET AND DATE  UPDATION END HERE -------------------------------

    private void setDataInTransctionAdapter()
    {

        transctions=new ArrayList<>();
        transctions.add(new transctionModel(R.drawable.graph,2,2300,"Investment","Bank","expense","12/09/25","","this is notes"));
        transctions.add(new transctionModel(R.drawable.personal,3,2000,"Loan","Paypal","expense","12/04/25","","this is notes"));
        transctions.add(new transctionModel(R.drawable.graph,2,2300,"Investment","Bank","expense","12/09/25","","this is notes"));

        transctionAdapter transAdapter=new transctionAdapter(transctions,getContext());

        binding.transctionRecyclerId.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.transctionRecyclerId.setAdapter(transAdapter);
    }


}