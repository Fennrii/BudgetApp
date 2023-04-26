package com.example.litebudgeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class AddSub extends AppCompatActivity {
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
                startActivity(new Intent(AddSub.this, AddSub.class));
            }
        });
    }

    private void submitFormButton() {
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSub.this, NavMain.class));
                updatePreferences();
            }
        });
    }

    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSub.this, SetupP3.class));
                updatePreferences();
            }
        });
    }

    private void updatePreferences() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();
        EditText subName = findViewById(R.id.subName_edit); //change Id
        String subNameStr = subName.getText().toString();
        EditText subEdit = findViewById(R.id.subCost_edit); //Change Id
        String subStr = subEdit.getText().toString();
        float subAmount;
        try{subAmount = Float.parseFloat(subStr);}
        catch(Exception e){
            subAmount = 0F;
        }
        Subscription sub1 = new Subscription(subNameStr, subAmount);

        int subCount = sharedPref.getInt(Keys.SUB_COUNTER, 0);
        subCount += 1;
        Gson gson = new Gson();
        String json = gson.toJson(sub1);
        prefEdit.putString(Keys.SUB+subCount, json);
        prefEdit.putInt(Keys.SUB_COUNTER,subCount);
        prefEdit.apply();
    }
}
