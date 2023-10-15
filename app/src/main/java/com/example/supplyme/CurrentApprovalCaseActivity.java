package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CurrentApprovalCaseActivity extends AppCompatActivity {

    public TextView nameTV;
    public TextView positionTV;
    public TextView emailTV;
    public TextView phoneTV;
    public TextView clusterTV;
    public TextView nswGovTV;

    public TextView dateRequiredTV;
    public TextView itemRequestedTV;
    public TextView quantityRequestedTV;
    public TextView stockNeedsTV;
    public TextView sizeSelectedTV;

    public TextView addressLineTV;
    public TextView agencyNameTV;
    public TextView suburbTV;
    public TextView sameLocationTV;
    public TextView sameAgencyTV;
    public TextView extraNotesTV;

    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;

    public FloatingActionButton rejectFAB;
    public FloatingActionButton approveFAB;







    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_approval_case);

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

        String documentId = i.getStringExtra("ID");

        //INCLUDE ID INTENT

        nameTV = findViewById(R.id.tvDateRequired2);
        positionTV = findViewById(R.id.tvDateRequired3);
        emailTV = findViewById(R.id.tvDateRequired4);
        phoneTV = findViewById(R.id.tvDateRequired5);
        clusterTV = findViewById(R.id.tvDateRequired6);
        nswGovTV = findViewById(R.id.tvDateRequired7);

        dateRequiredTV = findViewById(R.id.tvDateRequired);
        itemRequestedTV = findViewById(R.id.tvItemRequested);
        quantityRequestedTV = findViewById(R.id.tvQuantity);
        stockNeedsTV = findViewById(R.id.tvSupplier);
        sizeSelectedTV = findViewById(R.id.tvSizeSelected);

        addressLineTV = findViewById(R.id.tvItemRequested2);
        agencyNameTV = findViewById(R.id.tvSizeSelected2);
        suburbTV = findViewById(R.id.tvQuantity2);
        sameLocationTV = findViewById(R.id.tvQuantity3);
        sameAgencyTV = findViewById(R.id.tvQuantity4);
        extraNotesTV = findViewById(R.id.tvQuantity5);

        rejectFAB = findViewById(R.id.rejectFAB);
        approveFAB = findViewById(R.id.approveFAB);

        nameTV.setText(nameRequester);
        positionTV.setText(positionRequester);
        emailTV.setText(emailRequester);
        phoneTV.setText(mobileRequester);
        clusterTV.setText(cluster);
        nswGovTV.setText(governmentChoice);

        dateRequiredTV.setText(dateRequired);
        itemRequestedTV.setText(productItemSelection);
        quantityRequestedTV.setText(productQuantitySelection);
        stockNeedsTV.setText(stockNeeds);
        sizeSelectedTV.setText(size);

        addressLineTV.setText(addressLine1);
        agencyNameTV.setText(agencyName);
        suburbTV.setText(suburbTown);
        sameLocationTV.setText(locationChoice);
        sameAgencyTV.setText(agencyChoice);
        extraNotesTV.setText(extraNotesDetails);

        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
        String email = appUser.getEmail();

        if(email.equals("isabel.procureme@gmail.com") || email.equals("echie007@rfs.nsw.gov.au")) {

            rejectFAB.show();
            approveFAB.show();


        } else {

            rejectFAB.hide();
            approveFAB.hide();

        }

        if(approvalStatus1.equals("Application Accepted")) {

            approveFAB.setImageResource(R.drawable.ic_baseline_local_shipping_24);
            rejectFAB.hide();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                approveFAB.setTooltipText("Update Shipping Details");




            }

            //  nest if statement inside button approve

        }

        rejectFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context c = view.getContext();
                Intent i = new Intent(getApplicationContext(),AcceptRejectFinalActivity.class);


                i.putExtra("Status","Rejection");
                i.putExtra("Name", nameRequester);
                i.putExtra("Position", positionRequester);
                i.putExtra("Phone", mobileRequester);
                i.putExtra("Email", emailRequester);
                i.putExtra("Item", productItemSelection);
                i.putExtra("Quantity", productQuantitySelection);
                i.putExtra("Id",documentId);

                c.startActivity(i);



            }
        });

        approveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(approvalStatus1.equals("Application Accepted")) {

                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),AcceptRejectFinalActivity.class);

                    i.putExtra("Status","Delivery");
                    i.putExtra("Name", nameRequester);
                    i.putExtra("Position", positionRequester);
                    i.putExtra("Phone", mobileRequester);
                    i.putExtra("Email", emailRequester);
                    i.putExtra("Item", productItemSelection);
                    i.putExtra("Quantity", productQuantitySelection);
                    i.putExtra("Id",documentId);


                    c.startActivity(i);

                } else {

                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),AcceptRejectFinalActivity.class);

                    i.putExtra("Status","Acceptance");
                    i.putExtra("Name", nameRequester);
                    i.putExtra("Position", positionRequester);
                    i.putExtra("Phone", mobileRequester);
                    i.putExtra("Email", emailRequester);
                    i.putExtra("Item", productItemSelection);
                    i.putExtra("Quantity", productQuantitySelection);
                    i.putExtra("Id",documentId);


                    c.startActivity(i);


                }




            }
        });

        if(approvalStatus1.equals("Application Submitted") || approvalComment1.equals("Application Submitted")
                && email.equals("isabel.procureme@gmail.com")) {

            BackgroundMail.newBuilder(CurrentApprovalCaseActivity.this)
                    .withUsername("supplyme.updater@gmail.com")
                    .withPassword("infs3605t1")
                    .withMailto("corey.rfs@gmail.com")
                    .withType(BackgroundMail.TYPE_PLAIN)
                    .withSubject("Request Received")
                    .withBody("Hi Corey, I am now currently reviewing your request #2611. " +
                            "Please do not hesitate to message me if you have any questions. Warm Regards, Isabel Kim")
                    .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                        @Override
                        public void onSuccess() {

                            Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_LONG).show();
                            //do some magic
                        }
                    })
                    .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                        @Override
                        public void onFail() {

                            System.out.println("ISSUES EXIST CHECK LOG");

                            //do some magic
                        }
                    })
                    .send();

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDB = FirebaseFirestore.getInstance();
            currentUser = FirebaseAuth.getInstance().getCurrentUser();

            DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document(documentId);
            Map<String,Object> caseNoteHash = new HashMap<>();


            caseNoteHash.put("approvalStatus1","Reviewing Application");
            caseNoteHash.put("approvalComment1", "Reviewing Application");

            caseDB.update(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    Toast.makeText(getApplicationContext(), "Application Status Updated!", Toast.LENGTH_LONG).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getApplicationContext(), "Changes Unsuccessful", Toast.LENGTH_LONG).show();


                }
            });

        }














    }
}