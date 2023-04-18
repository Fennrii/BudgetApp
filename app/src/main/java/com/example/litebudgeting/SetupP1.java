package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SetupP1 extends AppCompatActivity {

    private EditText bankAccountInitialEditText;
    private float bankAccountInitialInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup_p1);
        //Testing


        RadioButton hourly = findViewById(R.id.hourly_pay);
        RadioButton salary = findViewById(R.id.salary_pay);
        RadioButton government = findViewById(R.id.government_pay);

        RadioGroup radioGroup = findViewById(R.id.payment_form);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (hourly.isChecked()) {
                findViewById(R.id.hourly_work_txt).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_work_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_pay_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_pay_txt).setVisibility(View.VISIBLE);
                findViewById(R.id.salary_text).setVisibility(View.GONE);
                findViewById(R.id.salary_edit).setVisibility(View.GONE);
            }
            else {
                findViewById(R.id.salary_text).setVisibility(View.VISIBLE);
                findViewById(R.id.salary_edit).setVisibility(View.VISIBLE);
                findViewById(R.id.hourly_work_txt).setVisibility(View.GONE);
                findViewById(R.id.hourly_work_edit).setVisibility(View.GONE);
                findViewById(R.id.hourly_pay_edit).setVisibility(View.GONE);
                findViewById(R.id.hourly_pay_txt).setVisibility(View.GONE);
            }
        });

//        nextPageButton();
        spinner();
    }


    private void nextPageButton() {
        Button nextButton = findViewById(R.id.btnNextP2);
//        nextButton.setOnClickListener(view -> startActivity(new Intent(SetupP1.this, SetupP2.class)));
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
               TextView test =  findViewById(R.id.txtPageTitle);
               test.setText(String.valueOf(sharedPreferences.getFloat("BankAccount", 0)));
            }
        });
        updatePrefs();
    }

    private void spinner(){
        Spinner spinnerLanguages=findViewById(R.id.list_payPeriods);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.pay_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }

    private void updatePrefs() {
        bankAccountInitialEditText = findViewById(R.id.current_cash_edit);
        bankAccountInitialInt = Float.parseFloat(bankAccountInitialEditText.getText().toString());

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putFloat("BankAccount", bankAccountInitialInt);
        editor.apply();
    }

}