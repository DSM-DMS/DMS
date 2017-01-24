package com.dms.beinone.application.facilityreport;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-01-20.
 */

public class FacilityReport implements Parcelable {

    private String title;
    private String content;
    private int room;
    private String writeDate;
    private String writer;
    private String result;
    private String resultDate;

    public FacilityReport(String title, String content, int room, String writeDate, String writer,
                          String result, String resultDate) {
        setTitle(title);
        setContent(content);
        setRoom(room);
        setWriteDate(writeDate);
        setWriter(writer);
        setResult(result);
        setResultDate(resultDate);
    }

    public FacilityReport(Parcel in) {
        setTitle(in.readString());
        setContent(in.readString());
        setRoom(in.readInt());
        setWriteDate(in.readString());
        setWriter(in.readString());
        setResult(in.readString());
        setResultDate(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(room);
        dest.writeString(writeDate);
        dest.writeString(writer);
        dest.writeString(result);
        dest.writeString(resultDate);
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public static final Creator<FacilityReport> CREATOR = new Creator<FacilityReport>() {
        @Override
        public FacilityReport createFromParcel(Parcel source) {
            return new FacilityReport(source);
        }

        @Override
        public FacilityReport[] newArray(int size) {
            return new FacilityReport[size];
        }
    };

}
