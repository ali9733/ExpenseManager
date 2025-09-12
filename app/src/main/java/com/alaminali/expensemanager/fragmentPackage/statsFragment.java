package com.alaminali.expensemanager.fragmentPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaminali.expensemanager.R;
import com.alaminali.expensemanager.databinding.FragmentStatsBinding;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        statsBinding=FragmentStatsBinding.inflate(inflater,container,false);

        /* SETTING PIE CHART ON STATSICTICS FRAGMENT BELOW */

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();                  // INSERT HERE CUSTOM DATA TO SHOW CHART
        data.add(new ValueDataEntry("Apples", 6371664));
        data.add(new ValueDataEntry("Pears", 789622));
        data.add(new ValueDataEntry("Bananas", 7216301));
        data.add(new ValueDataEntry("Grapes", 1486621));
        data.add(new ValueDataEntry("Oranges", 1200000));

        pie.data(data);

        pie.title("Fruits imported in 2015 (in kg)");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        statsBinding.anyChartView.setChart(pie);


        //------------END CODE HERE-------------

        return statsBinding.getRoot();
    }
}