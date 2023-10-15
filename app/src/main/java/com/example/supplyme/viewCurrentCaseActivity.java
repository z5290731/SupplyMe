package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class viewCurrentCaseActivity extends AppCompatActivity {

    private Button button9;
    private Button button10;
    private ImageButton imageButton;
    private TextView tvDateRequired;
    private TextView tvItemRequested;
    private TextView tvQuantity;
    private TextView tvSupplier;
    private TextView tvSizeSelected;
    private Button button14;

    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_current_case);

        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent i = getIntent();



        String dateRequired = i.getStringExtra("dateRequired");
        String stockNeeds = i.getStringExtra("stockNeeds");
        String cluster = i.getStringExtra("cluster");

        String nameRequester = i.getStringExtra("nameRequester");

        String positionRequester = i.getStringExtra("positionRequester");
        String mobileRequester = i.getStringExtra("mobileRequester");
        String emailRequester = i.getStringExtra("emailRequester");

        String agencyChoice = i.getStringExtra("agencyChoice");
        String locationChoice = i.getStringExtra("locationChoice");
        String productTypeSelection = i.getStringExtra("productTypeSelection");
        String productItemSelection = i.getStringExtra("productItemSelection");
        String productQuantitySelection = i.getStringExtra("productQuantitySelection");
        String size = i.getStringExtra("size");

        String agencyName = i.getStringExtra("agencyName");
        String governmentChoice = i.getStringExtra("governmentChoice");
        //Deleted i.getStringExtra("companyName", companyNameE);
        String addressLine1 = i.getStringExtra("addressLine1");
        //Deleted i.getStringExtra("addressLine2", addressLineE2);
        String suburbTown = i.getStringExtra("suburbTown");

        String extraNotesDetails = i.getStringExtra("extraNotesDetails");

        String approvalUser = i.getStringExtra("approvalUser");
        String approvalStatus1 = i.getStringExtra("approvalStatus1");
        String approvalComment1 = i.getStringExtra("approvalComment1");
        String trackingCompanyInformation = i.getStringExtra("trackingCompanyInformation");
        String trackingCompanyNumber = i.getStringExtra("trackingCompanyNumber");
        String deliveryDate = i.getStringExtra("deliveryDate");

        String itemDescription2 = i.getStringExtra("itemDescription2");
        String itemSupplier2 = i.getStringExtra("itemSupplier2");
        String itemContactSupplier2 = i.getStringExtra("itemContactSupplier2");

        String id = i.getStringExtra("ID");

        tvDateRequired = findViewById(R.id.tvDateRequired);
        tvItemRequested = findViewById(R.id.tvItemRequested);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvSupplier = findViewById(R.id.tvSupplier);
        tvSizeSelected = findViewById(R.id.tvSizeSelected);

        tvDateRequired.setText(dateRequired);
        tvItemRequested.setText(productItemSelection);
        tvQuantity.setText(productQuantitySelection);
        tvSupplier.setText(itemSupplier2);
        tvSizeSelected.setText(size);

        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button14 = findViewById(R.id.button14);

        imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context c = view.getContext();
                Intent i = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                c.startActivity(i);
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDB = FirebaseFirestore.getInstance();

                Intent i = getIntent();

                String dateRequired = i.getStringExtra("dateRequired");
                String stockNeeds = i.getStringExtra("stockNeeds");
                String cluster = i.getStringExtra("cluster");

                String nameRequester = i.getStringExtra("nameRequester");

                String positionRequester = i.getStringExtra("positionRequester");
                String mobileRequester = i.getStringExtra("mobileRequester");
                String emailRequester = i.getStringExtra("emailRequester");

                String agencyChoice = i.getStringExtra("agencyChoice");
                String locationChoice = i.getStringExtra("locationChoice");
                String productTypeSelection = i.getStringExtra("productTypeSelection");
                String productItemSelection = i.getStringExtra("productItemSelection");
                String productQuantitySelection = i.getStringExtra("productQuantitySelection");
                String size = i.getStringExtra("size");

                String agencyName = i.getStringExtra("agencyName");
                String governmentChoice = i.getStringExtra("governmentChoice");
                //Deleted i.getStringExtra("companyName", companyNameE);
                String addressLine1 = i.getStringExtra("addressLine1");
                //Deleted i.getStringExtra("addressLine2", addressLineE2);
                String suburbTown = i.getStringExtra("suburbTown");

                String extraNotesDetails = i.getStringExtra("extraNotesDetails");

                String approvalUser = i.getStringExtra("approvalUser");
                String approvalStatus1 = i.getStringExtra("approvalStatus1");
                String approvalComment1 = i.getStringExtra("approvalComment1");

                String itemDescription2 = i.getStringExtra("itemDescription2");
                String itemSupplier2 = i.getStringExtra("itemSupplier2");
                String itemContactSupplier2 = i.getStringExtra("itemContactSupplier2");

                String id = i.getStringExtra("ID");

                DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document();
                Map<String,Object> caseNoteHash = new HashMap<>();

                caseNoteHash.put("dateRequired",dateRequired);
                caseNoteHash.put("stockNeeds",stockNeeds);
                caseNoteHash.put("cluster",cluster);
                caseNoteHash.put("nameRequester",nameRequester);
                caseNoteHash.put("positionRequester",positionRequester);
                caseNoteHash.put("mobileRequester",mobileRequester);
                caseNoteHash.put("emailRequester",emailRequester);

                caseNoteHash.put("agencyChoice", agencyChoice);
                caseNoteHash.put("locationChoice", locationChoice);
                caseNoteHash.put("productTypeSelection", productTypeSelection);
                caseNoteHash.put("productItemSelection", productItemSelection);
                caseNoteHash.put("productQuantitySelection", productQuantitySelection);
                caseNoteHash.put("size", size);

                caseNoteHash.put("agencyName", agencyName);
                caseNoteHash.put("governmentChoice", governmentChoice);
                //Deleted caseNoteHash.put("companyName", companyNameE);
                caseNoteHash.put("addressLine1", addressLine1);
                //Deleted caseNoteHash.put("addressLine2", addressLineE2);
                caseNoteHash.put("suburbTown", suburbTown);

                caseNoteHash.put("extraNotesDetails", extraNotesDetails);

                String trackingCompanyInformation = "Pending User Allocation";
                String trackingCompanyNumber = "Pending User Allocation";
                String expectedDeliveryDate = "Pending";

                caseNoteHash.put("approvalUser", approvalUser);
                caseNoteHash.put("approvalStatus1", "Application Submitted");
                caseNoteHash.put("approvalComment1", "Application Submitted");
                caseNoteHash.put("trackingCompanyInformation", trackingCompanyInformation);
                caseNoteHash.put("trackingCompanyNumber",trackingCompanyNumber);
                caseNoteHash.put("deliveryDate",expectedDeliveryDate);

                caseNoteHash.put("itemDescription2", itemDescription2);
                caseNoteHash.put("itemSupplier2", itemSupplier2);
                caseNoteHash.put("itemContactSupplier2", itemContactSupplier2);

                caseDB.set(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(getApplicationContext(), "Request Duplicated", Toast.LENGTH_LONG).show();

                    }
                }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {

                        Toast.makeText(getApplicationContext(), "Request Cancelled", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(), "Request Failed", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context c = view.getContext();
                Intent i = new Intent(getApplicationContext(),CurrentApprovalCaseActivity.class);

                i.putExtra("dateRequired",dateRequired);
                i.putExtra("stockNeeds", stockNeeds);
                i.putExtra("cluster",cluster);
                i.putExtra("nameRequester",nameRequester);
                i.putExtra("positionRequester",positionRequester);
                i.putExtra("mobileRequester",mobileRequester);
                i.putExtra("emailRequester",emailRequester);

                i.putExtra("agencyChoice", agencyChoice);
                i.putExtra("locationChoice", locationChoice);
                i.putExtra("productTypeSelection", productTypeSelection);
                i.putExtra("productItemSelection", productItemSelection);
                i.putExtra("productQuantitySelection", productQuantitySelection);
                i.putExtra("size", size);

                i.putExtra("agencyName", agencyName);
                i.putExtra("governmentChoice", governmentChoice);
                //Deleted i.putExtra("companyName", companyNameE);
                i.putExtra("addressLine1", addressLine1);
                //Deleted i.putExtra("addressLine2", addressLineE2);
                i.putExtra("suburbTown", suburbTown);

                i.putExtra("extraNotesDetails", extraNotesDetails);

                i.putExtra("approvalUser", approvalUser);
                i.putExtra("approvalStatus1", approvalStatus1);
                i.putExtra("approvalComment1", approvalComment1);
                i.putExtra("trackingCompanyInformation", trackingCompanyInformation);
                i.putExtra("trackingCompanyNumber",trackingCompanyNumber);
                i.putExtra("deliveryDate",deliveryDate);

                i.putExtra("itemDescription2", itemDescription2);
                i.putExtra("itemSupplier2", itemSupplier2);
                i.putExtra("itemContactSupplier2", itemContactSupplier2);

                i.putExtra("ID",id);


                c.startActivity(i);

            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context c = view.getContext();
                Intent i = new Intent(getApplicationContext(),CurrentApprovalCaseActivity.class);

                i.putExtra("dateRequired",dateRequired);
                i.putExtra("stockNeeds", stockNeeds);
                i.putExtra("cluster",cluster);
                i.putExtra("nameRequester",nameRequester);
                i.putExtra("positionRequester",positionRequester);
                i.putExtra("mobileRequester",mobileRequester);
                i.putExtra("emailRequester",emailRequester);

                i.putExtra("agencyChoice", agencyChoice);
                i.putExtra("locationChoice", locationChoice);
                i.putExtra("productTypeSelection", productTypeSelection);
                i.putExtra("productItemSelection", productItemSelection);
                i.putExtra("productQuantitySelection", productQuantitySelection);
                i.putExtra("size", size);

                i.putExtra("agencyName", agencyName);
                i.putExtra("governmentChoice", governmentChoice);
                //Deleted i.putExtra("companyName", companyNameE);
                i.putExtra("addressLine1", addressLine1);
                //Deleted i.putExtra("addressLine2", addressLineE2);
                i.putExtra("suburbTown", suburbTown);

                i.putExtra("extraNotesDetails", extraNotesDetails);

                i.putExtra("approvalUser", approvalUser);
                i.putExtra("approvalStatus1", approvalStatus1);
                i.putExtra("approvalComment1", approvalComment1);
                i.putExtra("trackingCompanyInformation", trackingCompanyInformation);
                i.putExtra("trackingCompanyNumber",trackingCompanyNumber);
                i.putExtra("deliveryDate",deliveryDate);

                i.putExtra("itemDescription2", itemDescription2);
                i.putExtra("itemSupplier2", itemSupplier2);
                i.putExtra("itemContactSupplier2", itemContactSupplier2);

                i.putExtra("ID",id);


                c.startActivity(i);

            }
        });







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,CurrentCaseActivity.class));
    }
}