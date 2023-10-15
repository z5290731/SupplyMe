package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RequesterForm1Activity extends AppCompatActivity {

    private EditText dateRequired;
    private Spinner stockNeeds;
    private EditText cluster;
    private EditText nameRequester;
    private EditText positionRequester;
    private EditText emailRequester;
    private EditText mobileRequester;
    private Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_form1);

        dateRequired = findViewById(R.id.editTextDate);
        stockNeeds = findViewById(R.id.stockneedSpinner);
        cluster = findViewById(R.id.editTextCluster);
        nameRequester = findViewById(R.id.editTextName);
        positionRequester = findViewById(R.id.editTextPosition);
        emailRequester = findViewById(R.id.editTextEmail);
        mobileRequester = findViewById(R.id.editTextPhone2);

        nextPage = findViewById(R.id.button2);

        String[] stockNeedArray = new String[] {"High", "Medium", "Low"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,stockNeedArray);

        stockNeeds.setAdapter(adapter);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateRequiredE = dateRequired.getText().toString();
                String stockNeedsE = stockNeeds.getSelectedItem().toString();
                String clusterE = cluster.getText().toString();
                String nameRequesterE = nameRequester.getText().toString();
                String positionRequesterE = positionRequester.getText().toString();
                String mobileRequesterEString = mobileRequester.getText().toString();
                String emailRequesterE = emailRequester.getText().toString();

                //int mobileInt = Integer.parseInt(mobileRequesterEString);

                if (dateRequiredE.isEmpty() || stockNeedsE.isEmpty() || clusterE.isEmpty() || nameRequesterE.isEmpty() || positionRequesterE.isEmpty() || mobileRequesterEString.isEmpty() ) {

                    Toast.makeText(getApplicationContext(), "Please fill in both the Title and Body of the Mission Note :)", Toast.LENGTH_LONG).show();
                } else {

                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),RequesterForm2Activity.class);
                    i.putExtra("dateRequired", dateRequiredE);
                    i.putExtra("stockNeedsE", stockNeedsE);
                    i.putExtra("clusterE", clusterE );
                    i.putExtra("nameRequesterE", nameRequesterE);
                    i.putExtra("positionRequesterE", positionRequesterE);
                    i.putExtra("emailRequesterE", emailRequesterE);
                    i.putExtra("mobileRequesterEString", mobileRequesterEString);

                    System.out.println(nameRequesterE);

                    c.startActivity(i);

                }






            }
        });





    }
}