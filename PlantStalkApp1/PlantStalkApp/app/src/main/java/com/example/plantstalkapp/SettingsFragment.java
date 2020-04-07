package com.example.plantstalkapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dfrobot.angelo.blunobasicdemo.R;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//notifications
import com.dfrobot.angelo.blunobasicdemo.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


//CHARTS ACTIVITY:
public class SettingsFragment extends Fragment {
    Context context;
    private SettingsFragmentListener listener;
    //private EditText editText;
    //private Button buttonOk;
    //attempt at timed notifications

    public interface SettingsFragmentListener {
       // void onInputSettingsSent(CharSequence input);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        context = v.getContext();
        Button bluetoothButton = v.findViewById(R.id.bluetoothButton);

        bluetoothButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("BUTTONPRESSED");
                Intent intent = new Intent(context, BluetoothActivity.class);
                startActivity(intent);
                System.out.println(("WENTTOACTIVITY?"));
            }
        });

        return v;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SettingsFragmentListener){
            listener = (SettingsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement SettingsFragmentListener you goofball");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}



