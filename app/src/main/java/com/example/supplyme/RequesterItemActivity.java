package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sun.mail.imap.protocol.Item;

import java.util.Objects;

public class RequesterItemActivity extends AppCompatActivity {

    public ImageButton back;
    RecyclerView caseRecyclerView;
    public ImageButton next;
    FirebaseFirestore firebaseDB;
    FirebaseAuth firebaseAuth;
    StaggeredGridLayoutManager LayoutManager;
    FirestoreRecyclerAdapter<ItemsFirebaseModel, itemsViewHolder> itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_item);

        back = findViewById(R.id.imageButton4);
        next = findViewById(R.id.imageButton5);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDB = FirebaseFirestore.getInstance();

        Intent i = getIntent();

        String dateRequiredE = i.getStringExtra("dateRequired");
        String stockNeedsE = i.getStringExtra("stockNeedsE");
        String clusterE = i.getStringExtra("clusterE");
        String nameRequesterE = i.getStringExtra("nameRequesterE");
        String positionRequesterE = i.getStringExtra("positionRequesterE");
        String mobileRequesterEString = i.getStringExtra("mobileRequesterEString");
        String emailRequesterE = i.getStringExtra("emailRequesterE");
        String agencyYN = i.getStringExtra("agencyYN");

        Query caseQuery = firebaseDB.collection("items").document("itemDocuments").collection("uniqueItems");

        FirestoreRecyclerOptions<ItemsFirebaseModel> currentItems = new FirestoreRecyclerOptions.Builder<ItemsFirebaseModel>().setQuery(caseQuery, ItemsFirebaseModel.class).build();

        itemsAdapter = new FirestoreRecyclerAdapter<ItemsFirebaseModel, itemsViewHolder>(currentItems) {
            @NonNull
            @Override
            public itemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recycler_view,parent,false);

                return new itemsViewHolder(v);

            }

            @Override
            protected void onBindViewHolder(@NonNull itemsViewHolder holder, int position, @NonNull ItemsFirebaseModel model) {

                holder.productNameTV.setText(model.getItemName());

                String productType = model.getItemType().toString();
                String itemName2 = model.getItemName();
                String itemQuantity2 = model.getItemQuantity();
                String itemDescription2 = model.getItemDescription();
                String itemSupplier2 = model.getItemSupplier();
                String itemContactSupplier2 = model.getItemContactSupplier();

                if (productType.equals("Hand Sanitiser")) {

                    holder.imageView5.setImageResource(R.drawable.asanitiser);


                } else if(productType.equals("Examination Gloves")) {

                    holder.imageView5.setImageResource(R.drawable.agloves);

                } else if(productType.equals("Disinfectant / Cleaning Products")) {

                    holder.imageView5.setImageResource(R.drawable.adisinefectant);

                } else if(productType.equals("Handwash/Soap") || productType.equals(" Handwash/Soap")) {

                    holder.imageView5.setImageResource(R.drawable.ahandwash);

                } else if(productType.equals("Masks")) {

                    holder.imageView5.setImageResource(R.drawable.amask);

                } else if(productType.equals("Eyewear")) {

                    holder.imageView5.setImageResource(R.drawable.aeyewear);

                } else if(productType.equals("Gowns/Overalls")) {

                    holder.imageView5.setImageResource(R.drawable.agown);

                } else if(productType.equals("Paper Products")) {

                    holder.imageView5.setImageResource(R.drawable.aroll);

                } else if(productType.equals("Other")) {

                    holder.imageView5.setImageResource(R.drawable.aother);

                }


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {



                        Intent i = new Intent(view.getContext(), RequesterItemSpecific.class);

                        // Page 1 Deets

                        i.putExtra("dateRequired", dateRequiredE);
                        i.putExtra("stockNeedsE", stockNeedsE);
                        i.putExtra("clusterE", clusterE );
                        i.putExtra("nameRequesterE", nameRequesterE);
                        i.putExtra("positionRequesterE", positionRequesterE);
                        i.putExtra("mobileRequesterEString", mobileRequesterEString);
                        i.putExtra("emailRequesterE", emailRequesterE);
                        i.putExtra("agencyYN",agencyYN);

                        // Page 2 Deets


                        i.putExtra("productType", productType);
                        i.putExtra("itemName2", itemName2);
                        i.putExtra("itemQuantity2", itemQuantity2);
                        i.putExtra("itemDescription2", itemDescription2);
                        i.putExtra("itemSupplier2", itemSupplier2);
                        i.putExtra("itemContactSupplier2",itemContactSupplier2);




                        view.getContext().startActivity(i);

                    }
                });

            }
        };

        caseRecyclerView = findViewById(R.id.itemRV);
        caseRecyclerView.setHasFixedSize(true);
        LayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        caseRecyclerView.setLayoutManager(LayoutManager);
        caseRecyclerView.setAdapter(itemsAdapter);


    }

    public class itemsViewHolder extends RecyclerView.ViewHolder {

        public TextView productNameTV;
        public ImageView imageView5;

        public itemsViewHolder(@NonNull View itemView) {
            super(itemView);

            productNameTV = itemView.findViewById(R.id.productNameTV);
            imageView5 = itemView.findViewById(R.id.imageView5);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        itemsAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(itemsAdapter != null) {
            itemsAdapter.stopListening();
        }
    }
}