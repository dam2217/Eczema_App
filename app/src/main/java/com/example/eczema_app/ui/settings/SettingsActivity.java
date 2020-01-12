package com.example.eczema_app.ui.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.preference.PreferenceManager;

//code adapted from https://developer.android.com/guide/topics/ui/settings

public class SettingsActivity extends PreferenceActivity {
//
//    private String lat = "lllll";
//    private String lon;
//    private FusedLocationProviderClient mFusedLocationClient;
//    private int PERMISSION_ID = 44;

    private SharedPreferences prefs;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//            };//        System.out.println(lat);

//        System.out.println(lat);

//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        getLastLocation();
//        System.out.println(lat);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.nav_settings, new SettingsFragment())
//
//
//              .commit();

//        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
//                SharedPreferences.OnSharedPreferenceChangeListener() {
//                    @Override
//                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
//                                                          String key) {
//                        // your stuff here
//                    }
//                };


    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                int flag = 1;
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(listener);

    }


//
//    @SuppressLint("MissingPermission")
//    private void getLastLocation(){
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//                mFusedLocationClient.getLastLocation().addOnCompleteListener(
//                        new OnCompleteListener<Location>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Location> task) {
//                                Location location = task.getResult();
//                                if (location == null) {
//                                    requestNewLocationData();
//                                    System.out.println("bbbb");
//                                } else {
//                                    lat = Double.toString(location.getLatitude());
//                                    System.out.println(lat);
//                                    System.out.println("cccccc");
//                                    lon = Double.toString(location.getLongitude());
//                                }
//                            }
//                        }
//                );
//            } else {
//                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
//            }
//        } else {
//            requestPermissions();
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    private void requestNewLocationData(){
//
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(0);
//        mLocationRequest.setFastestInterval(0);
//        mLocationRequest.setNumUpdates(1);
//
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//        mFusedLocationClient.requestLocationUpdates(
//                mLocationRequest, mLocationCallback,
//                Looper.myLooper()
//        );
//
//    }
//
//    private LocationCallback mLocationCallback = new LocationCallback() {
//        @Override
//        public void onLocationResult(LocationResult locationResult) {
//            Location mLastLocation = locationResult.getLastLocation();
//            lat = Double.toString(mLastLocation.getLatitude());
//            lon = Double.toString(mLastLocation.getLongitude());
//        }
//    };
//
//    private boolean checkPermissions() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        return false;
//    }
//
//    private void requestPermissions() {
//        ActivityCompat.requestPermissions(
//                this,
//                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
//                PERMISSION_ID
//        );
//    }
//
//    private boolean isLocationEnabled() {
//        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
//                LocationManager.NETWORK_PROVIDER
//        );
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_ID) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLastLocation();
//            }
//        }
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        if (checkPermissions()) {
//            getLastLocation();
//        }
//
//    }

}



