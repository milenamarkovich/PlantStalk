package com.example.plantstalkapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

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

import static com.example.plantstalkapp.Notifications.CHANNEL_1_ID;
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

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dfrobot.angelo.blunobasicdemo.R;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;

public class MainActivity extends AppCompatActivity implements SettingsFragment.SettingsFragmentListener, HomeFragment.HomeFragmentListener{

    private FrameLayout fragmentContainer;

    //plant name
    private SettingsFragment fragmentSettings;
    private HomeFragment homeFragment;

    //for notifications
    private NotificationManagerCompat notificationManager;

    Button btnBarChart, btnPieChart;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);

        //for notifications
        notificationManager = NotificationManagerCompat.from(this);


        fragmentSettings = new SettingsFragment();
        homeFragment = new HomeFragment();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit(); // so we open on the home tab


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = findViewById(R.id.barchart);

        btnBarChart = findViewById(R.id.btnBarChart);
        //btnPieChart = findViewById(R.id.btnPieChart);
        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, BarChartActivity.class);
                startActivity(I);
            }
        });
        /*btnPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, PieChartActivity.class);
                startActivity(I);
            }
        });*/
    }


    //code for interfragment communication of plant name not working
    @Override
    public void onInputHomeSent(CharSequence input) {
        fragmentSettings.updateEditText(input);
    }

    @Override
    public void onInputSettingsSent(CharSequence input) {
        homeFragment.updateEditText(input);
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




   
    

