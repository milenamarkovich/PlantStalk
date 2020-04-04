package com.example.plantstalkapp;

// all created for notification channels to send on

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notifications extends Application {
    //test notification channels
    public static final String CHANNEL_1_ID = "notification";
    public static final String CHANNEL_2_ID = "water";


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is the test notification channel");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "water",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is the water channel");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}