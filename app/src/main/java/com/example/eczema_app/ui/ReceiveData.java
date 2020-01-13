package com.example.eczema_app.ui;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.eczema_app.ui.home.LoggedDataEntry;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ReceiveData extends AsyncTask<String, String, String> {
    ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();
    String data = "";

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
