package com.example.litebudgeting.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.litebudgeting.Income;
import com.example.litebudgeting.Keys;
import com.example.litebudgeting.R;
import com.example.litebudgeting.databinding.FragmentHomeBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;


import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private PieChart pieChart;
    private float totalIncome;
    private float housing;
    private float water;
    private float elec;
    private float ac;
    private float car;
    private float health;
    private float transport;
    private float groc;
    private float loan;
    private float needs;
    private float remainingIncome;
    private float oldIncome;
    private float extraIncome;
    private float subs;
    private Switch switch2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();


        //Chart Code Example (Needs to have our data from Setup sheet.
        //There are other charts you can use if you want.
        pieChart = root.findViewById(R.id.example_Chart);


        retrieveValues();
        oldIncome=totalIncome;
        TextView incomeText = root.findViewById(R.id.showAmount);
        incomeText.setText("$"+remainingIncome);

        itemizedPieChart();
//        ratioPieChart();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch2 = view.findViewById(R.id.switch2);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                if (isChecked){
                    ratioPieChart();
                }
                else {
                    itemizedPieChart();
                }
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();

        Log.d("onResumeCalled", "HomeFragment Called onResume");
        Log.d("increaseTotalIncome", "OldIncome = "+totalIncome);
        retrieveValues();
        Log.d("increaseTotalIncome", "NewIncome = "+totalIncome);
        if (oldIncome!=totalIncome) {
            Log.d("onResumeCalled", "HomeFragment if statement called");
            View root = binding.getRoot();


            //Chart Code Example (Needs to have our data from Setup sheet.
            //There are other charts you can use if you want.
            pieChart = root.findViewById(R.id.example_Chart);


            TextView incomeText = root.findViewById(R.id.showAmount);
            incomeText.setText("$" + remainingIncome);

            itemizedPieChart();
            this.getActivity().recreate();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void itemizedPieChart(){
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.animateX(500);
        pieChart.animate();

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.getLegend().setWordWrapEnabled(true);
        pieChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

        ArrayList<PieEntry> yValues = new ArrayList<>();


        if(remainingIncome > 0) {

            yValues.add(new PieEntry(remainingIncome, "Remaining"));
        }
        if(subs > 0){
            yValues.add(new PieEntry(subs, "Subscriptions"));
        }
        if(housing > 0) {
            yValues.add(new PieEntry(housing, "Housing"));
        }
        if(water > 0) {
            yValues.add(new PieEntry(water, "Water"));
        }
        if(elec > 0) {
            yValues.add(new PieEntry(elec, "Electricity"));
        }
        if(ac > 0) {
            yValues.add(new PieEntry(ac, "Air Conditioning"));
        }
        if(car > 0) {
            yValues.add(new PieEntry(car, "Car Insurance"));
        }
        if(health > 0) {
            yValues.add(new PieEntry(health, "Health Insurance"));
        }
        if(transport > 0) {
            yValues.add(new PieEntry(transport, "Transportation"));
        }
        if(groc > 0) {
            yValues.add(new PieEntry(groc, "Groceries"));
        }
        if(loan > 0) {
            yValues.add(new PieEntry(loan, "Loans"));
        }



        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.createColors(new int[]{ColorTemplate.rgb("#39E600"),
                ColorTemplate.rgb("#e60000"), ColorTemplate.rgb("#e6ac00"), ColorTemplate.rgb("#e67300"),
                ColorTemplate.rgb("#00e6ac"), ColorTemplate.rgb("#00e6e6"), ColorTemplate.rgb("#00ace6"),
                ColorTemplate.rgb("#7300e6"), ColorTemplate.rgb("#ac00e6"), ColorTemplate.rgb("#e600e6"),
                ColorTemplate.rgb("#e60073")}));


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);



        pieChart.setData(data);

    }

    private void ratioPieChart(){
        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setEntryLabelColor(Color.BLACK);

        pieChart.animateX(500);
        pieChart.animate();

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(55f);
        pieChart.getLegend().setWordWrapEnabled(true);

        ArrayList<PieEntry> yValues = new ArrayList<>();


        if(remainingIncome > 0) {
            yValues.add(new PieEntry(remainingIncome, "Remaining"));
        }
        if(housing > 0) {
            yValues.add(new PieEntry(needs, "Living Expenses"));
        }
        if(subs > 0) {
            yValues.add(new PieEntry(subs, "Subscriptions"));
        }



        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.createColors(new int[]{ColorTemplate.rgb("#39E600")
                ,ColorTemplate.rgb("#FF5C33")}));


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);




        pieChart.setData(data);
    }

    private void retrieveValues(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);

        totalIncome = 0;

        for (int i = 1; i <= sharedPref.getInt(Keys.JOB_COUNTER,1); i++){
            Gson gson = new Gson();
            String json = sharedPref.getString(Keys.JOB+i, "");
            Income job = gson.fromJson(json, Income.class);
            totalIncome+=job.getPay();
        }

        subs = 0;

        extraIncome = sharedPref.getFloat(Keys.EXTRA_INCOME, 0);
        totalIncome += extraIncome;
        housing = sharedPref.getFloat(Keys.HOUSING, 0F);
        water = sharedPref.getFloat(Keys.WATER, 0F);
        elec = sharedPref.getFloat(Keys.ELECTRICITY, 0F);
        ac = sharedPref.getFloat(Keys.AC, 0F);
        car = sharedPref.getFloat(Keys.CAR, 0F);
        health = sharedPref.getFloat(Keys.HEALTH, 0F);
        transport = sharedPref.getFloat(Keys.TRANSPORT, 0F);
        groc = sharedPref.getFloat(Keys.GROCERIES, 0F);
        loan = sharedPref.getFloat(Keys.LOAN, 0F);

        needs = housing + water + elec + ac + car + health + transport + groc + loan;

        remainingIncome = totalIncome-needs;
    }
}