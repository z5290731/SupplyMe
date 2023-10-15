package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler delayHandler = new Handler();
        delayHandler.postDelayed(loginScreen, 100);
    }

    private Runnable loginScreen = new Runnable () {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(intent);

        }


    };


}

