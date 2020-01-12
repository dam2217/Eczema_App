package com.example.eczema_app.ui.settings;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceFragmentCompat;

import com.example.eczema_app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//
//
//Code developed from https://developer.android.com/guide/topics/ui/settings
//
public class SettingsFragment extends PreferenceFragmentCompat {

        private String lat = "lllll";
        private String lon;
        private FusedLocationProviderClient mFusedLocationClient;
        private int PERMISSION_ID = 44;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
            System.out.println("asdfghjkl");
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
            getLastLocation();

        }




        @SuppressLint("MissingPermission")
        private void getLastLocation(){
                if (checkPermissions()) {
                    if (isLocationEnabled()) {
                        mFusedLocationClient.getLastLocation().addOnCompleteListener(
                            new OnCompleteListener<Location>() {
                                @Override
                                public void onComplete(@NonNull Task<Location> task) {
                                    Location location = task.getResult();
                                    if (location == null) {
                                        requestNewLocationData();
                                        System.out.println("bbbb");
                                    } else {
                                        lat = Double.toString(location.getLatitude());
                                        System.out.println(lat);
                                        System.out.println("cccccc");
                                        lon = Double.toString(location.getLongitude());
                                        System.out.println(lon);
                                    }
                                }
                            });
                    } else {
                        Toast.makeText(this.getContext(), "Turn on location sdfghjnmk,", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                } else {
                    requestPermissions();

                    System.out.println("meg");
                }
        }

        @SuppressLint("MissingPermission")
        private void requestNewLocationData(){

                LocationRequest mLocationRequest = new LocationRequest();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                mLocationRequest.setInterval(0);
                mLocationRequest.setFastestInterval(0);
                mLocationRequest.setNumUpdates(1);

                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());
                mFusedLocationClient.requestLocationUpdates(
                        mLocationRequest, mLocationCallback,
                        Looper.myLooper()
                );

        }

        private LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                        Location mLastLocation = locationResult.getLastLocation();
                        lat = Double.toString(mLastLocation.getLatitude());
                        lon = Double.toString(mLastLocation.getLongitude());
                }
        };

        private boolean checkPermissions() {
                if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        return true;
                }
                return false;
        }

        private void requestPermissions() {
                ActivityCompat.requestPermissions(
                        this.getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_ID
                );
        }

        private boolean isLocationEnabled() {
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                        LocationManager.NETWORK_PROVIDER
                );
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if (requestCode == PERMISSION_ID) {
                        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                getLastLocation();
                        }
                }
        }

        @Override
        public void onResume(){
                super.onResume();
                if (checkPermissions()) {
                        getLastLocation();
                }

        }

}

