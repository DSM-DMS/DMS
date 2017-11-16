package com.dms.beinone.application.models;

/**
 * Created by dsm2017 on 2017-11-12.
 */

public class SurveyList {

    private String title;
    private String date;

    public SurveyList (String title, String date) {

        this.title = title;
        this.date = date;
    }

    public void setTitle (String title) {

        this.title = title;
    }

    public void setDate (String date) {

        this.date = date;
    }

    public String getTitle () {

        return title;
    }

    public String getDate () {

        return date;
    }
}
