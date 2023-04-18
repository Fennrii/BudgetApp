package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinalSetupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_setup_page);


        previousPageButton();
        submitFormButton();
    }


    private void previousPageButton() {
        Button backButton = (Button) findViewById(R.id.btnBackP2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinalSetupPage.this, SetupP2.class));
            }
        });
    }

    private void submitFormButton() {
        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchLayout(true);
            }
        });
    }


    public void switchLayout(boolean isSecondLayout) {
        if(isSecondLayout){
            setContentView(R.layout.activity_graph_main);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

}