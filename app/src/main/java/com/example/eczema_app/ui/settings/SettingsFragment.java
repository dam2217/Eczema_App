package com.example.eczema_app.ui.settings;
import android.app.TaskInfo;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.eczema_app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

//Code developed from https://developer.android.com/guide/topics/ui/settings

public class SettingsFragment extends PreferenceFragmentCompat {

//    private FusedLocationProviderClient fusedLocationClient;
//    private double lat;
//    private double lon;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference locationsettings = findPreference("launch_settings");

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 0);
        locationsettings.setIntent(intent);

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(com.example.eczema_app.ui.settings.SettingsFragment);
//        fusedLocationClient.getLastLocation().addOnSuccessListener(this,  new OnSuccessListener<Location>(){
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            lat = location.getLatitude();
//                            lon = location.getLongitude();
//                        }
//                  }
//                });
//        System.out.print(lat);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest);
    }
//    code adapted from https://developer.android.com/training/location/change-location-settings
    protected void createLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
}
}
