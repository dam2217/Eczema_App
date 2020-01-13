package com.example.eczema_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.eczema_app.ui.HttpCommunicate;
import com.example.eczema_app.ui.LogEntrySerial;
import com.example.eczema_app.ui.ReceiveData;
import com.example.eczema_app.ui.home.LoggedDataEntry;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private AppBarConfiguration mAppBarConfiguration;
    SharedPreferences sharedPreferences;
    NavigationView navigationView;
    View hview;
    TextView personName;
    TextView personEmail;
//    List<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReceiveData().execute();
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
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);

        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

//        logList = getIntent().getParcelableExtra("logList");


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

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
