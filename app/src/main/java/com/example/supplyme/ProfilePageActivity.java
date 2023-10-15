package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ProfilePageActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    public TextView textView33;
    public TextView textView35;
    public TextView textView37;
    public TextView textView41;
    public TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Objects.requireNonNull(getSupportActionBar()).hide();

        textView33 = findViewById(R.id.textView33);
        textView35 = findViewById(R.id.textView35);
        textView37 = findViewById(R.id.textView37);
        textView41 = findViewById(R.id.textView41);
        textView = findViewById(R.id.textView);

        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
        String nameFirebase = appUser.getDisplayName();
        String emailFirebase = appUser.getEmail();

        textView33.setText(nameFirebase);
        textView.setText(nameFirebase);
        textView37.setText(emailFirebase);

        if(emailFirebase.equals("isabel.procureme@gmail.com") || emailFirebase.equals("echie007@rfs.nsw.gov.au")) {
            textView35.setText("Procurement Manager");
        } else {
            textView35.setText("RFS Volunteer");
            textView41.setText("South West Sydney");

        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // ***NAVIGATION BAR

        // Set current selected item
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Set up select listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), chatActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.map:
                            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                            overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.requests:
                        startActivity(new Intent(getApplicationContext(), CurrentCaseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
        // NAVIGATION BAR***




        /**
         * TO DO
         * PROGRAMATIC PAGE
         * EDIT TEXTVIEW
         * DATABASE APPLICATION TO SAVE DATA
         * DATABASE MODEL
         * BUTTON FUNCTIONALITY
         */
    }
}