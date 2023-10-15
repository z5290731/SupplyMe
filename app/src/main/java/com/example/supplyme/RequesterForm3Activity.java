package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RequesterForm3Activity extends AppCompatActivity {

    private EditText agencyName;
    private RadioGroup governmentYN;
    private EditText companyName;
    private EditText addressLine1;
    private EditText addressLine2;
    private EditText suburbTown;
    private Button finishButton;
    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_form3);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseFirestore.getInstance();

        agencyName = findViewById(R.id.editTextAgency);
        governmentYN = findViewById(R.id.governmentGroup);
        companyName = findViewById(R.id.editTextCompanyName);
        addressLine1 = findViewById(R.id.editTextAddress1);
        addressLine2 = findViewById(R.id.editTextAddress2);
        suburbTown = findViewById(R.id.editTextSuburb);
        finishButton = findViewById(R.id.finishButton);


        Intent i = getIntent();

        //Acquiring all the data from previous pages

        String dateRequiredE = i.getStringExtra("dateRequired");
        String stockNeedsE = i.getStringExtra("stockNeedsE");
        String clusterE = i.getStringExtra("clusterE");
        String nameRequesterE = i.getStringExtra("nameRequesterE");
        String positionRequesterE = i.getStringExtra("positionRequesterE");
        String mobileRequesterEString = i.getStringExtra("mobileRequesterEString");
        String agencyChoice = i.getStringExtra("agencyYN");
        String locationChoice = i.getStringExtra("locationYN");
        String productTypeSelection = i.getStringExtra("type");
        String productItemSelection = i.getStringExtra("item");
        String productQuantitySelection = i.getStringExtra("quantity");
        String emailRequesterE = i.getStringExtra("emailRequesterE");
        String size = i.getStringExtra("size");

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioGovernment = governmentYN.getCheckedRadioButtonId();

                RadioButton governmentRadioButton = (RadioButton) governmentYN.findViewById(radioGovernment);

                String governmentChoice = governmentRadioButton.getText().toString();
                String agencyNameE = agencyName.getText().toString();
                String companyNameE = companyName.getText().toString();
                String addressLineE1 = addressLine1.getText().toString();
                String addressLineE2 = addressLine2.getText().toString();
                String suburbTownE = suburbTown.getText().toString();
                String approvalUser = "Pending User Allocation";
                String approvalStatus1 = "Application Submitted";
                String approvalComment1 = "Application Submitted";
                String trackingCompanyInformation = "Pending User Allocation";
                String trackingCompanyNumber = "Pending User Allocation";
                String expectedDeliveryDate = "Pending";


                if(governmentChoice.isEmpty() || agencyNameE.isEmpty() || companyNameE.isEmpty() || addressLineE1.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill in both the Title and Body of the Mission Note :)", Toast.LENGTH_LONG).show();

                } else if(addressLineE2.isEmpty()) {
                    //SUBMIT FIREBASE QUERY WITHOUT ADDRESS LINE E2
                } else {

                    //SUBMIT FIREBASE QUERY WITH ADDRESS LINE E2

                    try {

                        DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document();
                        Map<String,Object> caseNoteHash = new HashMap<>();

                        caseNoteHash.put("dateRequired",dateRequiredE);
                        caseNoteHash.put("stockNeeds",stockNeedsE);
                        caseNoteHash.put("cluster",clusterE);
                        caseNoteHash.put("nameRequester",nameRequesterE);
                        caseNoteHash.put("positionRequester",positionRequesterE);
                        caseNoteHash.put("mobileRequester",mobileRequesterEString);
                        caseNoteHash.put("emailRequester",emailRequesterE);

                        caseNoteHash.put("agencyChoice", agencyChoice);
                        caseNoteHash.put("locationChoice", locationChoice);
                        caseNoteHash.put("productTypeSelection", productTypeSelection);
                        caseNoteHash.put("productItemSelection", productItemSelection);
                        caseNoteHash.put("productQuantitySelection", productQuantitySelection);
                        caseNoteHash.put("size", size);

                        caseNoteHash.put("agencyName", agencyNameE);
                        caseNoteHash.put("governmentChoice", governmentChoice);
                        caseNoteHash.put("companyName", companyNameE);
                        caseNoteHash.put("addressLine1", addressLineE1);
                        caseNoteHash.put("addressLine2", addressLineE2);
                        caseNoteHash.put("suburbTown", suburbTownE);

                        caseNoteHash.put("approvalUser", approvalUser);
                        caseNoteHash.put("approvalStatus1", approvalStatus1);
                        caseNoteHash.put("approvalComment1", approvalComment1);
                        caseNoteHash.put("trackingCompanyInformation", trackingCompanyInformation);
                        caseNoteHash.put("trackingCompanyNumber",trackingCompanyNumber);
                        caseNoteHash.put("deliveryDate",expectedDeliveryDate);

                        caseDB.set(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(getApplicationContext(), "Mission Note Created!", Toast.LENGTH_LONG).show();

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

                    System.out.println(dateRequiredE
                            + stockNeedsE
                            + clusterE
                            + nameRequesterE
                            + positionRequesterE
                            + mobileRequesterEString
                            + agencyChoice
                            + locationChoice
                            + productTypeSelection
                            + productItemSelection
                            + productQuantitySelection
                            + emailRequesterE
                            + size
                            + approvalUser
                            + governmentChoice
                            + agencyNameE
                            + companyNameE
                            + addressLineE1
                            + addressLineE2
                            + approvalStatus1
                            + approvalComment1 + " THIS IS THE EXPECTED OUTPUT" );


                    System.out.println("Thank you for submitting a request");

                    BackgroundMail.newBuilder(RequesterForm3Activity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi" + " Corey" + "we have received your request for " + productItemSelection + " with a quantity of " + productQuantitySelection)
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

                    BackgroundMail.newBuilder(RequesterForm3Activity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("isabel.procureme@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi" + "Isabel" + "you have received a request for " + productItemSelection + " with a quantity of " + productQuantitySelection)
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



                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),DetailActivityRequester.class);

                    c.startActivity(i);





                }

;

            }
        });














    }
}