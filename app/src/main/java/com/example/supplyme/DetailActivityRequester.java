package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetailActivityRequester extends AppCompatActivity {

    private Button button3;
    private Button checkCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_requester);

        button3 = findViewById(R.id.button3);
        checkCase = findViewById(R.id.checkCase);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivityRequester.this,RequesterForm1Activity.class));

            }
        });

        checkCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailActivityRequester.this,CurrentCaseActivity.class));
            }
        });





    }
}