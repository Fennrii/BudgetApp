package com.example.litebudgeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class Subs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subs);

        RadioButton yes = findViewById(R.id.addSub_yes);
        RadioButton no = findViewById(R.id.addSub_no);

        RadioGroup radioGroup = findViewById(R.id.addSub_edit);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(yes.isChecked()) {
                findViewById(R.id.btnNextP5).setVisibility(View.VISIBLE);
                findViewById(R.id.btnSubmit).setVisibility(View.INVISIBLE);
            }
            else if(no.isChecked()) {
                findViewById(R.id.btnSubmit).setVisibility(View.VISIBLE);
                findViewById(R.id.btnNextP5).setVisibility(View.INVISIBLE);
            }
            else {
                findViewById(R.id.btnNextP5).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnSubmit).setVisibility(View.INVISIBLE);
            }
        });

        nextPageButton();
        previousPageButton();
        submitFormButton();
    }
    private void nextPageButton() {
        Button nextButton = (Button) findViewById(R.id.btnNextP5);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Subs.this, Subs.class));

            }
        });
    }

    private void submitFormButton(){
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting up Shared Prefs
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = sharedPref.edit();

                //Initiating Object
                AddSubs sub1;

                EditText subName = findViewById(R.id.subName_edit); //change Id
                String subNameStr = subName.toString();
                EditText subEdit = findViewById(R.id.subCost_edit); //Change Id
                String subStr = subEdit.getText().toString();
                float subDollar;
                try{subDollar = Float.parseFloat(subStr);}
                catch(Exception e){
                    subDollar = 0F;
                }
                Log.d("DebugSubName", "Value: " + subNameStr);
                Log.d("DebugSubCost", "Value: " + subStr);

                sub1 = new AddSubs(subNameStr, subDollar);

                int subCount = sharedPref.getInt(Keys.SUB_COUNTER, 1);
                subCount++;
                Gson gson = new Gson();
                String json1 = gson.toJson(sub1);
                prefEdit.putString(Keys.SUB+subCount, json1);
                prefEdit.apply();
                Intent intent = new Intent(Subs.this, NavMain.class);
                startActivity(intent);
            }
        });
    }


    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Subs.this, SetupP3.class));
            }
        });
    }
}
