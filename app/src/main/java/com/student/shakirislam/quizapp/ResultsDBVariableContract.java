package com.student.shakirislam.quizapp;

import android.provider.BaseColumns;

public class ResultsDBVariableContract {
    //Class outlining column names of the Results Table

    public static class ResultTable implements BaseColumns{

        private ResultTable(){
            //this is so that another class cannot create an object of this class
            //as it is just a final variable class.
            //'Good coding procedure.'
        }


        public static final String TABLE_NAME = "results_info";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_SCORE = "score";


        //Need this specific interface (BaseColumns), because certain android classes require with the naming convention "_id"
        //Variables use in QuizDBHelper class




    }
}
