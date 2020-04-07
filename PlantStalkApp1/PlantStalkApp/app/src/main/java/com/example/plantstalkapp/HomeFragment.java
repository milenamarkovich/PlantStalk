package com.example.plantstalkapp;

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

import static com.example.plantstalkapp.Notifications.CHANNEL_1_ID;
import static com.example.plantstalkapp.Notifications.CHANNEL_2_ID;


public class HomeFragment extends Fragment {
    private HomeFragmentListener listener;
   // private EditText editText;

    //notifications
    Button waterButton;
    TextView notificationTitle;
    TextView notificationContent;
    //CharSequence input;

    private NotificationManagerCompat notificationManager;


    // for notification send. sets up going over to mainactivity from fragment
    public interface HomeFragmentListener {
        void onInputHomeSent(CharSequence input);
        void notification();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //notificationTitle.findViewById(R.id.notificationTitle);
        //notificationContent.findViewById(R.id.notificationContent);



        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //editText =v.findViewById(R.id.edit_text);
        waterButton = v.findViewById(R.id.waterButton);


        notificationTitle = v.findViewById(R.id.notificationTitle);
        notificationContent = v.findViewById(R.id.notificationContent);



        waterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notificationTitle.setText("Plant Watered");
                notificationContent.setText("thank you for watering me :)");
                listener.notification();
            }
        });



        return v;
    }





    /*public void updateEditText(CharSequence newText){
        editText.setText(newText);
    }*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragmentListener){
            listener = (HomeFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement HomeFragmentListener you goofball");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


}


