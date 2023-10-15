package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.tasks.OnCanceledListener;
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

public class AcceptRejectFinalActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvPosition;
    TextView tvEmail;
    TextView tvPhone;
    TextView tv57;
    TextView tvItem;
    TextView tvQuantity;

    EditText editTextTextMultiLine;
    FloatingActionButton approveFAB;

    public FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;
    public FirebaseFirestore firebaseDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_reject_final);

        Intent i = getIntent();

        String status = i.getStringExtra("Status");
        String name = i.getStringExtra("Name");
        String position = i.getStringExtra("Position");
        String phone = i.getStringExtra("Phone");
        String email = i.getStringExtra("Email");
        String item = i.getStringExtra("Item");
        String quantity = i.getStringExtra("Quantity");
        String ID = i.getStringExtra("Id");

        tv57 = findViewById(R.id.textView57);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        approveFAB = findViewById(R.id.approveFAB);
        tvName = findViewById(R.id.tvDateRequired2);
        tvPosition = findViewById(R.id.tvDateRequired3);
        tvEmail = findViewById(R.id.tvDateRequired4);
        tvPhone = findViewById(R.id.tvDateRequired5);
        tvItem = findViewById(R.id.tvDateRequired6);
        tvQuantity = findViewById(R.id.tvDateRequired7);



        tvName.setText(name);
        tvPosition.setText(position);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvItem.setText(item);
        tvQuantity.setText(quantity);

        if(status.equals("Delivery")) {

            tv57.setText("Input Tracking Number");

        } else if(status.equals("Rejection")) {

            tv57.setText("Enter Rejection Reason");

        } else if(status.equals("Acceptance")) {

            tv57.setText("Provide Approval Reason (Optional)");

        }


        String EditTextLine = editTextTextMultiLine.getText().toString();

        //IF STATUS = REJECTED

        System.out.print(status);


        approveFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                String EditTextLine = editTextTextMultiLine.getText().toString();
                String status = i.getStringExtra("Status");

                System.out.println(EditTextLine);
                System.out.println(status);

                if(EditTextLine.isEmpty() && status.equals("Delivery")) {

                    tv57.setText("Input Tracking Number");


                } else if(EditTextLine.isEmpty() && status.equals("Rejection")) {

                    tv57.setText("Enter Rejection Reason");

                } else if(EditTextLine.isEmpty() && status.equals("Acceptance")) {

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDB = FirebaseFirestore.getInstance();
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document(ID);
                    Map<String,Object> caseNoteHash = new HashMap<>();


                    caseNoteHash.put("approvalStatus1","Application Accepted");
                    caseNoteHash.put("approvalComment1", "N/A");

                    caseDB.update(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(getApplicationContext(), "Application Status Updated!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {

                            Toast.makeText(getApplicationContext(), "Cancelled Changes!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Changes Unsuccessful", Toast.LENGTH_LONG).show();

                        }
                    });


                    BackgroundMail.newBuilder(AcceptRejectFinalActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Corey, Your application has been accepted. Please be on the lookout for dispatch updates. Warm Regards, SupplyMe Administrator")
                            .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                @Override
                                public void onSuccess() {

                                    Toast.makeText(getApplicationContext(), "Delivery Email Sent", Toast.LENGTH_LONG).show();
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
                    Intent is = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(is);


                } else if(status.equals("Delivery")) {

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseDB = FirebaseFirestore.getInstance();
                        currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document(ID);
                        Map<String,Object> caseNoteHash = new HashMap<>();


                        caseNoteHash.put("approvalStatus1","Goods Dispatched");
                        caseNoteHash.put("approvalComment1", "Your Goods have been dispatched!");
                        caseNoteHash.put("trackingCompanyNumber", EditTextLine);

                        caseDB.update(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(getApplicationContext(), "Application Status Updated!", Toast.LENGTH_LONG).show();

                            }
                        }).addOnCanceledListener(new OnCanceledListener() {
                            @Override
                            public void onCanceled() {

                                Toast.makeText(getApplicationContext(), "Cancelled Changes!", Toast.LENGTH_LONG).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(), "Changes Unsuccessful", Toast.LENGTH_LONG).show();

                            }
                        });


                        BackgroundMail.newBuilder(AcceptRejectFinalActivity.this)
                                .withUsername("supplyme.updater@gmail.com")
                                .withPassword("infs3605t1")
                                .withMailto("corey.rfs@gmail.com")
                                .withType(BackgroundMail.TYPE_PLAIN)
                                .withSubject("Request Received")
                                .withBody("Hi Corey, Your goods have been dispatched with tracking number " + EditTextLine + ". Please message Isabel Kim if you have any questions. Warm Regards, SupplyMe Administrator")
                                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                    @Override
                                    public void onSuccess() {

                                        Toast.makeText(getApplicationContext(), "Delivery Email Sent", Toast.LENGTH_LONG).show();
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
                    Intent is = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(is);



                    } else if(status.equals("Rejection")) {

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDB = FirebaseFirestore.getInstance();
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document(ID);
                    Map<String,Object> caseNoteHash = new HashMap<>();


                    caseNoteHash.put("approvalStatus1","Rework Required");
                    caseNoteHash.put("approvalComment1", EditTextLine);
                    caseNoteHash.put("trackingCompanyNumber", EditTextLine);

                    caseDB.update(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(getApplicationContext(), "Application Status Updated!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {

                            Toast.makeText(getApplicationContext(), "Cancelled Changes!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Changes Unsuccessful", Toast.LENGTH_LONG).show();

                        }
                    });


                    BackgroundMail.newBuilder(AcceptRejectFinalActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Corey, Your application has been refused because of" + EditTextLine + ". Please message Isabel Kim if you have any questions. Warm Regards, SupplyMe Administrator")
                            .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                @Override
                                public void onSuccess() {

                                    Toast.makeText(getApplicationContext(), "Delivery Email Sent", Toast.LENGTH_LONG).show();
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
                    Intent is = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(is);


                } else if(status.equals("Acceptance")) {

                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseDB = FirebaseFirestore.getInstance();
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();

                    DocumentReference caseDB = firebaseDB.collection("cases").document("caseDocuments").collection("requesterCases").document(ID);
                    Map<String,Object> caseNoteHash = new HashMap<>();


                    caseNoteHash.put("approvalStatus1","Application Accepted");
                    caseNoteHash.put("approvalComment1", EditTextLine);

                    caseDB.update(caseNoteHash).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(getApplicationContext(), "Application Status Updated!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {

                            Toast.makeText(getApplicationContext(), "Cancelled Changes!", Toast.LENGTH_LONG).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), "Changes Unsuccessful", Toast.LENGTH_LONG).show();

                        }
                    });


                    BackgroundMail.newBuilder(AcceptRejectFinalActivity.this)
                            .withUsername("supplyme.updater@gmail.com")
                            .withPassword("infs3605t1")
                            .withMailto("corey.rfs@gmail.com")
                            .withType(BackgroundMail.TYPE_PLAIN)
                            .withSubject("Request Received")
                            .withBody("Hi Corey, Your application has been accepted with the comment of " + EditTextLine + ". Please message Isabel Kim if you have any questions. Warm Regards, SupplyMe Administrator")
                            .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                @Override
                                public void onSuccess() {

                                    Toast.makeText(getApplicationContext(), "Delivery Email Sent", Toast.LENGTH_LONG).show();
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
                    Intent is = new Intent(getApplicationContext(),CurrentCaseActivity.class);

                    c.startActivity(is);


                } else {
                    System.out.println("ISSUES EXIST WITH SAVING ASK SYSTEM ADMINISTRATOR FOR HELP");
                }

            }
        });


    }
}