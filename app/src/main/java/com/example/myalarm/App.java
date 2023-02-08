package com.example.myalarm;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("myapp","ALARM",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Touch This For Stop Alarm");
            if (notificationManager != null)
            notificationManager.createNotificationChannel(notificationChannel);
        }

    }
}
