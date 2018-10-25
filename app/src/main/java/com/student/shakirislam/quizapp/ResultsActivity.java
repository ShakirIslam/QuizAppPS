package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    //Class for configuring the results page after a quiz

    private static final String TAG = "ResultsActivity";
    private TextView resultTitle;
    float[] score;
    private String status [] = {"Correct", "Incorrect"};
    private Button homeButton;
    private String questionCategory;

    //ArrayList to hold scores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: has begun");


        //Designing Title and Button
        setContentView(R.layout.activity_results);
        score = new float[2];
        setScore();
        setupChart();
        resultTitle  = (TextView) findViewById(R.id.text_result);
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");
        Typeface tahoma =Typeface.createFromAsset(getAssets(),"fonts/tahoma.ttf");
        resultTitle.setTypeface(HelveticaNeue);

        homeButton = (Button) findViewById(R.id.button_home);

        //Producing percentage value of score
        String percentString = getIntent().getExtras().getString("Percentage");
        Log.d(TAG, "onCreate: Percentage value directly from intent" + percentString);
        percentString += "%";
        resultTitle.setText("Quiz Result \n" + percentString);

        Log.d(TAG, "onCreate: Printing value of percentString " + percentString);

        //Handling home button click
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(ResultsActivity.this,CategorySelectionActivity.class);
                startActivity(intent);
            }
        });
    }



    private void setupChart() {
        //Creating a list of piechart entries
        List<PieEntry> pieEntry = new ArrayList<>();
        for(int i = 0; i <2; i++){
            pieEntry.add(new PieEntry(score[i], status[i]));
        }

        //Inputting the data values into the chart
        PieDataSet dataSet = new PieDataSet(pieEntry, "Quiz");
        dataSet.setValueTextSize(40f);
        int[] correctColors = {Color.rgb(135, 232, 17), Color.rgb(232, 57, 18)};
        dataSet.setColors(ColorTemplate.createColors(correctColors));

        PieData data = new PieData(dataSet);

        //Creating the Visual Chart
        PieChart chart = (PieChart) findViewById(R.id.scoreChart);
        dataSet.setLabel("");
        chart.setData(data);
        chart.animateY(1000);
        chart.setEntryLabelTextSize(15f);
        chart.setCenterTextSizePixels(20f);
        chart.setHoleRadius(30f);
        chart.setTransparentCircleRadius(35f);

        //Causes to redraw chart
        chart.invalidate();
    }

    public void setScore(){
        //Extracting the intents sent from the QuizActivity class
        //correctNum = # of Questions answered correctly
        //wrongNum = # of Questions answered incorrectly
            float correctNum = (float) getIntent().getExtras().getInt("Correct");
            float wrongNum = (float) getIntent().getExtras().getInt("Wrong");


        //Adding above values into float array (used for pie chart)
        score[0] = correctNum;
        score[1] = wrongNum;

//        score[0] = (float) 1;
//        score[1] = (float) 2;
    }


    private void saveData() {
        //Method for adding score to the score list

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        //String json = gson.
    }

}
