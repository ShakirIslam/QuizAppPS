package com.student.shakirislam.quizapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ScoreTrendActivity extends AppCompatActivity {

    //WORK IN PROGRESS, CURRENT STATUS NON FUNCTIONAL

    private static final String TAG = "ScoreTrendActivity";
    private static ArrayList<Float> agileScore;
    private static ArrayList<Float> leanScore;
    private static ArrayList<Float> designScore;
    private static ArrayList<Float> allTopicScore;
    private static ArrayList<Float> resultStream;


    private static final int AGILE = 1;
    private static final int LEAN = 2;
    private static final int DESIGN = 3;
    private static final int ALL_TOPICS = 4;


    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_trend);

        mChart = (LineChart) findViewById(R.id.line_chart);

        //Testing Bay
//        ArrayList<Float> tester = new ArrayList<>();
//        Log.d(TAG, "onCreate: The size of newly created float " + tester.size());

        //Testing Bay

        //Creating instance of the DB helper class
        QuizDBHelper dbHelper = new QuizDBHelper(this);

        //Agile = 1, Lean = 2, Design = 3 & All Topics = 4
        resultStream = dbHelper.getResult(getIntent().getExtras().getInt("Type"));


        ArrayList<Entry> yValues = new ArrayList<>();


        if(resultStream.size() == 0){
            Log.d(TAG, "onCreate: The Size of the resultStream is 0");
        }

        if(resultStream.size() != 0) {
            for (int i = 0; i < resultStream.size(); i++) {
                yValues.add(new Entry(i, resultStream.get(i)));
            }


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




}
