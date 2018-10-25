package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingPageActivity extends AppCompatActivity {
    private static final String TAG = "LandingPageActivity";
    private TextView title,subtitle;
    private Button startButton;

    //This is the class for the front page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        //Configures design of Landing Page
        title = (TextView) findViewById(R.id.text_title);
        Typeface tahoma =Typeface.createFromAsset(getAssets(),"fonts/tahoma.ttf");
        title.setTypeface(tahoma);

        subtitle = (TextView) findViewById(R.id.text_subtitle);
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");
        subtitle.setTypeface(HelveticaNeue);
        subtitle.setText("test your INFS2603 knowledge now");

        startButton = (Button) findViewById(R.id.button_start);
        startButton.setTypeface(HelveticaNeue);
        startButton.setText("get started");

        //Handler for 'start' button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categorySelect();
            }
        });
    }



    private void categorySelect(){
    //This method is to begin the CategorySelectionActivity class
        Intent intent = new Intent(LandingPageActivity.this, CategorySelectionActivity.class );
        startActivity(intent);
    }
}
