package com.example.eczema_app.ui.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.preference.PreferenceManager;

//code adapted from https://developer.android.com/guide/topics/ui/settings

public class SettingsActivity extends PreferenceActivity {

    private SharedPreferences prefs;
    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}



