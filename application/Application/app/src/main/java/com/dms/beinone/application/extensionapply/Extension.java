package com.dms.beinone.application.extensionapply;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-03-13.
 */

public class Extension implements Parcelable {

    public static final int CLASS_GA = 1;
    public static final int CLASS_NA = 2;
    public static final int CLASS_DA = 3;
    public static final int CLASS_RA = 4;

    private String option;
    private int classId;
    private int seat;

    public Extension(String option, int classId) {
        setOption(option);
        setClassId(classId);
    }

    public Extension(int classId, int seat) {
        setClassId(classId);
        setSeat(seat);
    }

    public Extension(Parcel in) {
        setOption(in.readString());
        setClassId(in.readInt());
        setSeat(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(option);
        dest.writeInt(classId);
        dest.writeInt(seat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public static final Parcelable.Creator<Extension> CREATOR = new Parcelable.Creator<Extension>() {
        @Override
        public Extension createFromParcel(Parcel source) {
            return new Extension(source);
        }

        @Override
        public Extension[] newArray(int size) {
            return new Extension[size];
        }
    };

}
