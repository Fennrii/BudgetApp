package com.example.litebudgeting;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import com.example.litebudgeting.ui.income.IncomeViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.litebudgeting.databinding.ActivityNavMainBinding;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NavMain extends AppCompatActivity {

    private ActivityNavMainBinding binding;

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);

        binding = ActivityNavMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_nav_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //Chart Code Example (Needs to have our data from Setup sheet.
        //There are other charts you can use if you want.
        pieChart = (PieChart) findViewById(R.id.example_Chart);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(55f);

        float totalIncome = 0;

        for (int i = 1; i <= sharedPref.getInt(Keys.JOB_COUNTER,1);i++){
            Gson gson = new Gson();
            String json = sharedPref.getString(Keys.JOB+i, "");
            Income job = gson.fromJson(json, Income.class);
            totalIncome+=job.getPay();
        }

        float housing = sharedPref.getFloat(Keys.HOUSING, 0F);
        float water = sharedPref.getFloat(Keys.WATER, 0F);
        float elec = sharedPref.getFloat(Keys.ELECTRICITY, 0F);
        float ac = sharedPref.getFloat(Keys.AC, 0F);
        float car = sharedPref.getFloat(Keys.CAR, 0F);
        float health = sharedPref.getFloat(Keys.HEALTH, 0F);
        float transport = sharedPref.getFloat(Keys.TRANSPORT, 0F);
        float groc = sharedPref.getFloat(Keys.GROCERIES, 0F);
        float loan = sharedPref.getFloat(Keys.LOAN, 0F);

        float neads = housing + water + elec + ac + car + health + transport + groc + loan;

        float remainingIncome = totalIncome-neads;

        ArrayList<PieEntry> yValues = new ArrayList<>();



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
        if(remainingIncome > 0) {
            yValues.add(new PieEntry(remainingIncome, "Unallocated"));
        }


        PieDataSet dataSet = new PieDataSet(yValues, "Expenses");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);



        pieChart.setData(data);



    }

}