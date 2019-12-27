package com.example.eczema_app.ui.home;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eczema_app.R;
import com.example.eczema_app.ui.settings.SettingsFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String CHANNEL_ID = "testt";
    private NotificationManagerCompat notiManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button testeroni = root.findViewById(R.id.testeroni);
        testeroni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnotification();
                System.out.println("test");
            }
        });

        return root;
//        notiManager = NotificationManagerCompat.from(this);
    }

    private void addnotification(){
        System.out.println("entered");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("XMA Logbook")
                .setContentText("Don't forget to log your symptoms today!");
        Intent notificationsIntent = new Intent(getActivity(), HomeFragment.class);
        System.out.println("fuck");
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(),0, notificationsIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        System.out.println("help");
        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
        System.out.println("yikes");
    }
//    private void addnotification(){
//        createNotificationChannel();
//        Notification notification = new NotificationCompat.Builder(getContext(),CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("XMA Logbook")
//                .setContentText("Don't forget to log your symptoms today!")
//                .build();
//
//        notiManager.notify(1, notification);
////        Intent notificationsIntent = new Intent(getActivity(), HomeFragment.class);
////        PendingIntent contentIntent = PendingIntent.getActivity(getContext(),0, notificationsIntent,PendingIntent.FLAG_UPDATE_CURRENT);
////        builder.setContentIntent(contentIntent);
////
////        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
////        manager.notify(0, builder.build());
//    }
}