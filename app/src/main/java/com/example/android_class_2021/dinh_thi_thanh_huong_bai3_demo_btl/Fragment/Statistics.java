package com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.Fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_class_2021.dinh_thi_thanh_huong_bai3_demo_btl.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        createBarChart();
    }

    public void createBarChart() {
        BarChart chart = findViewById(R.id.barchart);

        ArrayList num = new ArrayList();

        num.add(new BarEntry(10, 0));
        num.add(new BarEntry(20, 1));
        num.add(new BarEntry(30, 2));
        num.add(new BarEntry(60, 3));
        num.add(new BarEntry(80, 4));
        num.add(new BarEntry(90, 5));
        num.add(new BarEntry(120, 6));

        ArrayList day = new ArrayList();

        day.add("T2");
        day.add("T3");
        day.add("T4");
        day.add("T5");
        day.add("T6");
        day.add("T7");
        day.add("T7");

        BarDataSet bardataset = new BarDataSet(num, " ");
        chart.animateY(5000);
        BarData data = new BarData(day, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}