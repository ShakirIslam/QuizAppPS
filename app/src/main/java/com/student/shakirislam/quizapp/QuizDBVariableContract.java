package com.student.shakirislam.quizapp;

import android.provider.BaseColumns;

public class QuizDBVariableContract {
    //Class outlining column names

    public static class QuizTable implements BaseColumns {
        private static final String TAG = "QuizTable";
        private QuizTable(){
            //this is so that another class cannot create an object of this class
            //as it is just a final variable class.
            //'Good coding procedure.'
        }

        //Listing out column names of db table
        //Note, these are just variables, not actual tables yet. Look in QuizDBHelper to create columns
        public static final String TABLE_NAME = "question_info";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUMBER = "answer_number";
        public static final String COLUMN_FEEDBACK = "feedback";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_WIKI_INTRO = "wiki_intro";
        public static final String COLUMN_WIKI_LINK = "wiki_link";
        public static final String COLUMN_YOUTUBE = "youtube";

        //Need this specific interface (BaseColumns), because certain android classes require with the naming convention "_id"
        //Variables use in QuizDBHelper class



    }

}
