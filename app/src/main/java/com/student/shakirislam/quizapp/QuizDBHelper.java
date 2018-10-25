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
    //Class to manage DB (Creating and Querying)

    private static final String TAG = "QuizDBHelper";
private static final String DATABASE_NAME = "Quiz_DB.db";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;


    public QuizDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //need to create initial database tables here

        Log.i(TAG, "onCreate: method has run");
        //Refers to instance of db
        this.db = db;

        //String of Query that table layout
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
                QuizTable.COLUMN_WIKI_INTRO + " TEXT," +
                QuizTable.COLUMN_WIKI_LINK + " TEXT," +
                QuizTable.COLUMN_YOUTUBE + " TEXT" +
                ")";

        //Executes the String above
        db.execSQL(SQL_CREATE_QUESTION_INFO_TABLE);
        //Method to increase the empty table with content
        fillQuizQuestionsTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newVersion) {
        //use to update to change db
        db.execSQL("DROP TABLE IF EXISTS " + QuizTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuizQuestionsTable(){
        //Creating a Question Object
        //Inserting question instance into db through 'addQuestion(Question question)'

        Log.d(TAG, "fillQuizQuestionsTable: Questions are being generated");
        //Agile Q's
        Question q1 = new Question("Which of the following is not a type of Agile approach?","Scrum","Extreme Programming (XP)","Design Thinking ","Kanban",3,"Agile Scrum, XP and Kanban are all frameworks that fall under the Agile method, classified as a progressive approach, whereas Design Thinking is an avant-garde approach to project development.", "Agile", "Agile_software_development", "Agile_software_development#Agile_software_development_methods", "7Xj0KuucEj8");
        addQuestion(q1);

        Question q2 = new Question("Which of the following is the product owner not responsible for?","Responsible for the profitability of the product","Accept or reject work results","Decide on sprint start date and content ","Host daily scrum stand ups",4,"The product owner is responsible for deciding on the sprint date, content, the profitability of the product and also approves or rejects work completed during the sprint. The scrum master on the other hand, is responsible for hosting daily standups. ", "Agile", "Scrum_(software_development)#Product_owner", "Scrum_(software_development)#Product_owner", "3eljozEWpf8");
        addQuestion(q2);

        Question q3 = new Question("Scrum is an agile process that allows us to focus on delivering the highest business value in the <blank> ","Largest time ","Shortest time","Best Time","Worst Time",2,"Scum is based on three main ideas of transparency, inspection and adaptation, with the main of delivering the hightest business value in the shortest amount of time. ", "Agile", "Scrum_(software_development)", "Scrum_(software_development)", "9TycLR0TqFA");
        addQuestion(q3);

        Question q4 = new Question("What is the typical duration of a sprint?","2-4 weeks","3 months ","1 year ","1 week",1,"The sprint typically has a maximum duration of one month, and minimum duration of two weeks. It is at the heart of the scrum methodology and in conjunction with the daily standup, sprint review and retrospective, one of the main ceremonies in Scrum. ", "Agile", "Scrum_Sprint", "Scrum_(software_development)#Sprint", "Z9QbYZh1YXY");
        addQuestion(q4);

        Question q5 = new Question("Can you make any changes during a particular sprint?","Yes","No","Only to the product backlog","Only to the project budget", 2,"Considering that the development team commits to an amount of work, it is not best practice to make drastic changes during a sprint. While small adjustments may be made, changes should be implemented in the following sprint. ", "Agile", "Scrum_Sprint", "Scrum_(software_development)#Development_team", "Z9QbYZh1YXY");
        addQuestion(q5);

        Question q6 = new Question("Which of the following is not part of the three aspects that the Scrum framework comprises of?","Roles ","Ceremonies","Budget","Artifacts ",3,"The three main aspects of the Scrum framework is comprised of roles, ceremonies and artifacts. While budget is important, it is a consideration that top level management usually account for. ", "Agile", "Scrum_(software_development)", "Scrum_(software_development)", "m-dbT8u6TJw");
        addQuestion(q6);

        Question q7 = new Question("Which of the following are characteristics of an agile scrum team?","Typically 10 people ","Team works in silos ","Members change during sprints","Teams are cross functional ",4,"A key characteristic of Scrum teams is that they are cross functional and self organising. They are usually comprised of 5-9 people who work collaboratively, with members not changing during a sprint. ", "Agile", "Scrum_(software_development)", "Scrum_(software_development)#Development_team", "m-dbT8u6TJw");
        addQuestion(q7);

        Question q8 = new Question("Which of the following is not a question asked at the daily standup?","Where did you go yesterday?","What will you do today?","What did you do yesterday?","Do you have any roadblocks?",1,"The aim of the daily standup is to maintain transparency between the team, keeping the team updated on the progress of work. The team discusses the progress of their work in terms of what they did yesterday, what they will do today and any roadblocks that are inhibiting them from progressing. ", "Agile", "Stand-up_meeting", "Scrum_(software_development)#Daily_Scrum", "m-dbT8u6TJw");
        addQuestion(q8);

        Question q9 = new Question("Which of the following is not a key change organisations need to make when implementing an agile approach?","Changes to existing mindsets ","Changes to existing software","Changes to existing roles","Changes to existing routines",2,"While software may change, the key repercussions of shifting to an agile framework is to adapt in mindset, accept a change in processes, routines and roles. ", "Agile", "Scrum_(software_development)", "Scrum_(software_development)#Key_ideas", "LgTAuAPcrYk");
        addQuestion(q9);

        Question q10 = new Question("The minimum plan necessary to start a Scrum project consists of a product <blank> and a product <blank>","backlog, roadmap","vision, role","vision, backlog ","role, roadmap",3,"The product vision and backlog are two critical components which must be established prior to the commencement of a sprint. The product vision outlines the objectives of the project, while the product backlog defines the scope of work.", "Agile", "Scrum_(software_development)", "Scrum_(software_development)#Product_backlog", "Z9QbYZh1YXY");
        addQuestion(q10);
        //Lean Q's
        Question q11 = new Question("Which of the following is  not a fundamental question that startups consider?","Can we build this?","What materials do we need?","If we build this, will they come?","If they come, can we realise value?",2,"Startups are more concerned with the feasibility and profitability of their product, as opposed to focusing on the finer details such as materials and resources.", "Lean", "Startup_company", "Startup_company", "U_GcDdUye58");
        addQuestion(q11);

        Question q12 = new Question("Which classification of workplace type does the lean startup methodology fall under?","Conservative","Avant-garde","Progressive ","Regressive",2,"The lean startup methodology is classified under the avant garde workplace type. Cross functional teams and platforms, creativity and iterative development are characteristic of this approach.  ", "Lean", "Lean_startup", "Lean_startup#Overview", "X2YoHFuWkqs");
        addQuestion(q12);

        Question q13 = new Question("The lean startup methodology prescribes how ideas should go from <blank> to <blank>","inception, implementation ","implementation, conception","reception, deception","implementation, restriction ",1,"The lean startup methodology is a holistic process and prescribes how ideas should go from inception to implementation. ", "Lean", "Lean_startup", "Lean_startup#Overview", "X2YoHFuWkqs");
        addQuestion(q13);

        Question q14 = new Question("Which of the following is not a lean startup principle?","Entrepreneurs are everywhere ","Innovation accounting ","Entrepreneurship is management","Innovative prototype",4,"Innovation accounting as opposed to innovation prototype is a key principle. There are five principles in total; entrepreneurs are everywhere, entrepreneurship is management, validated learning, innovation accounting and build-measure-learn", "Lean", "Startup_company", "Lean_startup#Innovation_accounting", "U_GcDdUye58");
        addQuestion(q14);

        Question q15 = new Question("What context are lean startup principles and practices geared towards?","Bleak certainty ","Trepidation","Extreme uncertainty ","Fear",3,"Lean startups are geared towards an environment of extreme uncertainty. It is conducive to an environment of change and adaptation. ", "Lean", "Startup_company", "Lean_startup#Overview", "U_GcDdUye59");
        addQuestion(q15);

        Question q16 = new Question("A startup is a <blank> designed to deliver a new product or service under conditions of extreme uncertainty","human institution ","good idea","bad idea","corporate institution ",1,"A startup is a human institution designed to deliver a product or service under conditions of extreme uncertainty. This is a key component of the principle 'entrepreneurs are everywhere' highlighting that this mindset can be adopted regardless of the size of the company, sector or industry. ", "Lean", "Lean_startup", "Lean_startup#Overview", "X2YoHFuWkqs");
        addQuestion(q16);

        Question q17 = new Question("Which of the following is not a key aspect of innovation accounting?","Pivot or persevere ","Establish the baseline ","Tune the engine","Turn the engine ",4,"Innovation accounting is characterised by three main aspects including: establish the baseline (build an MVP), tune the engine (experiment and use feedback to build on MVP) and pivot or persevere (when experiment reaches diminishing returns, it's time to pivot)", "Lean", "Minimum_viable_product", "Lean_startup#Innovation_accounting", "0P7nCmln7PM");
        addQuestion(q17);

        Question q18 = new Question("The goal of the lean startup methodology is to create a sustainable <blank> not just a <blank>","product, institution ","person, product ","institution, product","product, person ",3,"The lean startup methodology goes well beyond just focusing on churning out a product. Rather, the goal is to create a sustainable institution, not just a product. ", "Lean", "Lean_thinking", "Lean_startup#Overview", "GPQExuB-lWw");
        addQuestion(q18);

        Question q19 = new Question("Value hypothesis tests if: ","product is bad for customers ","manufacturers like the product ","product is bad for manufacturers ","product is valuable to potential customers ",4,"Value comes prior to growth and a value hypothesis tests if a product is valuable to potential customers. It determines whether a customer should adopt the product into their lives. ", "Lean", "Lean_thinking", "Lean_thinking", "GPQExuB-lWw");
        addQuestion(q19);

        Question q20 = new Question("Growth hypothesis is an assumption on: ","how users find your product ","manufacturers liking the product ","product being valuable for customers ","good budget ",1,"Growth should only be focused upon when value has been established. A growth hypothesis is an assumption on how users find your product. ", "Lean", "Lean_thinking", "Lean_thinking", "GPQExuB-lWw");
        addQuestion(q20);
        //Design Q's
        Question q21 = new Question("Which classification of workplace type does the design thinking methodology fall under?","Conservative","Regressive","Progressive ","Avant-garde ",4,"The design thinking methodology is classified under the avant garde workplace type. Cross functional teams and platforms, creativity and iterative development are characteristic of this approach.  ", "Design", "Design_thinking", "Design_thinking", "a7sEoEvT8l8");
        addQuestion(q21);

        Question q22 = new Question("\"Fail fast, iterate quickly\" is characteristic of which step in the design thinking process?","Prototype","Test","Empathise","Ideate ",1,"Prototyping is the fourth stage of the design thinking process and is characterised by fast, iterative development. The motto \"fail fast, iterate quickly\" encompasses this concept. ", "Design", "Design_thinking", "Design_thinking#Implementation_and_prototyping", "qyoZTUGzdGY");
        addQuestion(q22);

        Question q23 = new Question("Diverge/Converge is an idea pertaining to which step in the design thinking process?","Empathise","Ideate ","Test ","Define",2,"Diverge and converge is characteristic of the ideate step in the design thinking process. The ideate step also includes sharing ideas, prioritising ideas and adopting the \"yes and\" thinking. ", "Design", "Design_methods", "Design_thinking#Ideation:_Divergent_and_convergent_thinking", "qyoZTUGzdGY");
        addQuestion(q23);

        Question q24 = new Question("What characteristics does the empathise stage encompass?","Personas","Share ideas","Non-judgmental ","Mockups",3,"The empathise stage encompasses seeking to understand pain points without judgement, typically through shadowing or conducting interviews. ", "Design", "Design_thinking", "Design_thinking#Empathy", "qyoZTUGzdGY");
        addQuestion(q24);

        Question q25 = new Question("Which of the following is not a stage in the double diamond process?","Develop ","Define ","Deliver ","Delete ",4,"There are four main stages to the double diamond framework including: discover, define, develop and deliver. ", "Design", "Double_Diamond_(design_process_model)", "Double_Diamond_(design_process_model)", "qyoZTUGzdGY");
        addQuestion(q25);

        Question q26 = new Question("Which of the following is not part of the empathise phase process?","Identify users ","Share ideas ","Consolidate findings ","Gain empathy ",2,"The empathise phase process comprises of: identifying users, followed by gaining empathy and consolidating findings. ", "Design", "Design_thinking", "Design_thinking#Empathy", "pXtN4y3O35M");
        addQuestion(q26);

        Question q27 = new Question("Which of the following is not part of the define phase process?","Consolidate findings ","Derive insights ","Iterate quickly ","Analyse findings ",3,"The define phase process comprises of: consolidating findings, analysing findings, followed by deriving insights. ", "Design", "Design_thinking", "Design_thinking#Methods_and_process", "pXtN4y3O35M");
        addQuestion(q27);

        Question q28 = new Question("What step do sketching, bodystorming and mindmapping relate to?","Ideate ","Empathise ","Define ","Test ",1,"Sketching, bodystorming and mindmapping are all techniques and tools used to brainstorm in the ideate phase of the design thinking process. ", "Design", "Design_thinking", "Design_thinking#Methods_and_process", "pXtN4y3O35M");
        addQuestion(q28);

        Question q29 = new Question("Which of the following is not a design thinking mindset?","Bias towards documentation ","Radical collaboration ","Show don’t tell ","Human centred ",1,"The design thinking mindset consists of the following: human centred, mindful of process, culture of prototyping, show don’t tell, bias towards action and radical collaboration. ", "Design", "Design_thinking", "Design_thinking#Principles", "w_JnHnEjEnM");
        addQuestion(q29);

        Question q30 = new Question("Which of the following are an aspect of empathy maps?","Pain points and action","Influences and feelings ","Feelings and emotion ","Tasks and feelings",2,"Empathy maps comprise of: tasks, influences, overall goal, painpoints and feelings. ", "Design", "Design_thinking", "Design_thinking#Empathy", "pXtN4y3O35M");
        addQuestion(q30);


    }

    private void addQuestion(Question question){
        Log.d(TAG, "addQuestion: Inserting into the db");
        //ContentValues instance created to specify column and data to be inserted
        ContentValues contentValues = new ContentValues();
        contentValues.put(QuizTable.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuizTable.COLUMN_OPTION1, question.getOpt1());
        contentValues.put(QuizTable.COLUMN_OPTION2, question.getOpt2());
        contentValues.put(QuizTable.COLUMN_OPTION3, question.getOpt3());
        contentValues.put(QuizTable.COLUMN_OPTION4, question.getOpt4());
        contentValues.put(QuizTable.COLUMN_ANSWER_NUMBER, question.getAnswerNum());
        contentValues.put(QuizTable.COLUMN_FEEDBACK, question.getFeedback());
        contentValues.put(QuizTable.COLUMN_CATEGORY, question.getCategory());
        contentValues.put(QuizTable.COLUMN_WIKI_INTRO, question.getWiki_intro());
        contentValues.put(QuizTable.COLUMN_WIKI_LINK, question.getWiki_link());
        contentValues.put(QuizTable.COLUMN_YOUTUBE, question.getYoutube());

        //Inserts question into db
        db.insert(QuizTable.TABLE_NAME, null, contentValues);

    }
    



    public List<Question> getCategoryQuestions(int category){
        //Following method return array of chosen category of questions

        List<Question> listQuestion = new ArrayList<>();
        //Instance of db created, and runes the onCreate() if not done so.
        db = getReadableDatabase();

        //Cursor to handle query results
        Cursor cursor = null;


        //case for each type of category chosen
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

        //Setting the attributes for the question objects
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
                question.setWiki_intro(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_WIKI_INTRO)));
                question.setWiki_link(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_WIKI_LINK)));
                question.setYoutube(cursor.getString(cursor.getColumnIndex(QuizTable.COLUMN_YOUTUBE)));

                //Adding the question objects to a question array
                listQuestion.add(question);
            }while(cursor.moveToNext());
        }
        //Closing the cursor
        cursor.close();
        //When this method is called, it will return array of desired questions
        return listQuestion;


    }


}
