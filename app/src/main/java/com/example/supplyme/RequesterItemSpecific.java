package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class RequesterItemSpecific extends AppCompatActivity {

    public TextView textView16;
    public TextView textView18;
    public TextView textView19;
    public TextView textView15;
    public RadioGroup radioGroup;
    public EditText quantitySpecificET;
    public Button order;
    public ImageButton back;
    public ImageButton share;
    public Button button5;
    public ImageView imageView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Objects.requireNonNull(getSupportActionBar()).hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_item_specific);


        textView16 = findViewById(R.id.textView16);
        textView15 = findViewById(R.id.textView15);
        textView18 = findViewById(R.id.textView18);
        radioGroup = findViewById(R.id.radioGroup);
        quantitySpecificET = findViewById(R.id.quantitySpecificET);
        order = findViewById(R.id.button8);
        button5 = findViewById(R.id.button5);
        back = findViewById(R.id.imageButton2);
        share = findViewById(R.id.imageButton3);
        imageView6 = findViewById(R.id.imageView6);
        textView19 = findViewById(R.id.textView19);

        textView19.setMovementMethod(new ScrollingMovementMethod());

        Intent i = getIntent();




        // Page 1 Details

        String dateRequiredE = i.getStringExtra("dateRequired");
        String stockNeedsE = i.getStringExtra("stockNeedsE");
        String clusterE = i.getStringExtra("clusterE");
        String nameRequesterE = i.getStringExtra("nameRequesterE");
        String positionRequesterE = i.getStringExtra("positionRequesterE");
        String mobileRequesterEString = i.getStringExtra("mobileRequesterEString");
        String emailRequesterE = i.getStringExtra("emailRequesterE");
        String agencyYN = i.getStringExtra("agencyYN");

        // Page 2 Choosing Product Details

        String productType = i.getStringExtra("productType");
        String itemName2 = i.getStringExtra("itemName2");
        String itemQuantity2 = i.getStringExtra("itemQuantity2");
        String itemDescription2 = i.getStringExtra("itemDescription2");
        String itemSupplier2 = i.getStringExtra("itemSupplier2");
        String itemContactSupplier2 = i.getStringExtra("itemContactSupplier2");

        //Setting Details

        textView16.setText(itemName2);
        textView18.setText(itemName2);
        textView19.setText(itemDescription2);
        textView15.setText("Qty Available : 200" + itemQuantity2);

        int itemQuantityStock = Integer.parseInt(itemQuantity2);


        if (productType.equals("Hand Sanitiser")) {

            imageView6.setImageResource(R.drawable.asanitiser);


        } else if(productType.equals("Examination Gloves")) {

            imageView6.setImageResource(R.drawable.agloves);

        } else if(productType.equals("Disinfectant / Cleaning Products")) {

            imageView6.setImageResource(R.drawable.adisinefectant);

        } else if(productType.equals("Handwash/Soap") || productType.equals(" Handwash/Soap")) {

            imageView6.setImageResource(R.drawable.ahandwash);

        } else if(productType.equals("Masks")) {

            imageView6.setImageResource(R.drawable.amask);

        } else if(productType.equals("Eyewear")) {

            imageView6.setImageResource(R.drawable.aeyewear);

        } else if(productType.equals("Gowns/Overalls")) {

            imageView6.setImageResource(R.drawable.agown);

        } else if(productType.equals("Paper Products")) {

            imageView6.setImageResource(R.drawable.aroll);

        } else if(productType.equals("Other")) {

            imageView6.setImageResource(R.drawable.aother);

        }

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer sizeChoice = radioGroup.getCheckedRadioButtonId();
                String quantityChosen = quantitySpecificET.getText().toString();

                RadioButton sizeButton = (RadioButton) radioGroup.findViewById(sizeChoice);

                int quantityInt = Integer.parseInt(quantityChosen);

                if(sizeChoice == -1) {
                    String sizeSelection = "N/A";
                } else {
                    String sizeSelection = sizeButton.getText().toString();
                }



                if(quantityChosen.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill in the quantity required!", Toast.LENGTH_LONG).show();

                } else if(quantityInt > itemQuantityStock) {
                    Toast.makeText(getApplicationContext(), "Maximum Stock exceeded, please reduce the quantity required!", Toast.LENGTH_LONG).show();

                } else if(sizeChoice == -1) {

                    String sizeSelection = "N/A";

                    Context c = view.getContext();
                    Intent i = new Intent(view.getContext(), RequesterShippingActivity.class);

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
                    i.putExtra("sizeSelection", sizeSelection);
                    i.putExtra("QuantityChosen",quantityChosen);

                    c.startActivity(i);

                } else {

                    String sizeSelection = sizeButton.getText().toString();

                    Context c = view.getContext();
                    Intent i = new Intent(view.getContext(), RequesterShippingActivity.class);

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
                    i.putExtra("sizeSelection", sizeSelection);
                    i.putExtra("QuantityChosen",quantityChosen);

                    c.startActivity(i);

                }

            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer sizeChoice = radioGroup.getCheckedRadioButtonId();
                String quantityChosen = quantitySpecificET.getText().toString();

                RadioButton sizeButton = (RadioButton) radioGroup.findViewById(sizeChoice);

                int quantityInt = Integer.parseInt(quantityChosen);

                if(sizeChoice == -1) {
                    String sizeSelection = "N/A";
                } else {
                    String sizeSelection = sizeButton.getText().toString();
                }




                if(quantityChosen.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill in the quantity required!", Toast.LENGTH_LONG).show();

                } else if(quantityInt > itemQuantityStock) {
                    Toast.makeText(getApplicationContext(), "Maximum Stock exceeded, please reduce the quantity required!", Toast.LENGTH_LONG).show();

                } else if(sizeChoice == -1) {

                    String sizeSelection = "N/A";

                    Context c = view.getContext();
                    Intent i = new Intent(view.getContext(), RequesterShippingActivity.class);

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
                    i.putExtra("sizeSelection", sizeSelection);
                    i.putExtra("QuantityChosen",quantityChosen);

                    c.startActivity(i);

                } else {

                    String sizeSelection = sizeButton.getText().toString();

                    Context c = view.getContext();
                    Intent i = new Intent(view.getContext(), RequesterShippingActivity.class);

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
                    i.putExtra("sizeSelection", sizeSelection);
                    i.putExtra("QuantityChosen",quantityChosen);

                    c.startActivity(i);

                }

            }
        });






    }
}