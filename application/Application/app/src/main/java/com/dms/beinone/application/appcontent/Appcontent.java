package com.dms.beinone.application.appcontent;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by BeINone on 2017-01-26.
 */

public class Appcontent implements Parcelable {

    public static final int NOTICE = 0;
    public static final int NEWSLETTER = 1;
    public static final int COMPETITION = 2;

    private int category;
    private int number;
    private String title;
    private String content;
    private String writer;
    private String date;
    private List<Attachment> attachmentList;

    public Appcontent(int category, int number, String title, String writer, String date) {
        setCategory(category);
        setNumber(number);
        setTitle(title);
        setWriter(writer);
        setDate(date);
    }

    public Appcontent(int category, int number, String title, String content, String writer, String date, List<Attachment> attachmentList) {
        setCategory(category);
        setNumber(number);
        setTitle(title);
        setContent(content);
        setWriter(writer);
        setDate(date);
        setAttachmentList(attachmentList);
    }

    public Appcontent(Parcel in) {
        setCategory(in.readInt());
        setNumber(in.readInt());
        setTitle(in.readString());
        setContent(in.readString());
        setWriter(in.readString());
        setDate(in.readString());
        in.readList(attachmentList, List.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(category);
        dest.writeInt(number);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(writer);
        dest.writeString(date);
        dest.writeList(attachmentList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public static final Parcelable.Creator<Appcontent> CREATOR = new Parcelable.Creator<Appcontent>() {
        @Override
        public Appcontent createFromParcel(Parcel source) {
            return new Appcontent(source);
        }

        @Override
        public Appcontent[] newArray(int size) {
            return new Appcontent[size];
        }
    };

}
