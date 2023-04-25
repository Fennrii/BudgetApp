package com.example.litebudgeting.ui.expenses;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.litebudgeting.Keys;
import com.example.litebudgeting.NavMain;
import com.example.litebudgeting.R;
import com.example.litebudgeting.Subs;
import com.example.litebudgeting.databinding.FragmentExpensesBinding;

import java.util.zip.Inflater;


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

    private void updateExpense(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        Spinner spinner = root.findViewById(R.id.list_expenses);
        int spinnerSelected = spinner.getSelectedItemPosition();
        String key ="";
        String expName = "";
        float expCost = 0;
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
                int subIndex = spinnerSelected-7;
//                expName = "Housing";
//                key = Keys.SUB+subIndex;
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
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClickCalled", "FinalKey = "+finalKey);
                Log.d("onClickCalled", "newExp = "+newExp);
                prefEdit.putFloat(finalKey,Float.parseFloat(newExp.getText().toString()));
                prefEdit.apply();
                Toast.makeText(context, "Updated Monthly cost for " + finalExpName, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(context, context.getClass()));
            }
        });

    }

    private void spinner(View root){
        Spinner spinnerLanguages=root.findViewById(R.id.list_expenses);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this.getActivity(), R.array.expenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}