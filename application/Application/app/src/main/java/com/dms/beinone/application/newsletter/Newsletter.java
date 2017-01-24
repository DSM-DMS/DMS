package com.dms.beinone.application.newsletter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-01-24.
 */

public class Newsletter implements Parcelable {

    private String title;
    private String content;
    private String writer;
    private String date;

    public Newsletter(String title, String content, String writer, String date) {
        setTitle(title);
        setContent(content);
        setWriter(writer);
        setDate(date);
    }

    public Newsletter(Parcel in) {
        setTitle(in.readString());
        setContent(in.readString());
        setWriter(in.readString());
        setDate(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(writer);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public static final Parcelable.Creator<Newsletter> CREATOR = new Parcelable.Creator<Newsletter>() {
        @Override
        public Newsletter createFromParcel(Parcel source) {
            return new Newsletter(source);
        }

        @Override
        public Newsletter[] newArray(int size) {
            return new Newsletter[size];
        }
    };

}
