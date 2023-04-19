package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;


public class SetupP1 extends AppCompatActivity {
    private boolean is_salary;
    private int pay_period;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_p1);

        RadioButton hourly = findViewById(R.id.hourly_pay);


        RadioGroup radioGroup = findViewById(R.id.payment_form);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (hourly.isChecked()) {
                is_salary=false;
                findViewById(R.id.hourly_work_txt).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_work_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_pay_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_pay_txt).setVisibility(View.VISIBLE);
                findViewById(R.id.salary_text).setVisibility(View.GONE);
                findViewById(R.id.salary_edit).setVisibility(View.GONE);
            }
            else {
                is_salary=true;
                findViewById(R.id.salary_text).setVisibility(View.VISIBLE);
                findViewById(R.id.salary_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_work_txt).setVisibility(View.GONE);
                findViewById(R.id.hourly_work_edit).setVisibility(View.GONE);
                findViewById(R.id.hourly_pay_edit).setVisibility(View.GONE);
                findViewById(R.id.hourly_pay_txt).setVisibility(View.GONE);
            }
        });

        nextPageButton();
        spinner();
        Spinner spinner = findViewById(R.id.list_payPeriods);
        pay_period =spinner.getSelectedItemPosition();
    }

    private void nextPageButton() {
        Button nextButton = findViewById(R.id.btnNextP2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButtonClicked();
            }
        });
    }

    private void spinner(){
        Spinner spinnerLanguages=findViewById(R.id.list_payPeriods);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.pay_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }

    private void nextButtonClicked(){
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();
        Income job1;
        String workName = findViewById(R.id.income_name_edit).toString();
        if (is_salary){
            EditText salaryEdit = findViewById(R.id.salary_edit);
            String salaryStr = salaryEdit.getText().toString();
            float salary;
            try{salary = Float.parseFloat(salaryStr);}
            catch(Exception e){
                salary = 0F;
            }
            job1 = new Income(workName,is_salary, salary, pay_period);
        }
        else{
            EditText hourlyEdit = findViewById(R.id.hourly_pay_edit);
            String hourlyStr = hourlyEdit.toString();
            float hourly,workHour;
            try{hourly = Float.parseFloat(hourlyStr);}
            catch (Exception e){
                hourly = 0F;
            }
            EditText workHourEdit = findViewById(R.id.hourly_work_edit);
            String workHourStr = workHourEdit.toString();
            try{workHour = Float.parseFloat(workHourStr);}
            catch (Exception e){
                workHour = 0F;
            }
            job1 = new Income(workName,is_salary, hourly, workHour, pay_period);

        }
        int jobCount = sharedPref.getInt("jobCount", 1);
        Gson gson = new Gson();
        String json = gson.toJson(job1);
        prefEdit.putString("job"+jobCount, json);
        prefEdit.putInt("jobCount",jobCount);
        prefEdit.apply();
        Intent intent = new Intent(this, SetupP2.class);
        startActivity(intent);
    }

}