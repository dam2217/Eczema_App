package com.example.eczema_app.ui.settings;


import android.location.Location;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eczema_app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

//code adapted from https://developer.android.com/guide/topics/ui/settings

public class SettingsActivity extends AppCompatActivity{
    private FusedLocationProviderClient fusedLocationClient;
    private double lat;
    private double lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_settings, new SettingsFragment())
                .commit();

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this,  new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Logic to handle location object
                    lat = location.getLatitude();
                    System.out.print(lat);
                    lon = location.getLongitude();
                    System.out.print(lon);
                }
            }
        });

    }
}


