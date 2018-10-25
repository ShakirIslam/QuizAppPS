package com.student.shakirislam.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ScoreTrendActivity extends AppCompatActivity{

    private static final String TAG = "ScoreTrendActivity";
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_trend);

        mChart = (LineChart) findViewById(R.id.line_chart);

//        mChart.setOnChartGestureListener(ScoreTrendActivity.this);
//        mChart.setOnChartValueSelectedListener(ScoreTrendActivity.this);


        ArrayList<Entry> yValues = new ArrayList<>();

        //Array

        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,50f));
        yValues.add(new Entry(2,70f));
        yValues.add(new Entry(3,20f));
        yValues.add(new Entry(4,70f));
        yValues.add(new Entry(5,10f));


        //Inputing the entry values into the dataset
        LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");

        set1.setFillAlpha(110);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);
        mChart.animateX(2000);




    }
}
