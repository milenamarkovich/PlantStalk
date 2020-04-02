package com.example.bluetoothscan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.dfrobot.angelo.blunobasicdemo.R;

import java.util.Arrays;

public class MainActivity<tempArray, type_counter> extends BlunoLibrary {
    private Button buttonScan;
    private Button buttonSerialSend;
    private EditText serialSendText;
    private TextView serialReceivedText;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "The permission to get BLE location data is required", Toast.LENGTH_SHORT).show();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {
            Toast.makeText(this, "Location permissions already granted", Toast.LENGTH_SHORT).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onCreateProcess();                                                        //onCreate Process by BlunoLibrary


        serialBegin(115200);                                                    //set the Uart Baudrate on BLE chip to 115200

        serialReceivedText = (TextView) findViewById(R.id.serialReveicedText);    //initial the EditText of the received data

        buttonScan = (Button) findViewById(R.id.buttonScan);                    //initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                buttonScanOnClickProcess();                                        //Alert Dialog for selecting the BLE device
            }
        });
    }

    protected void onResume() {
        super.onResume();
        System.out.println("BlUNOActivity onResume");
        onResumeProcess();                                                        //onResume Process by BlunoLibrary
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResultProcess(requestCode, resultCode, data);                    //onActivityResult Process by BlunoLibrary
        super.onActivityResult(requestCode, resultCode, data);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();                                                        //onPause Process by BlunoLibrary
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onStop() {
        super.onStop();
        onStopProcess();                                                        //onStop Process by BlunoLibrary
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();                                                        //onDestroy Process by BlunoLibrary
    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
        switch (theConnectionState) {                                            //Four connection state
            case isConnected:
                buttonScan.setText("Connected");
                break;
            case isConnecting:
                buttonScan.setText("Connecting");
                break;
            case isToScan:
                buttonScan.setText("Scan");
                break;
            case isScanning:
                buttonScan.setText("Scanning");
                break;
            case isDisconnecting:
                buttonScan.setText("isDisconnecting");
                break;
            default:
                break;
        }
    }

    int tempArray[];    //declaring array
    int moistureArray[];    //declaring array
    int lightArray[];    //declaring array

    public void onSerialReceived(String theString) {//Once connection data received, this function will be called
        int sensor_val;
        try {
            sensor_val = Integer.parseInt(theString);
        } catch (
                NumberFormatException e) {
            sensor_val = 0;
        }
        int type_counter = 0;

        if (type_counter == 0) {
            tempArray = new int[sensor_val];  // allocating memory to array
            System.out.println("Temp values: " + Arrays.toString(tempArray));
            type_counter ++;
        }
        if (type_counter == 1) {
            moistureArray = new int[sensor_val];  // allocating memory to array
            System.out.println("Moisture values: " + Arrays.toString(moistureArray));
            type_counter ++;
        }
        if (type_counter == 2) {
            lightArray = new int[sensor_val];  // allocating memory to array
            System.out.println("Light values: " + Arrays.toString(lightArray));
            type_counter = 0;
        }

        tempArray.toString();
        System.out.println("Temp values: " + Arrays.toString(tempArray));
        moistureArray.toString();
        lightArray.toString();



            // TODO Auto-generated method stub
            serialReceivedText.append(theString);                            //append the text into the EditText
        //The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.

        ((ScrollView) serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);

    }
}



