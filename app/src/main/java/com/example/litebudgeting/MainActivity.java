package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupButton();
    }

    private void setupButton(){
        Button setupButton = findViewById(R.id.btnSetup);
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetupP1.class);
                startActivity(intent);
                switchLayout(true);
            }
        });
    }

    public void switchLayout(boolean toMainForm) {
        if(toMainForm == true){
            setContentView(R.layout.activity_graph_main);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    private void testButton(){
        Button testButton = findViewById(R.id.test_buttonThing);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetupP1.class);
                startActivity(intent);
            }
        });
    }








}