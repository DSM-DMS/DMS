package com.dms.beinone.application.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BeINone on 2017-03-13.
 */

public class Extension implements Parcelable {

    public static final String OPTION_MAP = "map";
    public static final String OPTION_STATUS = "status";

    public static final int CLASS_GA = 1;
    public static final int CLASS_NA = 2;
    public static final int CLASS_DA = 3;
    public static final int CLASS_RA = 4;
    public static final int CLASS_3 = 5;
    public static final int CLASS_4 = 6;
    public static final int CLASS_5 = 7;

    private String option;
    private int clazz;
    private int seat;

    public Extension(String option, int classId) {
        setOption(option);
        setClazz(classId);
    }

    public Extension(int clazz) {
        setClazz(clazz);
    }

    public Extension(int clazz, int seat) {
        setClazz(clazz);
        setSeat(seat);
    }

    public Extension(Parcel in) {
        setOption(in.readString());
        setClazz(in.readInt());
        setSeat(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(option);
        dest.writeInt(clazz);
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

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
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
