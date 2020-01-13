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
    String data = "";
    private ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();


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

        final CardView card1 = findViewById(R.id.cardView1);
        final CardView card2 = findViewById(R.id.cardView2);
        final CardView card3 = findViewById(R.id.cardView3);

        int entries = logList.size();
        System.out.println(entries);
        if (entries < 3){
            if (entries == 1){
                card3.setVisibility(View.GONE);
                card2.setVisibility(View.GONE);
            }else if (entries == 2){
                card3.setVisibility(View.GONE);
            }
        }


        final TextView date1 = findViewById((R.id.date_1));
//       TODO: SET DATE TO THE ONE OF THE LOG FROM DATABASE
//        System.out.println(logList.get(entries - 1).date);
//        date1.setText(logList.get(entries - 1).date);

        final TextView noLocations1 = findViewById(R.id.no_locations_1);

        //   TODO: UNCOMMENT THE BELOW AND INCLUDE THE ARRAY LENGTH TO SHOW THE NUMBER OF INFLAMED AREAS FROM DATABASE
        //   String numberOfLocations1 = String.valueOf("Length of array holding affected areas");
        //   noLocations1.setText("Locations: " + numberOfLocations1);

        final TextView treatment1 = findViewById(R.id.treatment_1);

//        TODO: UNCOMMENT THE BELOW AND SET THE TREATMENT TYPE FROM DATABASE, LIST THE TREATED AREAS FROM THE DATABASE -
//         ****** REPRESENTS LINE THAT NEEDS CHANGING
//      ******  if ("Treatment used is true"){
//            treatment1.setText("Treatment Used");
//            treatment1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//       *******      String treatmentType = "treatment type"
//                    inflammationLocations.setTitle("Treatment: " + treatmentType);
//       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
//                    inflammationLocations.show();
//                }
//            });
//        } else {
//            treatment1.setText("No Treatment Used");
//        }

        final TextView notes1None = findViewById(R.id.notes_1_none);
        final Button notes1 = findViewById(R.id.notes_1);
//        TODO: UNCOMMENT BELOW,
//         CHANGE INPUT FOR IF STATEMENT TO RELEVANT FIELD FROM DATABASE,
//         GET NOTES FROM DATABASE
//     *******   if ("notes field is empty"){
//            notes1.setVisibility(View.GONE);
//            notes1None.setVisibility(View.VISIBLE);
//        } else {
//            notes1.setVisibility(View.VISIBLE);
//            notes1None.setVisibility(View.GONE);
//            notes1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//                    inflammationLocations.setTitle("Notes");
//           ******** inflammationLocations.setMessage("notes for most recent entry");
//                    inflammationLocations.show();
//                }
//            });
//        }




        //creating activity of AlertDialog
        noLocations1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
// TODO: LIST OF LOCATIONS AND SEVERITY FROM DATABASE
                inflammationLocations.setMessage("Insert list of locations and severity for most recent entry ");
                inflammationLocations.show();
            }
        });



        final TextView date2 = findViewById((R.id.date_2));
//        TODO: BELOW
        date2.setText("get date");

        final TextView noLocations2 = findViewById(R.id.no_locations_2);
        String numberOfLocations2 = String.valueOf("Length of array holding affected areas");
        noLocations2.setText("Locations: " + numberOfLocations2);

        final TextView treatment2 = findViewById(R.id.treatment_2);

//        TODO: UNCOMMENT THE BELOW AND SET THE TREATMENT TYPE FROM DATABASE, LIST THE TREATED AREAS FROM THE DATABASE
//       ***** if ("Treatment used is true"){
//            treatment2.setText("Treatment Used");
//            treatment2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//         ******  String treatmentType = "treatment type"
////                    inflammationLocations.setTitle("Treatment: " + treatmentType);
////       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
////                    inflammationLocations.show();
//            }
//        });
//        } else {
//            treatment2.setText("No Treatment Used");
//        }

        final TextView notes2None = findViewById(R.id.notes_2_none);
        final Button notes2 = findViewById(R.id.notes_2);
//        TODO: UNCOMMENT BELOW,
//         CHANGE INPUT FOR IF STATEMENT TO RELEVANT FIELD FROM DATABASE,
//         GET NOTES FROM DATABASE

//  ******  if ("notes field is empty"){
//            notes2.setVisibility(View.GONE);
//            notes2None.setVisibility(View.VISIBLE);
//        } else {
//            notes2.setVisibility(View.VISIBLE);
//            notes2None.setVisibility(View.GONE);
//            notes2.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
        //                inflammationLocations.setTitle("Notes");
        //       ******   inflammationLocations.setMessage("notes for  second entry");
        //                inflammationLocations.show();
        //            }
        //        });
//        }


        noLocations2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
//      TODO: GET LIST OF LOCATIONS AND SEVERITY FOR SECOND-MOST RECENT ENTRY IN DATABASE
                inflammationLocations.setMessage("Insert list of locations and severity for second entry ");
                inflammationLocations.show();
            }
        });


        final TextView date3 = findViewById((R.id.date_3));
//        TODO: BELOW
        date3.setText("get date");

        final TextView noLocations3 = findViewById(R.id.no_locations_3);
//        TODO: BELOW
        String numberOfLocations3 = String.valueOf("Length of array holding affected areas");
        noLocations3.setText("Locations: " + numberOfLocations3);

        final TextView treatment3 = findViewById(R.id.treatment_3);
//        TODO: BELOW
//       ******* if ("Treatment used is true"){
//            treatment3.setText("Treatment Used");
//            treatment3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
//               ******  String treatmentType = "treatment type"
//////                    inflammationLocations.setTitle("Treatment: " + treatmentType);
//////       *******      inflammationLocations.setMessage("List of treated areas for most recent entry");
//////                    inflammationLocations.show();
//            }
//        });
//        } else {
//            treatment3.setText("No Treatment Used");
//        }

        final Button notes3 = findViewById(R.id.notes_3);
        final TextView notes3None = findViewById(R.id.notes_3_none);
//        TODO: BELOW
//  ******      if ("notes field is empty"){
//            notes3.setVisibility(View.GONE);
//            notes3None.setVisibility(View.VISIBLE);
//        } else {
//            notes3.setVisibility(View.VISIBLE);
//            notes3None.setVisibility(View.GONE);
        //        notes3.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
        //                inflammationLocations.setTitle("Notes");
        //     ******     inflammationLocations.setMessage("notes for third entry");
        //                inflammationLocations.show();
        //            }
        //        });
//        }





        noLocations3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(MainActivity.this, R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
//                TODO: BELOW
                inflammationLocations.setMessage("Insert list of locations and severity for third entry");
                inflammationLocations.show();
            }
        });
        
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

                    System.out.println("Location from db: " + LogFromDB.location);

                    logList.add(LogFromDB);
                }

                Log.i("length", String.valueOf(logList.size()));
                System.out.println(logList.get(1).location);

                return data;
            } catch (Exception e) {
                Log.i("caught?", "unfortunately");
                return new String("Exception: " + e.getMessage());
            }
        }

    }


}
