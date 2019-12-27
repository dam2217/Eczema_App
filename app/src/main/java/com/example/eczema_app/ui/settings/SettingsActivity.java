package com.example.eczema_app.ui.settings;


import android.Manifest;
import android.app.Notification;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.eczema_app.MainActivity;
import com.example.eczema_app.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import static java.security.AccessController.getContext;

//code adapted from https://developer.android.com/guide/topics/ui/settings

public class SettingsActivity extends AppCompatActivity{
    private NotificationManagerCompat notiManager;
    public static String CHANNEL_ID = "testt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_settings, new SettingsFragment())
                .commit();
        notiManager = NotificationManagerCompat.from(this);
        addnotification();
    }
    private void addnotification(){
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("XMA Logbook")
                .setContentText("Don't forget to log your symptoms today!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        notiManager.notify(1, notification);
    }
}



