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
import android.widget.RelativeLayout;

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
        SharedPreferences sharedPref = getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        if (sharedPref.getBoolean(Keys.NO_FORM, true)==false){
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            radioGroup.setVisibility(View.GONE);
            Button Submit = findViewById(R.id.btnSubmit);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.setMargins(0,0,0,10);
            Submit.setLayoutParams(params);
            Submit.setVisibility(View.VISIBLE);
            findViewById(R.id.txtPageNumber4).setVisibility(View.GONE);
            findViewById(R.id.addSub_txt).setVisibility(View.GONE);
            findViewById(R.id.btnBackP3).setVisibility(View.GONE);

        }


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(yes.isChecked()) {
                findViewById(R.id.btnNextP5).setVisibility(View.VISIBLE);
                findViewById(R.id.btnSubmit).setVisibility(View.GONE);
            }
            else if(no.isChecked()) {
                findViewById(R.id.btnSubmit).setVisibility(View.VISIBLE);
                findViewById(R.id.btnNextP5).setVisibility(View.GONE);
            }
            else {
                findViewById(R.id.btnNextP5).setVisibility(View.GONE);
                findViewById(R.id.btnSubmit).setVisibility(View.GONE);
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
                updatePreferences();
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
                SharedPreferences sharedPref = getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = sharedPref.edit();
                prefEdit.putBoolean(Keys.NO_FORM, false);
                prefEdit.apply();
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
        if (!subStr.isEmpty() && !subNameStr.isEmpty()) {
            float subAmount;
            try {
                subAmount = Float.parseFloat(subStr);
            } catch (Exception e) {
                subAmount = 0F;
            }
            Subscription sub1 = new Subscription(subNameStr, subAmount);

            int subCount = sharedPref.getInt(Keys.SUB_COUNTER, 0);
            subCount++;
            Gson gson = new Gson();
            String json = gson.toJson(sub1);
            prefEdit.putString(Keys.SUB + subCount, json);
            prefEdit.putInt(Keys.SUB_COUNTER, subCount);
            prefEdit.apply();
            Log.d("addSubsToScroll", "numOfSubs = " + sharedPref.getInt(Keys.SUB_COUNTER, 0));
        }
    }
}
