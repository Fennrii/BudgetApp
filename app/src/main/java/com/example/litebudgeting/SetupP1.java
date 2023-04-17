package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SetupP1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_p1);

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

        nextPageButton();
        spinner();
    }

    private void nextPageButton() {
        Button nextButton = findViewById(R.id.btnNextP2);
        nextButton.setOnClickListener(view -> startActivity(new Intent(SetupP1.this, SetupP2.class)));
    }

    private void spinner(){
        Spinner spinnerLanguages=findViewById(R.id.list_payPeriods);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.pay_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }


}