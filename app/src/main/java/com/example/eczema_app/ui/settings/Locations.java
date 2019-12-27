package com.example.eczema_app.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.eczema_app.R;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;

public class Locations extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_location);

//        Switch location_switch = findViewById(R.id.allow_location);
//        location_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    createLocationRequest();
//                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                            .addLocationRequest(locationRequest);
////                    Intent location_intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////                    startActivityForResult(location_intent);
//                }
//            }
//
//
//        });

    }
//    public void openPhoneSettings(){
//        Intent location_intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//         startActivityForResult(location_intent);
//    }
//    @Override
//    public void onCheckedChange(CompoundButton buttonView, boolean isChecked){
//        if (isChecked){
//        Intent location_intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//        startActivityForResult(location_intent);
//        } else {
//
//        }
//    }
//code from https://developer.android.com/training/location/change-location-settings
//protected void createLocationRequest() {
//    LocationRequest locationRequest = LocationRequest.create();
//    locationRequest.setInterval(10000);
//    locationRequest.setFastestInterval(5000);
//    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//}
}
