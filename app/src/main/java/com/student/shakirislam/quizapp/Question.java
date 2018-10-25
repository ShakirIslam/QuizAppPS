package com.student.shakirislam.quizapp;

public class Question {

    //Class to create Quesiton Objects

    private static final String TAG = "Question";
    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private int answerNum;
    private String feedback;
    private String category;
    private String wiki_intro;
    private String wiki_link;
    private String youtube;

    public Question(){
        //When querying from the db, create an empty question object and use setters to set values
    }

    public Question(String question, String opt1, String opt2, String opt3, String opt4, int answerNum, String feedback, String category, String wiki_intro, String wiki_link, String youtube) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answerNum = answerNum;
        this.feedback = feedback;
        this.category = category;
        this.wiki_intro = wiki_intro;
        this.wiki_link = wiki_link;
        this.youtube = youtube;

    }

    //Getters and setters for Question Attributes

    public String getWiki_intro() {
        return wiki_intro;
    }

    public void setWiki_intro(String wiki_intro) {
        this.wiki_intro = wiki_intro;
    }

    public String getWiki_link() {
        return wiki_link;
    }

    public void setWiki_link(String wiki_link) {
        this.wiki_link = wiki_link;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
}
