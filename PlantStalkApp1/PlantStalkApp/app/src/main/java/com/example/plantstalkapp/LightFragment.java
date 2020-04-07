package com.example.plantstalkapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfrobot.angelo.blunobasicdemo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


//CHARTS ACTIVITY
public class LightFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_light, container, false);

        Context context = v.getContext();


        BarChart chart = v.findViewById(R.id.barchartLight);


            //dummy data array:

            ArrayList <Float> Data = new ArrayList <Float>();

            Data.add(0.43f);
            Data.add(2.67f);
            Data.add(3.54f);
            Data.add(3.23f);
            Data.add(2.80f);
            Data.add(3.12f);
            Data.add(4.01f);



            ArrayList Temp = new ArrayList();

            for (int i = 0; i < Data.size(); i++) {
                Temp.add(new BarEntry(Data.get(i), i));
            }


            ArrayList day = new ArrayList();

        for(int i = 0; i<Data.size(); i++){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
            LocalDateTime now = LocalDateTime.now();
            int today = (int) (new Date().getDate());
            if(today-i>0){
                day.add(0,Integer.toString(today - i));}
            else if(today-i==0){
                day.add(0,"31");
            }
            else if (today-i==-1){
                day.add(0,"30");
            }
            else if (today-i==-2){
                day.add(0,"29");
            }
            else if (today-i==-3){
                day.add(0,"28");
            }
            else if (today-i==-4){
                day.add(0,"27");
            }
            else {
                day.add(0,"26");
            }
        }


            BarDataSet bardataset = new BarDataSet(Temp, "Light Exposure");
            chart.animateY(5000);
            BarData data = new BarData(day, bardataset);

        Resources res = getResources();

        int C1 = res.getColor((R.color.YL1));
        int C2 = res.getColor((R.color.YL2));
        int C3 = res.getColor((R.color.YL3));
        int C4 = res.getColor((R.color.YL4));
        int C5 = res.getColor((R.color.YL5));

        bardataset.setColors(new int[]{C1,C2,C3,C4,C5});            chart.setData(data);

            return v;

        }
    }



