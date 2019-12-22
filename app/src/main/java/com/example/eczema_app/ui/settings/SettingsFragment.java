package com.example.eczema_app.ui.settings;
import android.app.TaskInfo;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.eczema_app.R;
import com.google.android.gms.location.LocationRequest;


//Code developed from https://developer.android.com/guide/topics/ui/settings

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey){
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        Preference locationsettings = findPreference("launch_settings");

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 0);
        locationsettings.setIntent(intent);


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
