package com.student.shakirislam.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import com.student.shakirislam.quizapp.ResultsActivity.*;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private TextView textQuestion;
    private TextView textScore;
    private TextView textQuestionCount;
    private TextView textCounter;
    //Timer length set here
    private static final long COUNTDOWN_MILLIS = 30000;
    private long timeLeftmillis;
    private CountDownTimer countDownTimer;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonSubmit;
    private Button buttonFeedback;
    private ProgressBar timerProgressBar;

    private List<Question> listQuestion;

    private ColorStateList rbcolour;
    private ColorStateList cdcolour;
    private int questionCount;
    private int questionCountTotal;
    private Question currentQuestion;
    private ImageView cancelButton;

    private int score;
    private boolean answered;
    private static int categoryNumber;



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
        buttonFeedback =(Button) findViewById(R.id.button_feedback);
        timerProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        cancelButton = (ImageView) findViewById(R.id.imageCancelButton);


        //Getting the default colour of radiobutton
        rbcolour = rb1.getTextColors();
        //Getting the default colour of count-down
        cdcolour = textCounter.getTextColors();

        //Creating instance of the DB helper class
        QuizDBHelper dbHelper = new QuizDBHelper(this);


        //Extracting the category number to indicate what type of topic is desired
        categoryNumber = getIntent().getExtras().getInt("Type");
        //Array of the Quesiton class which gets filled with the desired category of quesitons
        listQuestion = dbHelper.getCategoryQuestions(categoryNumber);

        //randomises the questions
        Collections.shuffle(listQuestion);

        //Question number limiter for 'all topics' section
        //All topics question count needs to be limited down to 10 questions.
        if(categoryNumber == 4) {
            Log.d(TAG, "Begun question number changer");
            int size = listQuestion.size();
            //Creates a sublist of the array and then clears the remainder
            listQuestion.subList(10,size).clear();
            Log.d(TAG, "onCreate: Size of list Quesiton after limitation " + listQuestion.size());
        }


        //Defining the total amount of questions
        questionCountTotal = listQuestion.size();
        //Used to display the first question
        displayNextQuestion();

        //Handles the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Case: where question has not been submitted yet AND time is NOT up
                if(answered == false && timeLeftmillis > 0){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        //Checks if radiobuttons have been selected
                        //Changes status of question to be considered 'answered'
                        answered = true;
                        //Checks whether answer is correct
                        checkAnswer();
                        //Feedback button is made visible
                        buttonFeedback.setVisibility(View.VISIBLE);
                    }else{
                        //Case: Option has not been selected AND still has time  to do so
                        Toast.makeText(QuizActivity.this, "Select an option please", Toast.LENGTH_SHORT).show();
                    }
                }
                //Case: where question has not been submitted yet AND time is up
                else{
                    //next question
                    displayNextQuestion();
                }
            }
        });

        //Feedback button handler
        buttonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Shows the dialog box of the feedback content
                showFeedback();
            }
        });

        //Quit Quiz Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goes back to category selection
                onBackPressed();
            }
        });

    }


    private void displayNextQuestion() {
        //displays the question with default style

        //Setting the text colour of the radio button to default
        rb1.setTextColor(rbcolour);
        rb2.setTextColor(rbcolour);
        rb3.setTextColor(rbcolour);
        rb4.setTextColor(rbcolour);

        //Feedback button is cleared
        buttonFeedback.setVisibility(View.GONE);
        //Clearing the selection every round and reseting text style
        rbGroup.clearCheck();

        //Checks if questions are left
        if(questionCount < questionCountTotal){
            //Updates the 'current question'
            currentQuestion = listQuestion.get(questionCount);

            //Sets the text for the new question
            textQuestion.setText(currentQuestion.getQuestion());

            //Refreshes design of the radio buttons
            rb1.setText(currentQuestion.getOpt1());
            rb2.setText(currentQuestion.getOpt2());
            rb3.setText(currentQuestion.getOpt3());
            rb4.setText(currentQuestion.getOpt4());
            rb1.setPaintFlags(0);
            rb2.setPaintFlags(0);
            rb3.setPaintFlags(0);
            rb4.setPaintFlags(0);
            rb1.setTypeface(null,Typeface.NORMAL);
            rb2.setTypeface(null,Typeface.NORMAL);
            rb3.setTypeface(null,Typeface.NORMAL);
            rb4.setTypeface(null,Typeface.NORMAL);

            //this allows the questions to start at '1' (index counting)
            //Question counter, not time counter
            questionCount++;
            //Sets progress through quiz
            textQuestionCount.setText("Current Question " + questionCount + " / " + questionCountTotal);
            //Reseting answer status
            answered = false;
            //Resetting submit button, from 'Next Question'
            buttonSubmit.setText("Submit");

            //Reseting Timer
            timerProgressBar.setProgress(100);
            timeLeftmillis = COUNTDOWN_MILLIS;

            //Start count-down timer
            startCountDown();
        }else{
            //Quiz is finished (goes to ResultsActivity)
            quizFinished();
        }
    }

    private void startCountDown() {
        //Controlling the count-down timer

        //Instance of the countDownTimer class
        //Is a seconds timer (1000 milliseconds is the intervals)
        countDownTimer = new CountDownTimer(timeLeftmillis, 1000) {
            @Override
            public void onTick(long milliUntilFin) {
            //This method will be called every 1000 milli secs
                timeLeftmillis = milliUntilFin;
                updateCounterText();

                //Controlling Time Progress Bar
                Log.d(TAG, "onTick: timeLeftmillis = " + timeLeftmillis);
                //calculates a percentage of time left
                double dTimeProgressPercentage = ((double)timeLeftmillis/COUNTDOWN_MILLIS) * 100;
                Log.d(TAG, "onTick: Float Progress Value: " + dTimeProgressPercentage);
                int iTimeProgressPercentage = (int)dTimeProgressPercentage;
                //Passes percentage value and controls visual status of timer
                timerProgressBar.setProgress(iTimeProgressPercentage);

            }

            @Override
            public void onFinish() {
                //Runs when timer is finished
                //Ensures timeLeftMillis is set to 0 for other dependencies
                timeLeftmillis = 0;
                //updates the timer text (Textview)
                updateCounterText();
                //Timer is finished, does a check answer to validate entry
                checkAnswer();
                timerProgressBar.setProgress(0);



            }
        }.start();
    }

    private void updateCounterText() {
        //Calcualtes seconds of time left
        int seconds = (int) (timeLeftmillis /1000) % 60;
        //Formats the timer
        String timeFormat = String.format(Locale.getDefault(), "%02d", seconds);

        //Updates the textview of the timer.
        textCounter.setText(timeFormat);
        //Controls timer colour
        if (timeLeftmillis < 10000){
            //When timer goes below 10 seconds, timer will turn red
            textCounter.setTextColor(Color.RED);

        }else{
            //Time abover 10 seconds will be shown as black.
            textCounter.setTextColor(cdcolour);
        }
    }

    private void quizFinished() {
        //Calculates final score result and handles finishing quiz

        int correct = score;
        //Calculates number of quesitons that were answered wrong
        int wrong = questionCountTotal - score;
        //Calculates percentage of questions answered correctly
        double percenD = ((double) score/ questionCountTotal) * 100 ;
        String percentageString = Double.toString(percenD);

        Log.d(TAG, "quizFinished: Correct Questions: " + score + " Incorrect Questions: " + wrong);
        Log.d(TAG, "quizFinished: Percentage Double value: " + percenD + " & String value is " + percentageString);
        //Sending values to ResultsActivity class
        Intent intent = new Intent(QuizActivity.this,ResultsActivity.class);

        intent.putExtra("Correct", correct);
        intent.putExtra("Wrong",wrong );
        intent.putExtra("Percentage",percentageString);
        startActivity(intent);
    }

    public void checkAnswer(){
        //Checks whether answer was correct

        //Gets the radiobutton that was selected from the group rather than individually
        RadioButton selectedRb = findViewById(rbGroup.getCheckedRadioButtonId());
        Log.d(TAG, "checkAnswer: Value of selected radio button " + selectedRb);
        //Stops timer
        countDownTimer.cancel();

        //Radio button is not selected
        if(selectedRb != null) {
            //+1 is added because index notation
            //sets answerNum as correct answer number (A = 1, B = 2, C = 3 & D = 4)
            int answerNum = rbGroup.indexOfChild(selectedRb) + 1;

            //Compares button to correct answer from db value
            if (answerNum == currentQuestion.getAnswerNum()) {
                Log.d(TAG, "checkAnswer: Score was increased by 1");
                //Increases question if DB value matches correct RadioButton selected
                score++;
                //Textview updates
                textScore.setText("Score: " + score);

            }
            //Feedback button is made visible
            buttonFeedback.setVisibility(View.VISIBLE);
        }else{
            //No answer is selected and time has ran out
            Log.d(TAG, "checkAnswer: Feedback button is made visible");
            buttonFeedback.setVisibility(View.VISIBLE);
        }


        //Highlights incorrect and correct answer
        highlightSolution();
    }

    private void highlightSolution() {
        //Method to highlight correct and incorrect answers

        //setting all of them to red and then correct to underlined below
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        //Underlines and bolds the correct answer
        //Relies on getAnswerNum (DB stored value indicating correct answer)
        switch (currentQuestion.getAnswerNum()){
            case 1: rb1.setTextColor(Color.BLACK); rb1.setPaintFlags(rb1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); rb1.setTypeface(null, Typeface.BOLD);break;
            case 2: rb2.setTextColor(Color.BLACK); rb2.setPaintFlags(rb2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); rb2.setTypeface(null, Typeface.BOLD);break;
            case 3: rb3.setTextColor(Color.BLACK); rb3.setPaintFlags(rb3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); rb3.setTypeface(null, Typeface.BOLD);break;
            case 4: rb4.setTextColor(Color.BLACK); rb4.setPaintFlags(rb4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); rb4.setTypeface(null, Typeface.BOLD);break;

        }
        //Quiz continues
        if(questionCount < questionCountTotal){
            buttonSubmit.setText("Next Question");
        }
        //Quiz ends, results page is shown
        if(questionCount == questionCountTotal){
            buttonSubmit.setText("View Results");
        }


    }

    private void showFeedback() {
       //Creating dialog box. .
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(QuizActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_feedback, null);

       //Creating Text and Image Variables
        TextView textFeedbackContent = (TextView) mView.findViewById(R.id.text_feedbackContent);
        ImageView wikiSymbol = (ImageView) mView.findViewById(R.id.image_wiki);
        ImageView youtubeSymbol = (ImageView) mView.findViewById(R.id.image_youtube);
        TextView textFeedbackTitle = (TextView) mView.findViewById(R.id.text_feedbackTitle);

       //Applying content to variables
        textFeedbackContent.setText(currentQuestion.getFeedback());
        textFeedbackTitle.setPaintFlags(textFeedbackTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //Changing the font style of the Feedback Title
        Typeface HelveticaNeue =Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue.ttf");
        textFeedbackTitle.setTypeface(HelveticaNeue);

        //Fade in Animation
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        mView.setAnimation(fadeInAnimation);

        //Selecting the Wikipedia Button
        wikiSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Question specific wiki parameters sent to WikiSearchActivity class
                WikiSearchActivity.setWiki_intro(currentQuestion.getWiki_intro());
                WikiSearchActivity.setWiki_link(currentQuestion.getWiki_link());

                //Wiki info page is navigated to
                Intent intent = new Intent(QuizActivity.this,WikiSearchActivity.class);
                startActivity(intent);
            }
        });

        //Selecting the Youtube Button
        youtubeSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Question specific youtube parameters sent to the YoutuberPlayerCustomActivity class
                YoutubePlayerCustomActivity.youtubeVidID = currentQuestion.getYoutube();

                //Youtube info page is navigated to
                Intent intent = new Intent(QuizActivity.this,YoutubePlayerCustomActivity.class);
                startActivity(intent);
            }
        });

        //Building the dialog box
        mBuilder.setView(mView);
        AlertDialog feedbackDialog = mBuilder.create();
        feedbackDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Making sure that timer is cancelled when activity is destroyed.
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    public static int getCategoryNumber() {
        //getter to get the category number selected
        return categoryNumber;
    }
}
