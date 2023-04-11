package com.example.litebudgeting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SetupP1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_p1);

        nextPageButton();
        spinner();
    }

    private void nextPageButton() {
        Button nextButton = (Button) findViewById(R.id.btnNextP2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetupP1.this, SetupP2.class));
            }
        });
    }

    private void spinner(){
        Spinner spinnerLanguages=findViewById(R.id.list_payPeriods);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.pay_periods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }


}