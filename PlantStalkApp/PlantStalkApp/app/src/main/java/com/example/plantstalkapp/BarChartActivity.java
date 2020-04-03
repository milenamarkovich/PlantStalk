package com.example.plantstalkapp;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.dfrobot.angelo.blunobasicdemo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Formatter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class BarChartActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart chart = findViewById(R.id.barchart);


        //dummy data array:

        ArrayList <Float> Data = new ArrayList<Float>();


        ArrayList Temp = new ArrayList();

        for(int i = 0; i<Data.size(); i++){
            Temp.add(new BarEntry(Data.get(i), i));
        }


        ArrayList day = new ArrayList();

        for(int i = 0; i<Data.size(); i++){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
            LocalDateTime now = LocalDateTime.now();
            int today = (int) (new Date().getDate());
            day.add(Integer.toString(today+i)); }


        BarDataSet bardataset = new BarDataSet(Temp, "Temperature");
        chart.animateY(5000);
        BarData data = new BarData(day, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}
