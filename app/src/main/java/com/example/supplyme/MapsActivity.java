package com.example.supplyme;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.supplyme.databinding.ActivityMapsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        // ***NAVIGATION BAR

        // Set current selected item
        bottomNavigationView.setSelectedItemId(R.id.map);

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
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfilePageActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.requests:
                        startActivity(new Intent(getApplicationContext(), CurrentCaseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }
        });
        // NAVIGATION BAR***
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney Metropolitan Area"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        setCoordinates();


    }


    public void setCoordinates() {
        //Hardcoded coordinates
        LatLng post1 = new LatLng(-33.84434351578515, 151.07458867705162);
        mMap.addMarker(new MarkerOptions().position(post1).title("Resilience NSW HQ"));

        LatLng post2 = new LatLng(-33.93924591246286, 150.93829710286474);
        mMap.addMarker(new MarkerOptions().position(post2).title("ProcureMe Fulfillment Centre"));

        LatLng post3 = new LatLng(-33.819696452583784, 151.19134608469017);
        mMap.addMarker(new MarkerOptions().position(post3).title("Royal North Shore Hospital"));

        LatLng post4 = new LatLng(-33.74262933490664, 150.85679760020506);
        mMap.addMarker(new MarkerOptions().position(post4).title("RFS Station #2421"));

        LatLng post5 = new LatLng(-33.68779849178087, 151.10826732179532);
        mMap.addMarker(new MarkerOptions().position(post5).title("Order #2610 Live Location"));

        LatLng post6 = new LatLng(-33.813794167386405, 151.15568168904403);
        mMap.addMarker(new MarkerOptions().position(post6).title("Order #2611 Live Location"));

        LatLng post7 = new LatLng(-33.831604854530546, 151.0211600746636);
        mMap.addMarker(new MarkerOptions().position(post7).title("Order #2609 Live Location"));

        LatLng post8 = new LatLng(-33.77176536362177, 151.08134392144794);
        mMap.addMarker(new MarkerOptions().position(post8).title("Order #2607 Live Location"));

        LatLng post9 = new LatLng(-33.916984397912515, 151.10715170242253);
        mMap.addMarker(new MarkerOptions().position(post9).title("Order #2600 Live Location"));


        //Set position of camera to current position
        mMap.moveCamera(CameraUpdateFactory.newLatLng(post1));

    }


}