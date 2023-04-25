package com.example.litebudgeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.litebudgeting.ui.expenses.ExpensesFragment;

public class EditExpenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = sharedPref.edit();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        setupButton();
    }

    private void setupButton() {
        Button expenseButton = findViewById(R.id.btnUpdateExpense);
        expenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(EditExpenses.this, NavMain.class);
                startActivity(intent);
            }
        });
    }
}
