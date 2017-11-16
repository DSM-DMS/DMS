package com.dms.beinone.application.models;

import java.util.ArrayList;

/**
 * Created by 윤정현 on 2017-10-14.
 */

public class Survey {
    String title;
    String date;
    ArrayList<String> questionList;

    public Survey(String title, String date, ArrayList<String> questionList) {
        this.title = title;
        this.date = date;
        this.questionList = questionList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQuestionList(ArrayList<String > questionList) {

        this.questionList = questionList;
    }

    public ArrayList<String> getQuestionList() {

        return questionList;
    }
}
