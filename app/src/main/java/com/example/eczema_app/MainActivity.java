package com.example.eczema_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

    String data = "";
    ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
//        TextView title = findViewById(R.id.text_home);
//        title.setText("Your Recent Logs - Fetching Data");


        new ReceiveData().execute();








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

        new ReceiveData().execute();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                System.out.println("Last location: " + logList.get(logList.size()-1).location);
                Log.i("length in main", String.valueOf(logList.size()));

                updateHistory();
            }
        }, 5000);   //5 seconds

    }

    public void updateHistory(){
        TextView title = findViewById(R.id.text_home);
        title.setText("Your Recent Logs");

        TextView lastDate = findViewById(R.id.date_1);
        TextView secondLastDate = findViewById(R.id.date_2);
        TextView thirdLastDate = findViewById(R.id.date_3);
        String lastDateText = logList.get(logList.size()-1).date + " - Severity Score " + logList.get(logList.size()-1).severityScore;
        String secondLastDateText = logList.get(logList.size()-2).date + " - Severity Score " + logList.get(logList.size()-2).severityScore;
        String thirdLastDateText = logList.get(logList.size()-3).date + " - Severity Score " + logList.get(logList.size()-3).severityScore;
        lastDate.setText(lastDateText);
        secondLastDate.setText(secondLastDateText);
        thirdLastDate.setText(thirdLastDateText);

        TextView lastTreatment = findViewById(R.id.treatment_1);
        TextView secondLastTreatment = findViewById(R.id.treatment_2);
        TextView thirdLastTreatment = findViewById(R.id.treatment_3);
        String lastTreatmentText = "Treatment: " + logList.get(logList.size()-1).treatmentUsed + "\n" + "Location: " + logList.get(logList.size()-1).location;
        String secondLastTreatmentText = "Treatment: " + logList.get(logList.size()-2).treatmentUsed + "\n" + "Location: " + logList.get(logList.size()-2).location;
        String thirdLastTreatmentText = "Treatment: " + logList.get(logList.size()-3).treatmentUsed + "\n" + "Location: " + logList.get(logList.size()-3).location;
        lastTreatment.setText(lastTreatmentText);
        secondLastTreatment.setText(secondLastTreatmentText);
        thirdLastTreatment.setText(thirdLastTreatmentText);

        final TextView lastNotes = findViewById(R.id.notes_1_none);
        final TextView secondLastNotes = findViewById(R.id.notes_2_none);
        final TextView thirdLastNotes = findViewById(R.id.notes_3_none);
        final String lastNotesText = logList.get(logList.size()-1).notes;
        final String secondLastNotesText = logList.get(logList.size()-2).notes;
        final String thirdLastNotesText = logList.get(logList.size()-3).notes;

        final Button lnbutton = findViewById(R.id.notes_1);
        lnbutton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lastNotes.setText(lastNotesText);
                                            lnbutton.setVisibility(View.GONE);
                                            lnbutton.invalidate();
                                        }
                                    });

        final Button slnbutton = findViewById(R.id.notes_2);
        slnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondLastNotes.setText(secondLastNotesText);
                slnbutton.setVisibility(View.GONE);
                slnbutton.invalidate();
            }
        });

        final Button tlnbutton = findViewById(R.id.notes_3);
        tlnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdLastNotes.setText(thirdLastNotesText);
                tlnbutton.setVisibility(View.GONE);
                tlnbutton.invalidate();
            }
        });

    }

    public void onSharedPreferenceChanged(SharedPreferences prefs,
                                          String rootKey) {
        final TextView personName = findViewById(R.id.personName);
        final TextView personEmail = findViewById(R.id.email);
        personEmail.setText(sharedPreferences.getString("email", ""));
        personName.setText(sharedPreferences.getString("name", ""));
    }
    public class ReceiveData extends AsyncTask<String, String, String> {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.i("data received?", "yes");
                data = HttpCommunicate.sendGet("https://eczema-app.herokuapp.com/eczemadatabase");
                System.out.println("data: " + data);

                String[] splits = data.split("split");

                for (int i = 0; i < splits.length; i++) {
                    String individualReceived = "{" + splits[i] + "}";
                    Gson gson = new Gson();
                    LogEntrySerial LogFromDBserial = gson.fromJson(individualReceived, LogEntrySerial.class);
                    LoggedDataEntry LogFromDB = new LoggedDataEntry(LogFromDBserial.date, LogFromDBserial.time,
                            LogFromDBserial.hf, LogFromDBserial.hb, LogFromDBserial.tf, LogFromDBserial.tb,
                            LogFromDBserial.raf, LogFromDBserial.rab, LogFromDBserial.laf, LogFromDBserial.lab,
                            LogFromDBserial.rlf, LogFromDBserial.rlb, LogFromDBserial.llf, LogFromDBserial.llb,
                            LogFromDBserial.treatmentYorN, LogFromDBserial.treatmentUsed, LogFromDBserial.temperature,
                            LogFromDBserial.humidity, LogFromDBserial.pollutionLevel, LogFromDBserial.pollenLevel,
                            LogFromDBserial.location, LogFromDBserial.hfTreated, LogFromDBserial.hbTreated,
                            LogFromDBserial.tfTreated, LogFromDBserial.tbTreated, LogFromDBserial.rafTreated,
                            LogFromDBserial.rabTreated, LogFromDBserial.lafTreated, LogFromDBserial.labTreated,
                            LogFromDBserial.rlfTreated, LogFromDBserial.rlbTreated, LogFromDBserial.llfTreated,
                            LogFromDBserial.llbTreated, LogFromDBserial.notes);

//                    System.out.println("Location from db: " + LogFromDB.location);

                    logList.add(LogFromDB);
                }

                Log.i("length in receive data", String.valueOf(logList.size()));
                System.out.println(logList.get(1).location);

                return data;
            } catch (Exception e) {
                Log.i("caught?", "unfortunately");
                return new String("Exception: " + e.getMessage());
            }
        }

    }



}
