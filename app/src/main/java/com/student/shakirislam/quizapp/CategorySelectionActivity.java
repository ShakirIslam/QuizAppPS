package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CategorySelectionActivity extends AppCompatActivity {
    private static final String TAG = "CategorySelectionActivi";
    private TextView textChoose;
    private Button button1,button2,button3,button4;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        //Configuring title
        textChoose = (TextView) findViewById(R.id.text_trend);
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");
        textChoose.setTypeface(HelveticaNeue);
        textChoose.setText("choose a topic");

        //Configuring Button 1 (Agile Scrum)
        button1 = (Button) findViewById(R.id.button_1);
        button1.setText("agile scrum");
        button1.setTypeface(HelveticaNeue);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(1);
            }
        });

        //Configuring Button 2 (Lean Startup)
        button2 = (Button) findViewById(R.id.button_2);
        button2.setText("lean startup");
        button2.setTypeface(HelveticaNeue);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(2);
            }
        });


        //Configuring Button 3 (Design Thinking)
        button3 = (Button) findViewById(R.id.button_3);
        button3.setText("design thinking");
        button3.setTypeface(HelveticaNeue);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(3);
            }
        });


        //Configuring Button 4 (All Topics)
        button4 = (Button) findViewById(R.id.button_4);
        button4.setText("all topics");
        button4.setTypeface(HelveticaNeue);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selection(4);
            }
        });

        //Back Button
        backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes user to home page
                Intent intent = new Intent(CategorySelectionActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });




    }

    private void selection(int selectionNumber){
        //Lets the next class (Quiz Activity) know what button was selected
        //Agile passes 1
        //Lean passes 2
        //Design passes 3
        //All Topics passes 4

    Intent intent = new Intent(CategorySelectionActivity.this,QuizActivity.class);
    intent.putExtra("Type",selectionNumber);
    startActivity(intent);
    }




}
