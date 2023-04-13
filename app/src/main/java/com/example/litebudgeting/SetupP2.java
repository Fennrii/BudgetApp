package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                startActivity(new Intent(SetupP2.this, SetupP3.class));
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

}