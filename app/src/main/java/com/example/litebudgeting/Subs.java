package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subs);

        nextPageButton();
        previousPageButton();
    }

    private void nextPageButton() {
        Button nextButton = (Button) findViewById(R.id.btnNextP5);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Subs.this, FinalSetupPage.class));
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
