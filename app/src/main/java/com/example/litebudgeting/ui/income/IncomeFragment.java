package com.example.litebudgeting.ui.income;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import com.example.litebudgeting.AddJob;
import com.example.litebudgeting.AddSub;
import com.example.litebudgeting.Income;
import com.example.litebudgeting.Keys;
import com.example.litebudgeting.NavMain;
import com.example.litebudgeting.R;
import com.example.litebudgeting.databinding.FragmentIncomeBinding;
import com.example.litebudgeting.ui.home.HomeFragment;
import com.google.gson.Gson;

import java.security.Key;

public class IncomeFragment extends Fragment {

    private FragmentIncomeBinding binding;
    private Button btnAddJobs;
    private Button btnDeleteJobs;
    private Button btnAddIncome;
    private Button btnAddSub;
    private Spinner spnJobs;
    private Income[] jobList;
    private int numJobs;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        IncomeViewModel incomeViewModel = new ViewModelProvider(this).get(IncomeViewModel.class);

        binding = FragmentIncomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        fillJobList();
        numJobs=jobList.length;
        String[] jobListString = new String[jobList.length];
        for (int i =0; i<jobList.length; i++){
            jobListString[i] = jobList[i].getWorkName();
        }

        spinner(root,jobListString);
        spnJobs = root.findViewById(R.id.list_jobTitles);



        final TextView textView = binding.textIncome;
        incomeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IncomeFragment activity = this;
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        btnAddJobs = view.findViewById(R.id.btnAddJobs);
        btnAddJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddJob.class);
                startActivity(intent);
            }
        });
        btnDeleteJobs = view.findViewById(R.id.btnDeleteJobs);
        btnDeleteJobs.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                int jobNum=spnJobs.getSelectedItemPosition()+1;
                String jobNameView = spnJobs.getSelectedItem().toString();
                String jobName = jobNameView;
                int jobCount = sharedPref.getInt(Keys.JOB_COUNTER,1);
                Log.d("onResumeCalled", "IncomeFragment Selected Spinner Index = "+jobNum);
                if (jobNum == jobCount){
                    Log.d("onResumeCalled", jobNum + " = " + jobCount);
                    prefEdit.putString(Keys.JOB+jobNum,"");
                    prefEdit.putInt(Keys.JOB_COUNTER, jobCount-1);
                    prefEdit.apply();
                }
                else{
                    int current = jobNum;
                    int next = jobNum+1;
                    while (current < jobCount){
                        prefEdit.putString(Keys.JOB+current,sharedPref.getString(Keys.JOB+next,""));
                        current++;
                        next++;
                    }
                    prefEdit.putString(Keys.JOB+current,"");
                    prefEdit.putInt(Keys.JOB_COUNTER, jobCount-1);
                    prefEdit.apply();
                }
                onResume();
                Toast.makeText(activity.getActivity(), "Job: \"" +jobName+ "\" Removed", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddIncome = view.findViewById(R.id.btnAddIncome);
        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float oldExtra = sharedPref.getFloat(Keys.EXTRA_INCOME,0);
                EditText extraIncomeEdit = view.findViewById(R.id.extIncometxt);
                float extraIncome;
                try {
                    extraIncome = Float.parseFloat(extraIncomeEdit.getText().toString());
                }
                catch (Exception e){
                    extraIncome = 0F;
                }
                prefEdit.putFloat(Keys.EXTRA_INCOME, extraIncome+oldExtra);
                prefEdit.apply();
                extraIncomeEdit.setText("");
                Toast.makeText(activity.getActivity(), "Added $"+extraIncome+" to the Monthly Earnings ", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddSub = view.findViewById(R.id.btnAddSub);
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSub.class));
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();


        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        Log.d("onResumeCalled", "Job Count ="+ sharedPref.getInt(Keys.JOB_COUNTER,0) );
        if (numJobs!=sharedPref.getInt(Keys.JOB_COUNTER,1)) {
            Log.d("onResumeCalled", "IncomeFragment If Statement Called");
            View root = binding.getRoot();

            fillJobList();
            String[] jobListString = new String[jobList.length];
            for (int i = 0; i < jobList.length; i++) {
                jobListString[i] = jobList[i].getWorkName();
            }

            spinner(root, jobListString);
            this.getActivity().recreate();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void spinner(View root, String[] jobListString){
        Spinner spinnerLanguages=root.findViewById(R.id.list_jobTitles);
        ArrayAdapter adapter= new ArrayAdapter(this.getActivity(),android.R.layout.simple_spinner_item,jobListString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }

//  Iterates through all job objects and adds them to an array of jobs
    public void fillJobList(){
        SharedPreferences sharedPref = this.getActivity().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        jobList = new Income[sharedPref.getInt(Keys.JOB_COUNTER,1)];
        for (int i = 1; i <= sharedPref.getInt(Keys.JOB_COUNTER,1); i++){
            Gson gson = new Gson();
            String json = sharedPref.getString(Keys.JOB+i, "");
            Income job = gson.fromJson(json, Income.class);
            jobList[i-1]=job;
        }

    }
}