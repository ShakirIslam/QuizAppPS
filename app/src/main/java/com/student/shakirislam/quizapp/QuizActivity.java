package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textQuestion;
    private TextView textScore;
    private TextView textQuestionCount;
    private TextView textCounter;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonSubmit;

    private List<Question> listQuestion;

    private ColorStateList rbcolour;
    private int questionCount;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScore = (TextView) findViewById(R.id.text_score);
        textQuestionCount = (TextView) findViewById(R.id.text_question_count);
        textCounter = (TextView) findViewById(R.id.text_counter);
        rbGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);
        buttonSubmit = (Button) findViewById(R.id.button_submit);

        rbcolour = rb1.getTextColors();


        QuizDBHelper dbHelper = new QuizDBHelper(this);


        //Getting the intent that started the activity
        int categoryNumber = getIntent().getExtras().getInt("Type");
        listQuestion = dbHelper.getCategoryQuestions(categoryNumber);

        questionCountTotal = listQuestion.size();
        Collections.shuffle(listQuestion);

        displayNextQuestion();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        answered = true;
                        checkAnswer();
                    }else{
                        Toast.makeText(QuizActivity.this, "Select an option please", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    displayNextQuestion();
                }
            }
        });

    }

    private void displayNextQuestion() {
        //Setting the colour of the radio button to default
        rb1.setTextColor(rbcolour);
        rb2.setTextColor(rbcolour);
        rb3.setTextColor(rbcolour);
        rb4.setTextColor(rbcolour);

        //Clearing the selection every round
        rbGroup.clearCheck();

        if(questionCount < questionCountTotal){
            currentQuestion = listQuestion.get(questionCount);

            textQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOpt1());
            rb2.setText(currentQuestion.getOpt2());
            rb3.setText(currentQuestion.getOpt3());
            rb4.setText(currentQuestion.getOpt4());

            //this allows the questions to start at '1'
            questionCount++;
            textQuestionCount.setText("Current Question " + questionCount + " / " + questionCountTotal);
            //Used to later check if radio buttons have been selected
            answered = false;
            buttonSubmit.setText("Submit");


        }else{
            quizFinished();
        }
    }

    private void quizFinished() {
        //This will started a new activity showing results, need to pass intent and results. 
        finish();
    }

    public void checkAnswer(){
        //Gets the radiobutton that was selected from the group rather than individually

        RadioButton selectedRb = findViewById(rbGroup.getCheckedRadioButtonId());

        int answerNum = rbGroup.indexOfChild(selectedRb) + 1;
        //+1 is added because index notation

        if(answerNum == currentQuestion.getAnswerNum()){
            score ++;
            textScore.setText("Score:" + score);

        }

        highlightSolution();
    }

    private void highlightSolution() {
        //setting all of them to red and then set correct to green
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNum()){
            case 1: rb1.setTextColor(Color.GREEN);break;
            case 2: rb2.setTextColor(Color.GREEN);break;
            case 3: rb3.setTextColor(Color.GREEN);break;
            case 4: rb4.setTextColor(Color.GREEN);break;

        }

        if(questionCount < questionCountTotal){
            buttonSubmit.setText("Next Question");
        }

        if(questionCount == questionCountTotal){
            buttonSubmit.setText("View Results");
        }


    }
}
