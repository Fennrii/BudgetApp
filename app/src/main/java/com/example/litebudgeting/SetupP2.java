package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupP2 extends AppCompatActivity {
    EditText housingEditText;
    private int housingCostInt;
    EditText waterEditText;
    private int waterCostInt;
    EditText electritictyEditText;
    private int electritictyCostInt;
    EditText airConditioningEditText;
    private int airConditioningCostInt;
    EditText carInsuranceEditText;
    private int carInsuranceCostInt;

    SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_p2);
        nextPageButton();
        previousPageButton();
    }

    private void nextPageButton() {
        Button nextButton = (Button) findViewById(R.id.btnNextP3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupP2.this, FinalSetupPage.class));
            }
        });
        updatePrefs();
    }

    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupP2.this, SetupP1.class));
            }
        });
    }

    private void updatePrefs() {
        // Housing Cost
        housingEditText = findViewById(R.id.Housing_edit);
        housingCostInt = Integer.parseInt(housingEditText.getText().toString());

        // Water Cost
        waterEditText = findViewById(R.id.Water_edit);
        waterCostInt = Integer.parseInt(waterEditText.getText().toString());

        // Electricity Cost
        electritictyEditText = findViewById(R.id.Elec_edit);
        electritictyCostInt = Integer.parseInt(electritictyEditText.getText().toString());

        // Air Conditioning
        airConditioningEditText = findViewById(R.id.AC_edit);
        airConditioningCostInt = Integer.parseInt(airConditioningEditText.getText().toString());

        // Car Insurance
        carInsuranceEditText = findViewById(R.id.Car_edit);
        carInsuranceCostInt= Integer.parseInt(carInsuranceEditText.getText().toString());

        editor.putFloat("HousingCost", housingCostInt);
        editor.putFloat("WaterCost", waterCostInt);
        editor.putFloat("ElectricityCost", electritictyCostInt);
        editor.putFloat("AirConditioningCost", airConditioningCostInt);
        editor.putFloat("CarInsurance", carInsuranceCostInt);

        editor.apply();
    }

}