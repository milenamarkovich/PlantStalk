package com.example.plantstalkapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dfrobot.angelo.blunobasicdemo.R;

import java.util.ArrayList;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.example.plantstalkapp.Notifications.CHANNEL_1_ID;
import static com.example.plantstalkapp.Notifications.CHANNEL_2_ID;


//implements SettingsFragment.SettingsFragmentListener, HomeFragment.HomeFragmentListener
public class MainActivity extends AppCompatActivity
        implements SettingsFragment.SettingsFragmentListener, HomeFragment.HomeFragmentListener{



    //for notifications
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // for task bar
        //private FrameLayout fragmentContainer;
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        //for notifications
        notificationManager = NotificationManagerCompat.from(this);


        //plant name
        SettingsFragment fragmentSettings = new SettingsFragment();
        HomeFragment homeFragment = new HomeFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit(); // so we open on the home tab
    }

//code for interfragment communication of plant name not working

    @Override
    public void onInputHomeSent(CharSequence input) {
        //fragmentSettings.updateEditText(input);
    }


    public void onInputSettingsSent(CharSequence input) {
        //homeFragment.updateEditText(input);
    }

    // call for notification from HomeFragment working!
    public void notification() {
        String title = "Plant Watered";
        String message = "thank you for watering me :)";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_home_black_24dp)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
        notificationManager.notify(1, notification);
    }

    //for the second notification
    public void timedNotification(){
        String title = "hello there sir";
        String message = "notification channel 2 works";

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);

    }



// task bar
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_light:
                            selectedFragment = new LightFragment();
                            break;
                        case R.id.nav_water:
                            selectedFragment = new WaterFragment();
                            break;
                        case R.id.nav_temp:
                            selectedFragment = new TempFragment();
                            break;
                        case R.id.nav_Settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;  //to select clicked item
                 }
    };

}
