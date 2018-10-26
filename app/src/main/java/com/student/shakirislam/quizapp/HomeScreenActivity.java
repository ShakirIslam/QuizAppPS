package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreenActivity extends AppCompatActivity {

    private Button buttonQuiz;
    private Button buttonTrend;
    private TextView text_italic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        buttonQuiz = (Button) findViewById(R.id.button_quiz);
        buttonTrend = (Button) findViewById(R.id.button_trends);
        text_italic = (TextView) findViewById(R.id.text_italics);

        //Setting text for textview (below button)
        text_italic.setText("The key to mastering methodology fundamentals...");
        //Setting text for buttons
        buttonQuiz.setText("Quiz");
        buttonTrend.setText("Results");

        //Setting the font for the quiz and trend button
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");
        buttonQuiz.setTypeface(HelveticaNeue);
        buttonTrend.setTypeface(HelveticaNeue);


        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to enter the Quiz Category Selection
                Intent intent = new Intent(HomeScreenActivity.this,CategorySelectionActivity.class);
                startActivity(intent);
            }
        });

        buttonTrend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to enter the Results Trend Category Selection
                Intent intent = new Intent(HomeScreenActivity.this,ScoreTrendCategory.class);
                startActivity(intent);
            }
        });
    }
}
