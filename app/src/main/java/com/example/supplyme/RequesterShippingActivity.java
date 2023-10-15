package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.Objects;

public class RequesterShippingActivity extends AppCompatActivity {

    public EditText editTextAgencyName;
    public EditText editTextAddressLine;
    public EditText editTextSuburbTown;
    public RadioGroup radioGroup2Location;
    public RadioGroup radioGroup3Agency;
    public EditText editTextNotes;

    public ImageButton imageButton4;
    public ImageButton imageButton5;

    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_shipping);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseFirestore.getInstance();



        editTextAgencyName = findViewById(R.id.editTextAgencyName);
        editTextAddressLine = findViewById(R.id.editTextAddressLine);
        editTextSuburbTown = findViewById(R.id.editTextSuburbTown);
        radioGroup2Location = findViewById(R.id.radioGroup2);
        radioGroup3Agency = findViewById(R.id.radioGroup3);
        editTextNotes = findViewById(R.id.editTextNotes);

        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);

        
        Intent i = getIntent();

        String dateRequiredE = i.getStringExtra("dateRequired");
        String stockNeedsE = i.getStringExtra("stockNeedsE");
        String clusterE = i.getStringExtra("clusterE");
        String nameRequesterE = i.getStringExtra("nameRequesterE");
        String positionRequesterE = i.getStringExtra("positionRequesterE");
        String mobileRequesterEString = i.getStringExtra("mobileRequesterEString");
        String governmentChoice = i.getStringExtra("agencyYN");
        String emailRequesterE = i.getStringExtra("emailRequesterE");

        String productTypeSelection = i.getStringExtra("productType");
        String productItemSelection = i.getStringExtra("itemName2");
        String size = i.getStringExtra("sizeSelection");
        String productQuantitySelection = i.getStringExtra("QuantityChosen");

        String itemQuantity2 = i.getStringExtra("itemQuantity2");

        String itemDescription2 = i.getStringExtra("itemDescription2");
        String itemSupplier2 = i.getStringExtra("itemSupplier2");
        String itemContactSupplier2 = i.getStringExtra("itemContactSupplier2");



        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer radioGroup2Locationi = radioGroup2Location.getCheckedRadioButtonId();
                Integer radioGroup3Agencyi = radioGroup3Agency.getCheckedRadioButtonId();


                RadioButton locationRadioButton = (RadioButton) radioGroup2Location.findViewById(radioGroup2Locationi);
                RadioButton agencyRadioButton = (RadioButton) radioGroup3Agency.findViewById(radioGroup3Agencyi);

                String agencyNameE = editTextAgencyName.getText().toString();
                String addressLineE1 = editTextAddressLine.getText().toString();
                String suburbTownE = editTextSuburbTown.getText().toString();
                String extraNotes = editTextNotes.getText().toString();
                String approvalUser = "Pending User Allocation";
                String approvalStatus1 = "Application Submitted";
                String approvalComment1 = "Application Submitted";
                String trackingCompanyInformation = "Pending User Allocation";
                String trackingCompanyNumber = "Pending User Allocation";
                String expectedDeliveryDate = "Pending";

                if(agencyNameE.isEmpty() || addressLineE1.isEmpty() || suburbTownE.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill all Shipping Label Details", Toast.LENGTH_LONG).show();

                } else if(radioGroup2Locationi == -1) {

                    Toast.makeText(getApplicationContext(), "Please choose whether the items in this order being delivered to the same location!", Toast.LENGTH_LONG).show();


                } else if(radioGroup3Agencyi == -1) {
                    Toast.makeText(getApplicationContext(), "Please choose whether the items in this order are going to the same agency!", Toast.LENGTH_LONG).show();

                } else if(extraNotes.isEmpty()) {

                    String extraNotesDetails = "No Notes Entered";
                    String locationChoice = locationRadioButton.getText().toString();
                    String agencyChoice = agencyRadioButton.getText().toString();

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
                    //Deleted caseNoteHash.put("companyName", companyNameE);
                    caseNoteHash.put("addressLine1", addressLineE1);
                    //Deleted caseNoteHash.put("addressLine2", addressLineE2);
                    caseNoteHash.put("suburbTown", suburbTownE);

                    caseNoteHash.put("extraNotesDetails", extraNotesDetails);

                    caseNoteHash.put("approvalUser", approvalUser);
                    caseNoteHash.put("approvalStatus1", approvalStatus1);
                    caseNoteHash.put("approvalComment1", approvalComment1);
                    caseNoteHash.put("trackingCompanyInformation", trackingCompanyInformation);
                    caseNoteHash.put("trackingCompanyNumber",trackingCompanyNumber);
                    caseNoteHash.put("deliveryDate",expectedDeliveryDate);

                    caseNoteHash.put("itemDescription2", itemDescription2);
                    caseNoteHash.put("itemSupplier2", itemSupplier2);
                    caseNoteHash.put("itemContactSupplier2", itemContactSupplier2);

                    caseDB.set(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(getApplicationContext(), "Request Created", Toast.LENGTH_LONG).show();

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

                    BackgroundMail.newBuilder(RequesterShippingActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Corey,\\n\\nWe have received your request for the " + productItemSelection +" with a quantity of " + productQuantitySelection +".\\n\\nPlease message me on SupplyMe or reply back to this email for updates on your request.\\n\\nWarm Regards,\\n\\n" + itemSupplier2)
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

                    BackgroundMail.newBuilder(RequesterShippingActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("isabel.procureme@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Isabel Kim,\\n\\nYou have received a new request from Corey for the " + productItemSelection +" with a quantity of " + productQuantitySelection + ".\\n\\nPlease login to SupplyMe to view and reject or accept the request.\\n\\nWarm Regards,\\n\\nSupplyMe Administrator\\n\\n")
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
                    Intent i = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(i);


                } else {

                    String extraNotesDetails = editTextNotes.getText().toString();
                    String locationChoice = locationRadioButton.getText().toString();
                    String agencyChoice = agencyRadioButton.getText().toString();

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
                    //Deleted caseNoteHash.put("companyName", companyNameE);
                    caseNoteHash.put("addressLine1", addressLineE1);
                    //Deleted caseNoteHash.put("addressLine2", addressLineE2);
                    caseNoteHash.put("suburbTown", suburbTownE);

                    caseNoteHash.put("extraNotesDetails", extraNotesDetails);

                    caseNoteHash.put("approvalUser", approvalUser);
                    caseNoteHash.put("approvalStatus1", approvalStatus1);
                    caseNoteHash.put("approvalComment1", approvalComment1);
                    caseNoteHash.put("trackingCompanyInformation", trackingCompanyInformation);
                    caseNoteHash.put("trackingCompanyNumber",trackingCompanyNumber);
                    caseNoteHash.put("deliveryDate",expectedDeliveryDate);

                    caseNoteHash.put("itemDescription2", itemDescription2);
                    caseNoteHash.put("itemSupplier2", itemSupplier2);
                    caseNoteHash.put("itemContactSupplier2", itemContactSupplier2);

                    caseDB.set(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(getApplicationContext(), "Request Created", Toast.LENGTH_LONG).show();

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

                    BackgroundMail.newBuilder(RequesterShippingActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Corey,\\n\\nWe have received your request for the " + productItemSelection +" with a quantity of " + productQuantitySelection +".\\n\\nPlease message me on SupplyMe or reply back to this email for updates on your request.\\n\\nWarm Regards,\\n\\n" + itemSupplier2)
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

                    BackgroundMail.newBuilder(RequesterShippingActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("isabel.procureme@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Isabel Kim," +"\n" +"\n" + "You have received a new request from Corey for the " + productItemSelection +" with a quantity of " + productQuantitySelection + "."+"\n"+"\n"+ "Please login to SupplyMe to view and reject or accept the request." +'\n' +'\n'+ "Warm Regards,"+'\n'+'\n'+"SupplyMe Administrator")
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
                    Intent i = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(i);
                }





            }
        });
        
        


    }
}