package com.example.litebudgeting;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.litebudgeting.databinding.NavMainBinding;

public class NavMain extends AppCompatActivity {

    NavMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NavMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFrag(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    replaceFrag(new HomeFragment());
                    break;
                case R.id.income:
                    replaceFrag(new IncomeFragment());
                    break;
                case R.id.expenses:
                    replaceFrag(new ExpensesFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFrag(Fragment fragment){

    }
}
