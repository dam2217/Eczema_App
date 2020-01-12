package com.example.eczema_app.ui.settings;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.eczema_app.MainActivity;
import com.example.eczema_app.R;

import java.util.Calendar;

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
//        addnotification();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(SettingsActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) SettingsActivity.this.getSystemService(SettingsActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
//    private void addnotification(){
//        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("XMA Logbook")
//                .setContentText("Don't forget to log your symptoms today!")
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build();
//
//        notiManager.notify(1, notification);
//    }

    public class AlarmReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            long when = System.currentTimeMillis();
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(context,MainActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


            NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                    context).setSmallIcon(R.drawable.xmalogo)
                    .setContentTitle("XMA Logbook")
                    .setContentText("Don't forget to log your symptoms today!")
                    .setAutoCancel(true).setWhen(when)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
            if (notificationManager != null) {
                notificationManager.notify(1, mNotifyBuilder.build());
            }

        }

    }

    public class MyNewIntentService extends IntentService {
        private static final int NOTIFICATION_ID = 3;

        public MyNewIntentService() {
            super("MyNewIntentService");
        }

        @Override
        protected void onHandleIntent(Intent intent) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setContentTitle("XMA Logbook");
            builder.setContentText("Don't forget to log your symptoms today!");
            builder.setSmallIcon(R.drawable.xmalogo);
            Intent notifyIntent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //to be able to launch your activity from the notification
            builder.setContentIntent(pendingIntent);
            Notification notificationCompat = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                notificationCompat = builder.build();
            }
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(NOTIFICATION_ID, notificationCompat);
        }
    }

}



