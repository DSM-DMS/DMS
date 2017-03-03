package com.dms.beinone.application.comment;

/**
 * Created by BeINone on 2017-01-25.
 */

public class Comment {

    private String writer;
    private String commentDate;
    private String content;

    public Comment(String writer, String commentDate, String content) {
        setWriter(writer);
        setCommentDate(commentDate);
        setContent(content);
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
