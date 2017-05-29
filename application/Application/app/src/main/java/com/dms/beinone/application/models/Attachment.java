package com.dms.beinone.application.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-01-31.
 */

public class Attachment implements Parcelable {

    private String name;
    private String link;

    public Attachment(String name, String link) {
        setName(name);
        setLink(link);
    }

    public Attachment(Parcel in) {
        setName(in.readString());
        setLink(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel source) {
            return new Attachment(source);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };

}
