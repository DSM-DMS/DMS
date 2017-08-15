package com.dms.beinone.application.models;

import java.util.ArrayList;

/**
 * Created by root1 on 2017. 8. 10..
 */

public class AfterSchoolClass {
    private String questionName;

    private ArrayList<String> answerList;

    public AfterSchoolClass(String questionName, ArrayList<String> answerList) {
        this.questionName = questionName;
        this.answerList = answerList;
    }

    public String getQuestionName() {
        return questionName;
    }

    public ArrayList<String> getAnswerList() {
        return answerList;
    }
}
