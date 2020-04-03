package com.example.plantstalkapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dfrobot.angelo.blunobasicdemo.R;

import org.w3c.dom.Text;

public class SettingsFragment extends Fragment {
    private SettingsFragmentListener listener;
    private EditText editText;
    private Button buttonOk;

    TextView text;
    //public CharSequence input;

    public interface SettingsFragmentListener {
        void onInputSettingsSent(CharSequence input);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       View v = inflater.inflate(R.layout.fragment_settings, container, false);

        //editText = text.findViewById(R.id.edit_text);
        editText = v.findViewById(R.id.edit_text);
        buttonOk = v.findViewById(R.id.button_ok);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CharSequence input = editText.getText();
                listener.onInputSettingsSent(input);

            }
        });

        return v;
    }

    public void updateEditText(CharSequence nexttext){
        editText.setText(String.valueOf(nexttext));
    }
//code below this crashes app

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





