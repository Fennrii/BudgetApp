package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    private void submitFormButton() {
        Button submitButton = findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Subs.this, NavMain.class));
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
