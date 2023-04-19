package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupP2 extends AppCompatActivity {


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
                nextButtonClicked();
            }
        });

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

    private void nextButtonClicked(){

//      Update shared preferences
        SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

//      Get values to be saved
        EditText housingEdit, waterEdit, elecEdit, acEdit, carEdit;
        housingEdit = findViewById(R.id.Housing_edit);
        waterEdit = findViewById(R.id.Water_edit);
        elecEdit = findViewById(R.id.Elec_edit);
        acEdit = findViewById(R.id.AC_edit);
        carEdit = findViewById(R.id.Car_edit);

//      Convert values to floats. If the values are empty then put 0.0 instead
        float housing, water, elec, ac, car;
        try{ housing = Float.parseFloat(housingEdit.toString());}
        catch (Exception e){ housing = 0F;}
        try{ water = Float.parseFloat(waterEdit.toString());}
        catch (Exception e){ water = 0F;}
        try{ elec = Float.parseFloat(elecEdit.toString());}
        catch (Exception e){ elec = 0F;}
        try{ ac = Float.parseFloat(acEdit.toString());}
        catch (Exception e){ ac = 0F;}
        try{ car = Float.parseFloat(carEdit.toString());}
        catch (Exception e){ car = 0F;}

//      Put the floats into shared preferences
        prefEdit.putFloat("Housing", housing);
        prefEdit.putFloat("Water", water);
        prefEdit.putFloat("Elec", elec);
        prefEdit.putFloat("AC", ac);
        prefEdit.putFloat("Car", car);

        prefEdit.apply();

//      Go to next page
        startActivity(new Intent(SetupP2.this, SetupP3.class));
    }


}