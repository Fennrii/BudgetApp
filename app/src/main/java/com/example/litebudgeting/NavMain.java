package com.example.litebudgeting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.litebudgeting.databinding.ActivityNavMainBinding;

public class NavMain extends AppCompatActivity {

    private ActivityNavMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);

        boolean noForm = sharedPref.getBoolean(Keys.NO_FORM, true);

        if (noForm){
            setContentView(R.layout.activity_main);
            setupButton();
        }

        else {
            binding = ActivityNavMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            BottomNavigationView navView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_nav_main);
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        }



    }

    private void setupButton(){
        Button setupButton = findViewById(R.id.btnSetup);
        setupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavMain.this, SetupP1.class);
                startActivity(intent);
                SharedPreferences sharedPref = getSharedPreferences(Keys.PREFS_KEY, MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = sharedPref.edit();
                prefEdit.putBoolean(Keys.NO_FORM, false);
                prefEdit.apply();

            }
        });
    }

}