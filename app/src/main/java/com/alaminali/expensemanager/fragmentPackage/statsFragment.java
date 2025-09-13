package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentStatsBinding;
import com.alaminali.expensemanager.dbUtils.transctionModel;
import com.alaminali.expensemanager.dbUtils.transctionViewModel;
import com.alaminali.expensemanager.modelPackage.Constants;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class statsFragment extends Fragment {



    public statsFragment()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
     FragmentStatsBinding statsBinding;

     transctionViewModel viewModel;
     Calendar calendar;

     public int CALENDER_DAILY=1,CALENDER_MONTHLY=0;
     public int INCOME_TYPE=1;
     public int EXPENSE_TYPE=0;

    // Add this to your class fields
    private Observer<List<transctionModel>> transactionObserver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        statsBinding=FragmentStatsBinding.inflate(inflater,container,false);

        /* CUSTOMIZE CODING STARTS FROM HERE INSIDE onCreate METHOD */

        calendar=Calendar.getInstance();
        statsBinding.statsCalenderShowDateId.setText(Constants.getFullDateFormat(calendar));


        statsBinding.statsLeftBtn.setOnClickListener(v -> {
           if (CALENDER_DAILY==1)
           {
               calendar.add(Calendar.DAY_OF_MONTH,-1);
               updateCalender(calendar,"daily");
           }
           else if (CALENDER_MONTHLY==2)
           {
             calendar.add(Calendar.MONTH,-1);
               updateCalender(calendar,"monthly");
           }

        });

        statsBinding.statsRightBtn.setOnClickListener(v -> {

            if (CALENDER_DAILY==1)
            {
                calendar.add(Calendar.DAY_OF_MONTH,1);
                updateCalender(calendar,"daily");
            }
            else if (CALENDER_MONTHLY==2)
            {
                calendar.add(Calendar.MONTH,1);
                updateCalender(calendar,"monthly");
            }

        });


        /* DAILY AND MONTHLY BUTTON SETTING */
        statsBinding.statsDailyId.setTextColor(getContext().getColor(R.color.red));
        statsBinding.statsDailyId.setBackground(getContext().getDrawable(R.drawable.label_background));

        statsBinding.statsDailyId.setOnClickListener(v -> {

            CALENDER_DAILY=1;
            CALENDER_MONTHLY=0;
            updateCalenderCurrently(calendar,"daily");
            statsBinding.statsDailyId.setTextColor(getContext().getColor(R.color.red));
            statsBinding.statsDailyId.setBackground(getContext().getDrawable(R.drawable.label_background));

            statsBinding.statsMonthlyId.setTextColor(getContext().getColor(R.color.white));
            statsBinding.statsMonthlyId.setBackground(getContext().getDrawable(R.drawable.label_unselected));

        });
        statsBinding.statsMonthlyId.setOnClickListener(v -> {

            CALENDER_MONTHLY=2;
            CALENDER_DAILY=0;
            updateCalenderCurrently(calendar,"monthly");

            statsBinding.statsDailyId.setTextColor(getContext().getColor(R.color.white));
            statsBinding.statsDailyId.setBackground(getContext().getDrawable(R.drawable.label_unselected));

            statsBinding.statsMonthlyId.setTextColor(getContext().getColor(R.color.red));
            statsBinding.statsMonthlyId.setBackground(getContext().getDrawable(R.drawable.label_background));


        });
       /* INCOME AND EXPENSE BUTTON SETTING */

        statsBinding.statsIncomeBtnId.setOnClickListener(v -> {
            INCOME_TYPE=1;
            EXPENSE_TYPE=0;
            statsBinding.statsIncomeBtnId.setBackground(getContext().getDrawable(R.drawable.selected_income_btn));
            statsBinding.statsExpenseBtnId.setBackground(getContext().getDrawable(R.drawable.unselected_btn));
            statsBinding.statsIncomeBtnId.setTextColor(getContext().getColor(R.color.Darkgreen));
            statsBinding.statsExpenseBtnId.setTextColor(getContext().getColor(R.color.dark_blur));
            setDataToPieChart(Constants.getFullDateFormat(calendar),"daily");


        });
        statsBinding.statsExpenseBtnId.setOnClickListener(v -> {

            EXPENSE_TYPE=2;
            INCOME_TYPE=0;
            statsBinding.statsIncomeBtnId.setBackground(getContext().getDrawable(R.drawable.unselected_btn));
            statsBinding.statsExpenseBtnId.setBackground(getContext().getDrawable(R.drawable.selected_expense_btn));
            statsBinding.statsIncomeBtnId.setTextColor(getContext().getColor(R.color.dark_blur));
            statsBinding.statsExpenseBtnId.setTextColor(getContext().getColor(R.color.red));
            setDataToPieChart(Constants.getFullDateFormat(calendar),"daily");

        });



      //  setDataToPieChart(Constants.getFullDateFormat(calendar),"daily");




        /* CUSTOMIZE CODING ENDS FROM HERE INSIDE onCreate METHOD */

        return statsBinding.getRoot();
    }

    private void updateCalenderCurrently(Calendar calendar,String types)
    {
        if (types.equals("daily"))
        {
            statsBinding.statsCalenderShowDateId.setText(Constants.getFullDateFormat(calendar));
            setDataToPieChart(Constants.getFullDateFormat(calendar),"daily");

        }
        else if (types.equals("monthly"))
        {
            statsBinding.statsCalenderShowDateId.setText(Constants.getShortDateFormat(calendar));
            setDataToPieChart(Constants.getShortDateFormat(calendar),"monthly");
        }
    }

    private void updateCalender(Calendar calendar,String types)
    {
        if (types.equals("daily"))
        {
            statsBinding.statsCalenderShowDateId.setText(Constants.getFullDateFormat(calendar));
            setDataToPieChart(Constants.getFullDateFormat(calendar),"daily");
        }
        else if (types.equals("monthly"))
        {
            statsBinding.statsCalenderShowDateId.setText(Constants.getShortDateFormat(calendar));
            setDataToPieChart(Constants.getShortDateFormat(calendar),"monthly");
        }
    }

    private void setDataToPieChart(String date,String types)
    {
        viewModel=new ViewModelProvider(getActivity()).get(transctionViewModel.class);
        ArrayList<transctionModel> transctions=new ArrayList<>();


     /* copy paste from deepseek */
        if (viewModel.getDailyTransctionViewData(date).hasActiveObservers()) {
            viewModel.getDailyTransctionViewData(date).removeObservers(getViewLifecycleOwner());
        }
        if (viewModel.getMonthlyTransctionViewData(date).hasActiveObservers()) {
            viewModel.getMonthlyTransctionViewData(date).removeObservers(getViewLifecycleOwner());
        }

        /* copy paste from deepseek  end here*/




             if (types.equals("daily"))
             {


                 transctions.clear();


                 viewModel.getDailyTransctionViewData(date).observe(getViewLifecycleOwner(),transctionModels -> {

                   transctions.clear();

                   transctions.addAll(transctionModels);


                     extractUsefulDatas(transctions);

                 });
             }
             else if (types.equals("monthly"))
             {
                 transctions.clear();
                 viewModel.getMonthlyTransctionViewData(date).observe(getViewLifecycleOwner(),transctionModels -> {

                     transctions.clear();

                     transctions.addAll(transctionModels);

                     extractUsefulDatas(transctions);


                 });


             }

    }


    private void extractUsefulDatas(ArrayList<transctionModel> transction)
    {
        List<DataEntry> data = new ArrayList<>();
        data.clear();

        if (INCOME_TYPE==1)
        {
            data.clear();
            for (transctionModel model:transction)
            {
               if (model.getType().equals(Constants.INCOME))
               {



                   data.add(new ValueDataEntry(model.getCategoryName(), model.getAmount()));

               }
            }

            pieChartFinalSetup(data);
        }
        else if (EXPENSE_TYPE==2)
        {
            data.clear();

            for (transctionModel model:transction)
            {
                  if (model.getType().equals(Constants.EXPENSE))
                  {
                      data.add(new ValueDataEntry(model.getCategoryName(), model.getAmount()));
                  }
            }

            pieChartFinalSetup(data);
        }


    }

    private void pieChartFinalSetup(List<DataEntry> data)
    {

          Pie pie = AnyChart.pie();

//        List<DataEntry> data = new ArrayList<>();                  // INSERT HERE CUSTOM DATA TO SHOW CHART
//        data.add(new ValueDataEntry("Apples", 6371664));
//        data.add(new ValueDataEntry("Pears", 789622));
//        data.add(new ValueDataEntry("Bananas", 7216301));
//        data.add(new ValueDataEntry("Grapes", 1486621));
//        data.add(new ValueDataEntry("Oranges", 1200000));

        Toast.makeText(getContext(), "Size:"+data.size(), Toast.LENGTH_SHORT).show();




        pie.data(data);

        pie.title("DAT TO DAY LIFE INCOME AND SPENT");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Expense Tracking")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        statsBinding.anyChartView.clear();
        statsBinding.anyChartView.setChart(pie);
        // Force a refresh
        statsBinding.anyChartView.invalidate();
        statsBinding.anyChartView.requestLayout();

    }




}