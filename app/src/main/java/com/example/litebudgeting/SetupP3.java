package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetupP3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_p3);

        nextPageButton();
        previousPageButton();
    }

    private void nextPageButton() {
        Button nextButton = (Button) findViewById(R.id.btnNextP4);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButtonClicked();
            }
        });
    }

    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupP3.this, SetupP2.class));
            }
        });
    }

    private void nextButtonClicked(){

//      Update shared preferences
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

//      Get values to be saved
        EditText healthEdit, transportEdit, grocEdit, loanEdit;
        healthEdit = findViewById(R.id.Health_edit);
        transportEdit = findViewById(R.id.Transport_edit);
        grocEdit = findViewById(R.id.Groc_edit);
        loanEdit = findViewById(R.id.Loan_edit);

//      Convert values to floats. If the values are empty then put 0.0 instead
        float health, transport, groc, loan;
        try{ health = Float.parseFloat(healthEdit.getText().toString());}
        catch (Exception e){ health = 0F;}
        try{ transport = Float.parseFloat(transportEdit.getText().toString());}
        catch (Exception e){ transport = 0F;}
        try{ groc = Float.parseFloat(grocEdit.getText().toString());}
        catch (Exception e){ groc = 0F;}
        try{ loan = Float.parseFloat(loanEdit.getText().toString());}
        catch (Exception e){ loan = 0F;}


//      Put the floats into shared preferences
        prefEdit.putFloat(Keys.HEALTH, health);
        prefEdit.putFloat(Keys.TRANSPORT, transport);
        prefEdit.putFloat(Keys.GROCERIES, groc);
        prefEdit.putFloat(Keys.LOAN, loan);

        prefEdit.apply();

//      Go to next page
        startActivity(new Intent(SetupP3.this, FinalSetupPage.class));
    }
}