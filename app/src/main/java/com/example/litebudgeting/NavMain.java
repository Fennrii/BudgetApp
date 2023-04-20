package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_main);
        setupButton();
    }

    private void setupButton(){
        Button setupButton = findViewById(R.id.btnSetup);
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavMain.this, SetupP1.class);
                startActivity(intent);
            }
        });
    }
}
