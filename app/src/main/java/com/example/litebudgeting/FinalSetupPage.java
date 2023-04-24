package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class FinalSetupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_setup_page);


        previousPageButton();
        submitFormButton();
//        showTest is temporary
        showTest();
    }


    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinalSetupPage.this, SetupP3.class));
            }
        });
    }

    private void submitFormButton() {
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinalSetupPage.this, NavMain.class));
            }
        });
    }

//    This is temporary delete if you use FinalSetupPage.java
    private void showTest(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        TextView tester = findViewById(R.id.showTest);
       Gson gson = new Gson();
    String json = sharedPref.getString("job1", "");
     Income income = gson.fromJson(json, Income.class);
     String test = String.valueOf(income.getPay());
//      String test = String.valueOf(sharedPref.getFloat(Keys.HOUSING,0f));
        tester.setText(test);
//        tester.setText(json);
    }



}