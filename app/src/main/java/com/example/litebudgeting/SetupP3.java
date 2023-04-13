package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity(new Intent(SetupP3.this, FinalSetupPage.class));
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

}