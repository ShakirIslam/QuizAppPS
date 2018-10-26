package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
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
    private TextView text_alert;
    private Button home_button, reset_button;


    private static final int AGILE = 1;
    private static final int LEAN = 2;
    private static final int DESIGN = 3;
    private static final int ALL_TOPICS = 4;
    QuizDBHelper dbHelper;

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_trend);

        //Setting up view components
        mChart = (LineChart) findViewById(R.id.line_chart);
        text_alert = (TextView) findViewById(R.id.text_alert);
        home_button = (Button) findViewById(R.id.button_home);
        reset_button = (Button) findViewById(R.id.button_reset);

        //Pressing home button
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreTrendActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        //Creating instance of the DB helper class
         dbHelper = new QuizDBHelper(this);

        //Pressing Clear Button
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Db is sent category parameter to know which category to clear
                dbHelper.clearResult(getIntent().getExtras().getInt("Type"));
                Log.d(TAG, "onClick: Type value passed to resetDB: " + getIntent().getExtras().getInt("Type"));
                //Toast indicating data was cleared
                Toast.makeText(ScoreTrendActivity.this, "Results Cleared", Toast.LENGTH_SHORT).show();
                //Takes user back to the home screen
                Intent intent = new Intent(ScoreTrendActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        //Resetting alert text visibily
        text_alert.setVisibility(View.INVISIBLE);

        //Resetting line chart visibility
        mChart.setVisibility(View.VISIBLE);
        //Resetting reset button visibility
        reset_button.setVisibility(View.VISIBLE);



        //Agile = 1, Lean = 2, Design = 3 & All Topics = 4
        resultStream = dbHelper.getResult(getIntent().getExtras().getInt("Type"));

        //Array to hold the result score values
        ArrayList<Entry> yValues = new ArrayList<>();

        //Ran if there are not results currently stored for that category
        if(resultStream.size() == 0){
            Log.d(TAG, "onCreate: The Size of the resultStream is 0");
            //Makes text outlining no results, visible
            text_alert.setVisibility(View.VISIBLE);

            String quizType ="";

            //Determing which category was chosen
            switch(getIntent().getExtras().getInt("Type")){
                case 1: quizType = "an Agile "; break;
                case 2: quizType = "a Lean "; break;
                case 3: quizType = "a Design ";  break;
                case 4: quizType = "an All Topics ";  break;
            }
            //Catering text_alert message to specific category
            text_alert.setText("Please come back after doing " + quizType +  "quiz to view result trends");
            //Making chart and reset button invisible
            mChart.setVisibility(View.INVISIBLE);
            reset_button.setVisibility(View.GONE);
        }
        //When result table for specific column is not empty
        if(resultStream.size() != 0) {
            for (int i = 0; i < resultStream.size(); i++) {
                //Adds db results into the yvalue array
                yValues.add(new Entry(i, resultStream.get(i)));
            }


            //Inputing the entry values into the dataset
            LineDataSet set1 = new LineDataSet(yValues, "Quiz Scores");

            //Modifying Visual of line
            set1.setFillAlpha(110);
            set1.setLineWidth(5f);
            set1.setValueTextSize(25f);
            set1.setColor(Color.rgb(135, 232, 17));

            //Creating and setting the dataset
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            //Dataline (line on the graph)
            LineData data = new LineData(dataSets);

            //Attaching line to the graph
            mChart.setData(data);

            //Modifying Visuals of the chart

            //1 Second animation
            mChart.animateX(1000);
        }

    }




}
