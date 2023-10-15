package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Objects;

public class RequesterDetailsActivity extends AppCompatActivity {

    public TextView name;
    public TextView position;
    public TextView emailAddress;
    public TextView phoneNumber;
    public TextView clusterArea;
    public EditText dateRequired;
    public RadioGroup stockNeeds;
    public RadioGroup nswGov;
    public ImageButton Edit;
    public ImageButton back;
    public ImageButton next;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_details);

        name = findViewById(R.id.textView23);
        position = findViewById(R.id.textView24);
        emailAddress = findViewById(R.id.textView25);
        phoneNumber = findViewById(R.id.textView26);
        clusterArea = findViewById(R.id.textView27);

        stockNeeds = findViewById(R.id.radioGroup2);
        nswGov = findViewById(R.id.radioGroupGov);

        Edit = findViewById(R.id.imageButton6);
        back = findViewById(R.id.imageButton4);
        next = findViewById(R.id.imageButton5);

        dateRequired = findViewById(R.id.editTextDate3);

        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
        String nameFirebase = appUser.getDisplayName();
        String emailFirebase = appUser.getEmail();

        name.setText(nameFirebase);
        emailAddress.setText(emailFirebase);

        if(emailFirebase.equals("isabel.procureme@gmail.com") || emailFirebase.equals("echie007@rfs.nsw.gov.au")) {
            position.setText("Procurement Manager");
        } else {
            position.setText("RFS Volunteer");
            clusterArea.setText("South West Sydney Cluster");

        }

        // TO-DO
        // SET UP EDIT FUNCTIONALITY FOR PERSONAL DETAILS
        // LINK TO DATABASE ENTRY TO SET TEXT ON PERSONAL DETAILS
        // BACK AND CANCEL BUTTON FUNCTIONALITY


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer stockChoice = stockNeeds.getCheckedRadioButtonId();
                Integer govChoice = nswGov.getCheckedRadioButtonId();

                System.out.println("hi there " + stockChoice);

                RadioButton stockButton = (RadioButton) stockNeeds.findViewById(stockChoice);
                RadioButton govButton = (RadioButton) nswGov.findViewById(govChoice);


                String dateChosen = dateRequired.getText().toString();





                String clusterE = clusterArea.getText().toString();
                String nameRequesterE = name.getText().toString();
                String positionRequesterE = position.getText().toString();
                String mobileRequesterEString = phoneNumber.getText().toString();
                String emailRequesterE = emailAddress.getText().toString();

                if(dateChosen.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill in the date required!", Toast.LENGTH_LONG).show();

                } else if(stockChoice == -1) {

                    Toast.makeText(getApplicationContext(), "Please fill in the stock needs!", Toast.LENGTH_LONG).show();

                } else if(govChoice == -1) {
                    Toast.makeText(getApplicationContext(), "Please select whether the goods are for the NSW Government!", Toast.LENGTH_LONG).show();
                } else {

                    String stockSelection = stockButton.getText().toString();
                    String govSelection = govButton.getText().toString();


                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),RequesterItemActivity.class);
                    i.putExtra("dateRequired", dateChosen);
                    i.putExtra("stockNeedsE", stockSelection);
                    i.putExtra("clusterE", clusterE );
                    i.putExtra("nameRequesterE", nameRequesterE);
                    i.putExtra("positionRequesterE", positionRequesterE);
                    i.putExtra("emailRequesterE", emailRequesterE);
                    i.putExtra("mobileRequesterEString", mobileRequesterEString);
                    i.putExtra("agencyYN", govSelection);

                    c.startActivity(i);


                }


            }
        });





    }
}