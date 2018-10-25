package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ScoreTrendCategory extends AppCompatActivity {

    private Button buttonAgile,buttonLean,buttonDesign,buttonAll;
    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_trend_category);

        //Setting up a font style
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");

        //Setting up back button
        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Simulates back button being pressed
                onBackPressed();
            }
        });

        //Configuring Button 1 (Agile Scrum)
        buttonAgile = (Button) findViewById(R.id.button_1);
        buttonAgile.setText("agile scrum");
        buttonAgile.setTypeface(HelveticaNeue);
        buttonAgile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(1);
            }
        });

        //Configuring Button 2 (Lean Startup)
        buttonLean = (Button) findViewById(R.id.button_2);
        buttonLean.setText("lean startup");
        buttonLean.setTypeface(HelveticaNeue);
        buttonLean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(2);
            }
        });


        //Configuring Button 3 (Design Thinking)
        buttonDesign = (Button) findViewById(R.id.button_3);
        buttonDesign.setText("design thinking");
        buttonDesign.setTypeface(HelveticaNeue);
        buttonDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(3);
            }
        });


        //Configuring Button 4 (All Topics)
        buttonAll = (Button) findViewById(R.id.button_4);
        buttonAll.setText("all topics");
        buttonAll.setTypeface(HelveticaNeue);
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(4);
            }
        });

    }
    private void selection(int selectionNumber){
        //Lets the next class (Quiz Activity) know what button was selected
        //Agile passes 1
        //Lean passes 2
        //Design passes 3
        //All Topics passes 4

        Intent intent = new Intent(ScoreTrendCategory.this,ScoreTrendActivity.class);
        intent.putExtra("Type",selectionNumber);
        startActivity(intent);
    }
}
