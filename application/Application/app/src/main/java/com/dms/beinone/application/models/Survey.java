package com.dms.beinone.application.models;

/**
 * Created by 윤정현 on 2017-10-14.
 */

public class Survey {
    String title;
    String date;

    public Survey(String title, String date) {
        this.title = title;
        this.date = date;
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



}
