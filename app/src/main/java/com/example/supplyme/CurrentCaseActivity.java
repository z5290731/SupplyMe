package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.util.Objects;

public class CurrentCaseActivity extends AppCompatActivity {

    RecyclerView caseRecyclerView;
    FloatingActionButton add;
    FirebaseFirestore fireBaseDB;
    FirebaseAuth firebaseAuth;
    BottomNavigationView bottomNavigationView;
    FirestoreRecyclerAdapter<CasesFirebaseModel,CasesViewHolder> CurrentCasesAdapter;
    ImageView imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_case);

        Objects.requireNonNull(getSupportActionBar()).hide();

        firebaseAuth = FirebaseAuth.getInstance();
        fireBaseDB = FirebaseFirestore.getInstance();

        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
        String name = appUser.getDisplayName();
        String firebaseEmail = appUser.getEmail();

        TextView welcomeMessage = (TextView) findViewById(R.id.textView);
        imageView2 = findViewById(R.id.imageView2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CurrentCaseActivity.this,DashboardActivity.class));

            }
        });

        welcomeMessage.setText("Welcome " + name +"!");

        add = findViewById(R.id.floatingActionButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
                String name = appUser.getDisplayName();
                String firebaseEmail = appUser.getEmail();

                if(firebaseEmail.equals("isabel.procureme@gmail.com") || firebaseEmail.equals("echie007@rfs.nsw.gov.au")) {

                    add.setTooltipText("Add New Item into SupplyMe");
                    startActivity(new Intent(CurrentCaseActivity.this,AddItemActivity.class));


                } else {
                    add.setTooltipText("Request New Item");
                    startActivity(new Intent(CurrentCaseActivity.this,RequesterDetailsActivity.class));
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

        Query caseQuery = fireBaseDB.collection("cases").document("caseDocuments").collection("requesterCases");

        FirestoreRecyclerOptions<CasesFirebaseModel> currentCases = new FirestoreRecyclerOptions.Builder<CasesFirebaseModel>().setQuery(caseQuery,CasesFirebaseModel.class).build();



        CurrentCasesAdapter = new FirestoreRecyclerAdapter<CasesFirebaseModel,CasesViewHolder>(currentCases) {

            @Override
            protected void onBindViewHolder(@NonNull CasesViewHolder casesViewHolder, int i, @NonNull CasesFirebaseModel casesFirebaseModel) {
                //SET TEXT START ACTIVITY ON CLICK

                casesViewHolder.itemTV.setText(casesFirebaseModel.getProductItemSelection());
                casesViewHolder.quantityTV.setText(casesFirebaseModel.getProductQuantitySelection());
                casesViewHolder.approvalStatusTV.setText(casesFirebaseModel.getApprovalStatus1());
                casesViewHolder.approverTV.setText(casesFirebaseModel.getApprovalUser());

                String approvalStatusTV = casesFirebaseModel.getApprovalStatus1().toString();

                System.out.println(casesFirebaseModel.getProductItemSelection().toString() + " SUCCESS DATABASE QUERY ");

                String caseID = CurrentCasesAdapter.getSnapshots().getSnapshot(casesViewHolder.getAdapterPosition()).getId();

                FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
                String email = appUser.getEmail();



                casesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FirebaseUser appUser = FirebaseAuth.getInstance().getCurrentUser();
                        String email = appUser.getEmail();

                        if(email.equals("isabel.procureme@gmail.com") || email.equals("echie007@rfs.nsw.gov.au")) {

                            Intent i = new Intent(view.getContext(), CurrentApprovalCaseActivity.class);

                            i.putExtra("dateRequired",casesFirebaseModel.getDateRequired());
                            i.putExtra("stockNeeds",casesFirebaseModel.getStockNeeds());
                            i.putExtra("cluster",casesFirebaseModel.getCluster());
                            i.putExtra("nameRequester",casesFirebaseModel.getNameRequester());
                            i.putExtra("positionRequester",casesFirebaseModel.getPositionRequester());
                            i.putExtra("mobileRequester",casesFirebaseModel.getMobileRequester());
                            i.putExtra("emailRequester",casesFirebaseModel.getEmailRequester());

                            i.putExtra("agencyChoice", casesFirebaseModel.getAgencyChoice());
                            i.putExtra("locationChoice", casesFirebaseModel.getLocationChoice());
                            i.putExtra("productTypeSelection", casesFirebaseModel.getProductTypeSelection());
                            i.putExtra("productItemSelection", casesFirebaseModel.getProductItemSelection());
                            i.putExtra("productQuantitySelection", casesFirebaseModel.getProductQuantitySelection());
                            i.putExtra("size", casesFirebaseModel.getSize());

                            i.putExtra("agencyName", casesFirebaseModel.getAgencyName());
                            i.putExtra("governmentChoice", casesFirebaseModel.getGovernmentChoice());
                            //Deleted i.putExtra("companyName", companyNameE);
                            i.putExtra("addressLine1", casesFirebaseModel.getAddressLine1());
                            //Deleted i.putExtra("addressLine2", addressLineE2);
                            i.putExtra("suburbTown", casesFirebaseModel.getSuburbTown());

                            i.putExtra("extraNotesDetails", casesFirebaseModel.getExtraNotesDetails());

                            i.putExtra("approvalUser", casesFirebaseModel.getApprovalUser());
                            i.putExtra("approvalStatus1", casesFirebaseModel.getApprovalStatus1());
                            i.putExtra("approvalComment1", casesFirebaseModel.getApprovalComment1());
                            i.putExtra("trackingCompanyInformation", casesFirebaseModel.getTrackingCompanyInformation());
                            i.putExtra("trackingCompanyNumber",casesFirebaseModel.getTrackingCompanyNumber());
                            i.putExtra("deliveryDate",casesFirebaseModel.getDeliveryDate());

                            i.putExtra("itemDescription2", casesFirebaseModel.getItemDescription2());
                            i.putExtra("itemSupplier2", casesFirebaseModel.getItemSupplier2());
                            i.putExtra("itemContactSupplier2", casesFirebaseModel.getItemContactSupplier2());

                            i.putExtra("ID",caseID);

                            view.getContext().startActivity(i);



                        } else {

                            Intent i = new Intent(view.getContext(), viewCurrentCaseActivity.class);

                            i.putExtra("dateRequired",casesFirebaseModel.getDateRequired());
                            i.putExtra("stockNeeds",casesFirebaseModel.getStockNeeds());
                            i.putExtra("cluster",casesFirebaseModel.getCluster());
                            i.putExtra("nameRequester",casesFirebaseModel.getNameRequester());
                            i.putExtra("positionRequester",casesFirebaseModel.getPositionRequester());
                            i.putExtra("mobileRequester",casesFirebaseModel.getMobileRequester());
                            i.putExtra("emailRequester",casesFirebaseModel.getEmailRequester());

                            i.putExtra("agencyChoice", casesFirebaseModel.getAgencyChoice());
                            i.putExtra("locationChoice", casesFirebaseModel.getLocationChoice());
                            i.putExtra("productTypeSelection", casesFirebaseModel.getProductTypeSelection());
                            i.putExtra("productItemSelection", casesFirebaseModel.getProductItemSelection());
                            i.putExtra("productQuantitySelection", casesFirebaseModel.getProductQuantitySelection());
                            i.putExtra("size", casesFirebaseModel.getSize());

                            i.putExtra("agencyName", casesFirebaseModel.getAgencyName());
                            i.putExtra("governmentChoice", casesFirebaseModel.getGovernmentChoice());
                            //Deleted i.putExtra("companyName", companyNameE);
                            i.putExtra("addressLine1", casesFirebaseModel.getAddressLine1());
                            //Deleted i.putExtra("addressLine2", addressLineE2);
                            i.putExtra("suburbTown", casesFirebaseModel.getSuburbTown());

                            i.putExtra("extraNotesDetails", casesFirebaseModel.getExtraNotesDetails());

                            i.putExtra("approvalUser", casesFirebaseModel.getApprovalUser());
                            i.putExtra("approvalStatus1", casesFirebaseModel.getApprovalStatus1());
                            i.putExtra("approvalComment1", casesFirebaseModel.getApprovalComment1());
                            i.putExtra("trackingCompanyInformation", casesFirebaseModel.getTrackingCompanyInformation());
                            i.putExtra("trackingCompanyNumber",casesFirebaseModel.getTrackingCompanyNumber());
                            i.putExtra("deliveryDate",casesFirebaseModel.getDeliveryDate());

                            i.putExtra("itemDescription2", casesFirebaseModel.getItemDescription2());
                            i.putExtra("itemSupplier2", casesFirebaseModel.getItemSupplier2());
                            i.putExtra("itemContactSupplier2", casesFirebaseModel.getItemContactSupplier2());

                            i.putExtra("ID",caseID);

                            view.getContext().startActivity(i);

                        }



                    }
                });


            }

            @NonNull
            @Override
            public CasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cases_recycler_view,parent,false);

                return new CasesViewHolder(v);

            }

        };

        caseRecyclerView = findViewById(R.id.rvCurrentCaseView);
        caseRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        caseRecyclerView.setAdapter(CurrentCasesAdapter);








    }


    @Override
    public void onBackPressed() {
    }

    public class CasesViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTV;
        private TextView quantityTV;
        private TextView approvalStatusTV;
        private TextView approverTV;

        //private TextView bodyNote;
        //LinearLayout mNote;

        public CasesViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTV = itemView.findViewById(R.id.productItemTV);
            quantityTV = itemView.findViewById(R.id.productQuantityTV);
            approvalStatusTV = itemView.findViewById(R.id.approvalStatusTV);
            approverTV = itemView.findViewById(R.id.approverTV);

            //titleNote = itemView.findViewById(R.id.textView98);
            //bodyNote = itemView.findViewById(R.id.textView99);
            //mNote = itemView.findViewById(R.id.ll_Note);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        CurrentCasesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(CurrentCasesAdapter != null) {
            CurrentCasesAdapter.stopListening();
        }
    }
}