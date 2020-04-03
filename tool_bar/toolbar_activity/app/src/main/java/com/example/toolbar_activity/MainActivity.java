package com.example.toolbar_activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

//added for notifications
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import static com.example.toolbar_activity.Notifications.CHANNEL_1_ID;
import static com.example.toolbar_activity.Notifications.CHANNEL_2_ID;
// end of notification code addition

// implements View.OnClickListener --> used for button checking
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextMessage;

    Button WaterButton;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_settings);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_history:
                    mTextMessage.setText(R.string.title_history);
                    return true;
                case R.id.navigation_battery:
                    mTextMessage.setText(R.string.title_battery);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //notification stuff

        WaterButton=findViewById(R.id.WaterButton);
        WaterButton.setOnClickListener(this);
        notificationManager = NotificationManagerCompat.from(this);
        // end notification stuff

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //notification stuff from here on out
    private NotificationManagerCompat notificationManager;

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.WaterButton){

            String title = "Plant Watered";
            String message = "Thank you for watering me <3 I guess I'll see you tomorrow";


            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();
            notificationManager.notify(1, notification);
        }
        if(view.getId() == R.id.navigation_settings){
            setContentView(R.layout.activity_settings);
        }
        if(view.getId() == R.id.navigation_home){
            setContentView(R.layout.activity_main);
        }
    }
}
