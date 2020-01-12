package com.example.eczema_app;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.eczema_app.ui.home.LoggedDataEntry;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sharedPreferences;
    NavigationView navigationView;
    View hview;
    TextView personName;
    TextView personEmail;
    List<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logList = getIntent().getParcelableExtra("logList");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        hview = navigationView.getHeaderView(0);
        personName = hview.findViewById(R.id.personName);
        personEmail = hview.findViewById(R.id.email);







        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.newLog, R.id.nav_graphs, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        final AlertDialog inflammationLocations = new AlertDialog.Builder(this, R.style.CustomDialogTheme).create();
        inflammationLocations.setTitle("Inflammations");
        inflammationLocations.setMessage("Insert list of locations and severity ");
//        show on click of locations button
//        inflammationLocations.show();

        final AlertDialog treatments = new AlertDialog.Builder(this, R.style.CustomDialogTheme).create();
        treatments.setTitle("Treatment: **Name of Treatment**");
        treatments.setMessage("Insert list of where treatment used");
//        treatments.show();

        final AlertDialog notes = new AlertDialog.Builder(this, R.style.CustomDialogTheme).create();
        notes.setTitle("Notes");
        notes.setMessage("Insert Notes");
//        notes.show();
        
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
//
//    protected void createLocationRequest() {
//        LocationRequest locationRequest = LocationRequest.create();
//        locationRequest.setInterval(10000);
//        locationRequest.setFastestInterval(5000);
//        locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
//    }

    @Override
    protected void onResume(){
        super.onResume();

        String userName = new String();
        String emailAddress = new String();

        navigationView = findViewById(R.id.nav_view);
        hview = navigationView.getHeaderView(0);
        personName = hview.findViewById(R.id.personName);
        personEmail = hview.findViewById(R.id.email);

        userName = sharedPreferences.getString("name","");
        emailAddress = sharedPreferences.getString("email", "");
//        Bundle extras = getIntent().getExtras();
//        if(extras !=null) {
//            userName = extras.getString("KEY");
//        }

        personName.setText(userName);
        personEmail.setText(emailAddress);

    }

    public void onSharedPreferenceChanged(SharedPreferences prefs,
                                          String rootKey) {
        final TextView personName = findViewById(R.id.personName);
        final TextView personEmail = findViewById(R.id.email);
        personEmail.setText(sharedPreferences.getString("email", ""));
        personName.setText(sharedPreferences.getString("name", ""));
    }


}
