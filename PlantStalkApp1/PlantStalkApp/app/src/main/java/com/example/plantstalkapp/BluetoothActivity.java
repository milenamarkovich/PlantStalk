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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dfrobot.angelo.blunobasicdemo.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


//BLUETOOTH MAIN ACTIVITY
public class BluetoothActivity extends BlunoLibrary implements SettingsFragment.SettingsFragmentListener{

    private Button bluetoothButton;


    public void onInputSettingsSent(CharSequence input) {
        //homeFragment.updateEditText(input);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "The permission to get BLE location data is required", Toast.LENGTH_SHORT).show();
            }else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }else{
            Toast.makeText(this, "Location permissions already granted", Toast.LENGTH_SHORT).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200


                buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device

    }

    protected void onResume(){
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();														//onResume Process by BlunoLibrary
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onStop() {
        super.onStop();
        onStopProcess();														//onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
        switch (theConnectionState) {//Four connection state
            case isConnected:
               if(bluetoothButton!=null){
               bluetoothButton.setText("Connected");
                break;}
            case isConnecting:
                if(bluetoothButton!=null){
                bluetoothButton.setText("Connecting");
                break;}
            case isToScan:
                if(bluetoothButton!=null){
                bluetoothButton.setText("Scan");
                break;}
            case isScanning:
                if(bluetoothButton!=null){
                bluetoothButton.setText("Scanning");
                break;}
            case isDisconnecting:
                if(bluetoothButton!=null){
                bluetoothButton.setText("isDisconnecting");
                break;}
            default:
                break;
        }
    }
    //output bluetooth array
    ArrayList TempArray = new ArrayList();
    ArrayList MoistureArray = new ArrayList();
    ArrayList LightArray = new ArrayList();

    int type_count = 0;


    @Override
    public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
        // TODO Auto-generated method stub

        float sensor_val;
        try {
            sensor_val = Float.parseFloat(theString);

            if (sensor_val>5 && sensor_val <=40){
                TempArray.add(sensor_val);
                type_count++;
            }
            else if (sensor_val>260){
                MoistureArray.add(sensor_val);
                type_count++;
            }
            else if (sensor_val>0.0 && sensor_val<=5.0){
                LightArray.add(sensor_val);
                type_count = 0;
            }

        } catch (NumberFormatException e){ //empty ERROR
            sensor_val = 0; System.out.println("ERROR");
        }

        System.out.println(TempArray);
        System.out.println(MoistureArray);
        System.out.println(LightArray);

    }
}


