package com.dms.beinone.application.models;

/**
 * Created by dsm2016 on 2017-09-12.
 */

public class Notice {

    private int no;
    private String title;
    private String content;
    private String writer;

    public Notice(int no, String title, String content, String writer) {
        this.no = no;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }


}
