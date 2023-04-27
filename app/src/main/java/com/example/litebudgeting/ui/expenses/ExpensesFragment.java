package com.example.litebudgeting.ui.expenses;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.litebudgeting.Keys;
import com.example.litebudgeting.R;
import com.example.litebudgeting.Subscription;
import com.example.litebudgeting.databinding.FragmentExpensesBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExpensesFragment extends Fragment {

    private FragmentExpensesBinding binding;
    private View otherBinding;
    private View root;
    private Button editButton;
    private FragmentActivity activity;
    private ViewGroup container;
    private LayoutInflater inflater;
    private Context context;
    private Bundle savedInstanceState;
    private int expenses;
    private List<String> expenseArray;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.container=container;
        this.inflater=inflater;
        this.savedInstanceState=savedInstanceState;
        ExpensesViewModel expensesViewModel =
                new ViewModelProvider(this).get(ExpensesViewModel.class);
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        binding = FragmentExpensesBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        activity = this.getActivity();
        TextView housing = root.findViewById(R.id.housing_cost);
        housing.setText("$ "+sharedPref.getFloat(Keys.HOUSING,0F));

        TextView water = root.findViewById(R.id.water_cost);
        water.setText("$ "+sharedPref.getFloat(Keys.WATER,0F));

        TextView electric = root.findViewById(R.id.electric_cost);
        electric.setText("$ "+sharedPref.getFloat(Keys.ELECTRICITY,0F));

        TextView AC = root.findViewById(R.id.AC_cost);
        AC.setText("$ "+sharedPref.getFloat(Keys.AC,0F));

        TextView car = root.findViewById(R.id.car_cost);
        car.setText("$ "+sharedPref.getFloat(Keys.CAR,0F));

        TextView health = root.findViewById(R.id.health_cost);
        health.setText("$ "+sharedPref.getFloat(Keys.HEALTH,0F));

        TextView transport = root.findViewById(R.id.transport_cost);
        transport.setText("$ "+sharedPref.getFloat(Keys.TRANSPORT,0F));

        TextView groc = root.findViewById(R.id.groc_cost);
        groc.setText("$ "+sharedPref.getFloat(Keys.GROCERIES,0F));

        TextView loan = root.findViewById(R.id.loan_cost);
        loan.setText("$ "+sharedPref.getFloat(Keys.LOAN,0F));

        context = this.getContext();

        addSubsToScroll();
        fillExpenseList();

        spinner(root);
        Spinner spinner = root.findViewById(R.id.list_expenses);
        expenses =spinner.getSelectedItemPosition();

        final TextView textView = binding.textNotifications;
        expensesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editButton = view.findViewById(R.id.btnEdit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpense();
            }
        });

    }
    private void spinner(View root){
        Spinner spinnerLanguages=root.findViewById(R.id.list_expenses);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, expenseArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }

    private void fillExpenseList(){
        String[] oldArray = getResources().getStringArray(R.array.expenses);
        expenseArray = new ArrayList<>(Arrays.asList(oldArray));
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        for (int i = 1; i <= sharedPref.getInt(Keys.SUB_COUNTER, 0); i++) {
            String json = sharedPref.getString(Keys.SUB+i, "");
            Subscription sub = gson.fromJson(json, Subscription.class);
            expenseArray.add(sub.getSubName());
        }
    }

    private void updateExpense(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        Spinner spinner = root.findViewById(R.id.list_expenses);
        int spinnerSelected = spinner.getSelectedItemPosition();
        String key ="";
        String expName = "";
        Subscription sub = null;
        float expCost = 0;
        int subIndex = 0;
        boolean isSub = false;
        switch (spinnerSelected){
            case 0: // Housing
                expName = "Housing";
                key = Keys.HOUSING;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 1: // Water
                expName = "Water";
                key = Keys.WATER;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 2: // Electricity
                expName = "Electricity";
                key = Keys.ELECTRICITY;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 3: // AC
                expName = "Air Conditioning";
                key = Keys.AC;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 4: // Car Insurance
                expName = "Car Insurance";
                key = Keys.CAR;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 5: // Health Insurance
                expName = "Health Insurance";
                key = Keys.HEALTH;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 6: // Transportation
                expName = "Transportation";
                key = Keys.TRANSPORT;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 7: // Groceries
                expName = "Groceries";
                key = Keys.GROCERIES;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            case 8: // Loans
                expName = "Loans";
                key = Keys.LOAN;
                expCost = sharedPref.getFloat(key, 0F);
                break;
            default: // Subs
                subIndex = spinnerSelected-8;
                isSub=true;

//                expName = "Housing";
                key = Keys.SUB+subIndex;
                Gson gson = new Gson();
                sub = gson.fromJson(sharedPref.getString(key,""), Subscription.class);
                expCost=sub.getSubCost();
                expName=sub.getSubName();
//                expCost = sharedPref.getFloat(Keys.SUB+subIndex, 0F);
                break;
        }

        otherBinding = inflater.inflate(R.layout.activity_edit_expense,container,false);
        binding.getRoot().removeAllViews();
        binding.getRoot().addView(otherBinding);
        root=binding.getRoot();

        TextView expNameView = root.findViewById(R.id.expenseName);
        expNameView.setText(expName);

        TextView expCurrView = root.findViewById(R.id.currentExpense);
        expCurrView.setText("$"+expCost);

        EditText newExp = root.findViewById(R.id.editUpdateExpense);

        Button btnUpdate = root.findViewById(R.id.btnUpdateExpense);
        String finalKey = key;
        String finalExpName = expName;
        boolean finalIsSub = isSub;
        Subscription finalSub = sub;
        int finalSubIndex = subIndex;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClickCalled", "FinalKey = "+finalKey);
                Log.d("onClickCalled", "newExp = "+newExp);
                if (newExp.getText().toString().isEmpty()){
                    newExp.setText(String.valueOf(0));
                }
                if (finalIsSub){
                    if (Float.parseFloat(newExp.getText().toString())==0){
                        int subCount = sharedPref.getInt(Keys.SUB_COUNTER,1);
                        Log.d("onResumeCalled", "IncomeFragment Selected Spinner Index = "+ finalSubIndex);
                        if (finalSubIndex == subCount){
                            Log.d("onResumeCalled", finalSubIndex + " = " + subCount);
                            prefEdit.putString(Keys.SUB+ finalSubIndex,"");
                            prefEdit.putInt(Keys.SUB_COUNTER, subCount-1);
                            prefEdit.apply();
                        }
                        else{
                            int current = finalSubIndex;
                            int next = finalSubIndex +1;
                            while (current < subCount){
                                prefEdit.putString(Keys.SUB+current,sharedPref.getString(Keys.SUB+next,""));
                                current++;
                                next++;
                            }
                            prefEdit.putString(Keys.SUB+current,"");
                            prefEdit.putInt(Keys.SUB_COUNTER, subCount-1);
                            prefEdit.apply();
                        }
                    }
                    else{
                        finalSub.setSubCost(Float.parseFloat(newExp.getText().toString()));
                        Gson gson = new Gson();
                        String json = gson.toJson(finalSub);
                        prefEdit.putString(finalKey, json);
                    }

                }else{
                    prefEdit.putFloat(finalKey,Float.parseFloat(newExp.getText().toString()));
                }

                prefEdit.apply();
                Toast.makeText(context, "Updated Monthly cost for " + finalExpName, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, context.getClass()));
            }
        });

    }

    private void addSubsToScroll(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        int lastID = R.id.loan_cost;
        int lastCostID = R.id.loan_cost;
        for (int i = 1; i <= sharedPref.getInt(Keys.SUB_COUNTER,0); i++){
            Log.d("addSubsToScroll", "Loop index: "+i+" was called");
            Log.d("addSubsToScroll","numOfSubs = "+sharedPref.getInt(Keys.SUB_COUNTER,0));
            int newID = View.generateViewId();
            int costID = View.generateViewId();
            TextView expName = new TextView(context);
            TextView expCost = new TextView(context);
            Gson gson = new Gson();
            LayoutParams nameParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );
            LayoutParams costParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
            );


            String json = sharedPref.getString(Keys.SUB+i, "");
            Subscription sub = gson.fromJson(json, Subscription.class);

            // Setup name view

            nameParams.setMargins(40,70,0,0);
            nameParams.addRule(RelativeLayout.BELOW, lastID);

            expName.setText(sub.getSubName());
            expName.setTypeface(null, Typeface.BOLD);
            expName.setTextSize(18);
            expName.setId(newID);

            expName.setLayoutParams(nameParams);

            RelativeLayout parentView = root.findViewById(R.id.expenses_layout);
            parentView.addView(expName);

            // Setup cost view

            costParams.addRule(RelativeLayout.BELOW, lastCostID);
            costParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            costParams.setMargins(0,70,40,0);

            expCost.setText("$ "+sub.getSubCost());
            expCost.setTypeface(null, Typeface.BOLD);
            expCost.setTextSize(18);
            expCost.setId(costID);

            expCost.setLayoutParams(costParams);

            RelativeLayout costParentView = root.findViewById(R.id.expenses_layout);
            costParentView.addView(expCost);


            lastID = newID;
            lastCostID = costID;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}