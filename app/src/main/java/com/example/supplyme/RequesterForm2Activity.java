package com.example.supplyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RequesterForm2Activity extends AppCompatActivity {

    private RadioGroup agency;
    private RadioGroup location;
    private Spinner productType;
    private Spinner productItem;
    private Spinner productSize;
    private EditText productQuantity;
    private Button step2Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_form2);

        agency = findViewById(R.id.agencyRadioGroup);
        location = findViewById(R.id.locationRadioGroup);
        productType = findViewById(R.id.productTypeSpinner);
        productItem = findViewById(R.id.productItemSpinner);
        productSize = findViewById(R.id.sizeSpinner);
        productQuantity = findViewById(R.id.quantityEditText);
        step2Next = findViewById(R.id.step2Next);

        Intent i = getIntent();

        String dateRequiredE = i.getStringExtra("dateRequired");
        String stockNeedsE = i.getStringExtra("stockNeedsE");
        String clusterE = i.getStringExtra("clusterE");
        String nameRequesterE = i.getStringExtra("nameRequesterE");
        String positionRequesterE = i.getStringExtra("positionRequesterE");
        String mobileRequesterEString = i.getStringExtra("mobileRequesterEString");
        String emailRequesterE = i.getStringExtra("emailRequesterE");



        String[] productSizeArray = new String[] {"S", "M" , "L" , "XL" , "XXL" , "XXXL"};

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,productSizeArray);

        productSize.setAdapter(sizeAdapter);


        String[] productTypeArray = new String[] {"Hand Sanitiser", "Examination Gloves", "Disinfectant / Cleaning Products", " Handwash/Soap", "Masks", "Eyewear", "Gowns/Overalls", "Paper Products", "Other"};

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,productTypeArray);

        productType.setAdapter(typeAdapter);

        productType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch(i) {

                    case 0:
                        String[] HS = new String[] {"1L Aloe Vera", "500mL Alcohol Free", "30mL Lemon", " 1L Dettol Pump Instant" , " 350mL Tea Tree Sanitiser"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS));
                        break;

                    case 1:
                        String[] HS1 = new String[] {"Heavy Duty", "Blue Flexi" , "Ultra"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS1));
                        break;

                    case 2:
                        String[] HS2 = new String[] {"95% Isopropyl", "Combi Cleaner", "Hard Surface Cleaner"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS2));
                        break;

                    case 3:
                        String[] HS3 = new String[] {"Dettol Raspberry HandWash" , "Aesop Aromatique Hand Wash", "Premium Pink Hand Soap" , "Thankyou Botanical HandWash" };

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS3));
                        break;

                    case 4:
                        String[] HS4 = new String[] {"3M KN95 Mask x 5", "TGA Surgical Mask x 50", "Cloth Face Mask", "3M Respirator Mask"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS4));
                        break;

                    case 5:
                        String[] HS5 = new String[] {"Glasses"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS5));
                        break;

                    case 6:
                        String[] HS6 = new String[] {"UNSW Gowns"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS6));
                        break;

                    case 7:
                        String[] HS7 = new String[] {"3 Ply Paper x 1000 Rolls"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS7));
                        break;

                    case 8:
                        String[] HS8 = new String[] {"Special Request"};

                        productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS8));
                        break;

                }

                //if(i == 0) {

                    //String[] HS = new String[] {"1L Aloe Vera", "500mL Alcohol Free", "30mL Lemon", " 1L Dettol Pump Instant" , " 350mL Tea Tree Sanitiser"};

                    //productItem.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,HS));

                //}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(getApplicationContext(), "Select an item!", Toast.LENGTH_LONG).show();



            }
        });


        step2Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioAgencyChoice = agency.getCheckedRadioButtonId();
                int radioLocationChoice = location.getCheckedRadioButtonId();

                RadioButton agencyButton = (RadioButton) agency.findViewById(radioAgencyChoice);
                RadioButton locationButton = (RadioButton) location.findViewById(radioLocationChoice);

                String agencyChoice = agencyButton.getText().toString();
                String locationChoice = locationButton.getText().toString();

                String productTypeSelection = productType.getSelectedItem().toString();
                String productItemSelection = productItem.getSelectedItem().toString();
                String productSizeSelection = productSize.getSelectedItem().toString();
                String productQuantitySelection = productQuantity.getText().toString();


                if (agencyChoice.isEmpty() || locationChoice.isEmpty() || productTypeSelection.isEmpty() || productItemSelection.isEmpty() || productQuantitySelection.isEmpty() ) {

                    Toast.makeText(getApplicationContext(), "Please fill in all Required Details", Toast.LENGTH_LONG).show();
                } else if(productSizeSelection.isEmpty()) {

                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),RequesterForm3Activity.class);

                    String noSelection = "No Size Selected";


                    //2nd Page Form Deets

                    i.putExtra("agencyYN", agencyChoice);
                    i.putExtra("locationYN", locationChoice);
                    i.putExtra("type", productTypeSelection);
                    i.putExtra("item", productItemSelection);
                    i.putExtra("quantity", productQuantitySelection);
                    i.putExtra("size", noSelection);


                    //i.putExtra("mobileRequesterEString", mobileRequesterEString);

                    //1st Page Form Deets

                    i.putExtra("dateRequired", dateRequiredE);
                    i.putExtra("stockNeedsE", stockNeedsE);
                    i.putExtra("clusterE", clusterE );
                    i.putExtra("nameRequesterE", nameRequesterE);
                    i.putExtra("positionRequesterE", positionRequesterE);
                    i.putExtra("mobileRequesterEString", mobileRequesterEString);
                    i.putExtra("emailRequesterE", emailRequesterE);

                    System.out.println(dateRequiredE);

                    c.startActivity(i);

                } else {

                    Context c = view.getContext();
                    Intent i = new Intent(getApplicationContext(),RequesterForm3Activity.class);
                    i.putExtra("agencyYN", agencyChoice);
                    i.putExtra("locationYN", locationChoice);
                    i.putExtra("type", productTypeSelection);
                    i.putExtra("item", productItemSelection);
                    i.putExtra("quantity", productQuantitySelection);
                    i.putExtra("size", productSizeSelection);

                    i.putExtra("dateRequired", dateRequiredE);
                    i.putExtra("stockNeedsE", stockNeedsE);
                    i.putExtra("clusterE", clusterE );
                    i.putExtra("nameRequesterE", nameRequesterE);
                    i.putExtra("positionRequesterE", positionRequesterE);
                    i.putExtra("mobileRequesterEString", mobileRequesterEString);
                    i.putExtra("emailRequesterE", emailRequesterE);

                    System.out.println(productTypeSelection);

                    c.startActivity(i);

                }











            }
        });



    }
}