package com.example.litebudgeting.ui.expenses;

import static com.example.litebudgeting.R.array.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.litebudgeting.Keys;
import com.example.litebudgeting.R;
import com.example.litebudgeting.databinding.FragmentExpensesBinding;


public class ExpensesFragment extends Fragment {

    private FragmentExpensesBinding binding;
    private int expenses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExpensesViewModel expensesViewModel =
                new ViewModelProvider(this).get(ExpensesViewModel.class);
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, Context.MODE_PRIVATE);
        binding = FragmentExpensesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        spinner(root);
        Spinner spinner = root.findViewById(R.id.list_expenses);
        expenses =spinner.getSelectedItemPosition();

        final TextView textView = binding.textNotifications;
        expensesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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