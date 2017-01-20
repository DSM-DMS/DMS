package com.dms.beinone.application.notice;

/**
 * Created by BeINone on 2017-01-19.
 */

public class Notice {

    private String title;
    private String writer;
    private String date;

    public Notice(String title, String writer, String date) {
        setTitle(title);
        setWriter(writer);
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
