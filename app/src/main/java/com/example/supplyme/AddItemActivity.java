package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddItemActivity extends AppCompatActivity {

    public EditText itemNameET;
    public EditText itemQuantityET;
    public EditText itemDescriptionET;
    public Spinner spinner;
    public Button button4;
    public BottomNavigationView bottomNavigationView;

    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Objects.requireNonNull(getSupportActionBar()).hide();

        itemNameET = findViewById(R.id.itemNameET);
        itemQuantityET = findViewById(R.id.itemQuantityET);
        itemDescriptionET = findViewById(R.id.itemDescriptionET);
        spinner = findViewById(R.id.spinner);
        button4 = findViewById(R.id.button4);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseFirestore.getInstance();


        String[] productSizeArray= new String[] {"Hand Sanitiser", "Examination Gloves", "Disinfectant / Cleaning Products", "Handwash/Soap", "Masks", "Eyewear", "Gowns/Overalls", "Paper Products", "Other"};

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,productSizeArray);

        spinner.setAdapter(sizeAdapter);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemNameETS = itemNameET.getText().toString();
                String itemQuantityETS = itemQuantityET.getText().toString();
                String itemDescriptionETS = itemDescriptionET.getText().toString();
                String itemTypeETS = spinner.getSelectedItem().toString();
                String itemSupplier = "ProcureMe";
                String itemContactSupplier = "Isabel Kim";

                if(itemNameETS.isEmpty() || itemQuantityETS.isEmpty() || itemDescriptionETS.isEmpty() || itemTypeETS.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill in all Required Details", Toast.LENGTH_LONG).show();

                } else {

                    try {

                        DocumentReference caseDB = firebaseDB.collection("items").document("itemDocuments").collection("uniqueItems").document();
                        Map<String,Object> caseNoteHash = new HashMap<>();

                        caseNoteHash.put("itemName",itemNameETS);
                        caseNoteHash.put("itemQuantity",itemQuantityETS);
                        caseNoteHash.put("itemType", itemTypeETS);
                        caseNoteHash.put("itemDescription" , itemDescriptionETS);
                        caseNoteHash.put("itemSupplier", itemSupplier);
                        caseNoteHash.put("itemContactSupplier", itemContactSupplier);

                        caseDB.set(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(getApplicationContext(), "Mission Note Created!", Toast.LENGTH_LONG).show();

                                Context c = view.getContext();
                                Intent i = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                                c.startActivity(i);



                                //ADD BUTTON TO TAKE BACK TO HOME

                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {

                                Toast.makeText(getApplicationContext(), "Mission Note Creation Cancelled!", Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(), "Mission Note Creation Unsuccessful!", Toast.LENGTH_LONG).show();

                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }





                }

            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // ***NAVIGATION BAR

        // Set current selected item
        bottomNavigationView.setSelectedItemId(R.id.requests);

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
                        startActivity(new Intent(getApplicationContext(), ProfilePageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        return true;
                }

                return false;
            }
        });
        // NAVIGATION BAR***
    }
}