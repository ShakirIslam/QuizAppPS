package com.student.shakirislam.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.student.shakirislam.quizapp.QuizDBVariableContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Quiz_DB.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private static final String TAG = QuizDBHelper.class.getSimpleName();

    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //need to create initial database tables here

        Log.i(TAG, "onCreate: method has run");

        this.db = db;

        final String SQL_CREATE_QUESTION_INFO_TABLE = "CREATE TABLE " +
                QuizTable.TABLE_NAME + " (" +
                QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizTable.COLUMN_QUESTION + " TEXT, " +
                QuizTable.COLUMN_OPTION1 + " TEXT, " +
                QuizTable.COLUMN_OPTION2 + " TEXT, " +
                QuizTable.COLUMN_OPTION3 + " TEXT, " +
                QuizTable.COLUMN_OPTION4 + " TEXT, " +
                QuizTable.COLUMN_ANSWER_NUMBER + " INTEGER, " +
                QuizTable.COLUMN_FEEDBACK + " TEXT," +
                QuizTable.COLUMN_CATEGORY + " TEXT," +
                QuizTable.COLUMN_WIKI + " TEXT," +
                QuizTable.COLUMN_YOUTUBE + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_INFO_TABLE);
        fillQuizQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        //use to update to change db

        db.execSQL("DROP TABLE IF EXISTS " + QuizTable.TABLE_NAME);
        onCreate(db);
    }

    public void refreshDB(){

        //deletes db and reinserts data.
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + QuizTable.TABLE_NAME);
        System.out.println("Success");
        onCreate(db);
    }

    private void fillQuizQuestionsTable(){
        Question q1 = new Question("Which of the following is not a type of Agile approach?", "Scrum", "Extreme Programming (XP)", "Design Thinking", "Kanban", 3, "Agile Scrum, XP and Kanban are all frameworks that fall under the Agile method, classified as a progressive approach, whereas Design Thinking is an avant-garde approach to project development.", "Agile", "Agile_software_development", "Agile approach");
        addQuestion(q1);

        Question q2 = new Question("Which of the following is the product owner responsible for?", "Responsible for the profitability of the product", "Accept or reject work results", "Decide on sprint start date and content", "Host daily scrum stand ups", 4, "The product owner is responsible for deciding on the sprint date, content, the profitability of the product and also approves or rejects work completed during the sprint. The scrum master on the other hand, is responsible for hosting daily standups.", "Agile", "Scrum_(software_development)", "Product owner");
        addQuestion(q2);

        Question q3 = new Question("Which of the following is  not a fundamental question that startups consider?", "Can we build this?", "What materials do we need?", "If we build this, will they come?", "If they come, can we realise value?", 2, "Startups are more concerned with the feasibility and profitability of their product, as opposed to focusing on the finer details such as materials and resources.", "Lean", "Startup_company", "Startups");
        addQuestion(q3);

        Question q4 = new Question("Which classification of workplace type does the lean startup methodology fall under?", "Conservative", "Avant-garde", "Progressive ", "Regressive", 2, "The lean startup methodology is classified under the avant garde workplace type. Cross functional teams and platforms, creativity and iterative development are characteristic of this approach.", "Lean", "Lean_startup", "Lean startup");
        addQuestion(q4);

        Question q5 = new Question("Which classification of workplace type does the design thinking methodology fall under?", "Conservative", "Regressive", "Progressive", "Avant-garde ", 4, "The design thinking methodology is classified under the avant garde workplace type. Cross functional teams and platforms, creativity and iterative development are characteristic of this approach.", "Design", "Design_thinking", "Design thinking");
        addQuestion(q5);

        Question q6 = new Question("\"Fail fast, iterate quickly\" is characteristic of which step in the design thinking process?", "Prototype", "Test", "Empathy", "Ideate", 1, "Prototyping is the fourth stage of the design thinking process and is characterised by fast, iterative development. The motto \"fail fast, iterate quickly\" encompasses this concept.", "Design", "Design_thinking", "Design thinking process");
        addQuestion(q6);


    }

    private void addQuestion(Question question){
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuizTable.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuizTable.COLUMN_OPTION1, question.getOpt1());
        contentValues.put(QuizTable.COLUMN_OPTION2, question.getOpt2());
        contentValues.put(QuizTable.COLUMN_OPTION3, question.getOpt3());
        contentValues.put(QuizTable.COLUMN_OPTION4, question.getOpt4());
        contentValues.put(QuizTable.COLUMN_ANSWER_NUMBER, question.getAnswerNum());
        contentValues.put(QuizTable.COLUMN_FEEDBACK, question.getFeedback());
        contentValues.put(QuizTable.COLUMN_CATEGORY, question.getCategory());
        contentValues.put(QuizTable.COLUMN_WIKI, question.getWiki());
        contentValues.put(QuizTable.COLUMN_YOUTUBE, question.getYoutube());

        //nullColumnHack is about adding null values in, review this.
        //Inserts question into db
        db.insert(QuizTable.TABLE_NAME, null, contentValues);

    }

//    public List<Question> getAllQuestions(){
//        //Method to return all retrieve all the questions inputted into the DB.
//        //This is creating a list of all the question objects and putting it into an array.
//        List<Question> listQuestion = new ArrayList<>();
//        //This will do the onCreate command
//        db = getReadableDatabase();
//        //runs on the onCreate method of the db and puts it in a readable state [we are retrieving data]
//        Cursor cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME, null);
//
//        if (cursor.moveToFirst()){
//            //checking if there are returned entries
//            do {
//                Question question = new Question();
//                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_QUESTION)));
//                question.setOpt1(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION1)));
//                question.setOpt2(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION2)));
//                question.setOpt3(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION3)));
//                question.setOpt4(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION4)));
//                question.setAnswerNum(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANSWER_NUMBER)));
//                question.setFeedback(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_FEEDBACK)));
//                listQuestion.add(question);
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        return listQuestion;
//
//    }


    //Following method is to get specific category of questions
    public List<Question> getCategoryQuestions(int category){

        List<Question> listQuestion = new ArrayList<>();
        db = getReadableDatabase();
        //runs on the onCreate method of the db and puts it in a readable state [we are retrieving data]

        Cursor cursor = null;

        //1 = agile, 2 = lean, 3 = design and 4 = all topics
        switch(category){
            case 1:  cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME +
                    " WHERE " + QuizTable.COLUMN_CATEGORY + " = ?", new String[] {"Agile"}); break;

            case 2:  cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME +
                    " WHERE " + QuizTable.COLUMN_CATEGORY + " = ?", new String[] {"Lean"}); break;

            case 3:  cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME +
                    " WHERE " + QuizTable.COLUMN_CATEGORY + " = ?", new String[] {"Design"}); break;

            case 4:  cursor = db.rawQuery("SELECT * FROM " + QuizTable.TABLE_NAME, null); break;
        }

        if (cursor.moveToFirst()){
            //checking if there are returned entries
            do {
                Question question = new Question();
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_QUESTION)));
                question.setOpt1(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION1)));
                question.setOpt2(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION2)));
                question.setOpt3(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION3)));
                question.setOpt4(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_OPTION4)));
                question.setAnswerNum(cursor.getInt(cursor.getColumnIndex(QuizTable.COLUMN_ANSWER_NUMBER)));
                question.setFeedback(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_FEEDBACK)));
                question.setCategory(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_CATEGORY)));
                question.setWiki(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_WIKI)));
                question.setYoutube(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_YOUTUBE)));

                //Adding to the array list
                listQuestion.add(question);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listQuestion;


    }


}
