package com.dms.beinone.application.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BeINone on 2017-08-01.
 */

public class Account {

    private int number;
    private String name;
    private int merit;
    private int demerit;
    private int room;
    private int seat;
    @SerializedName("stay_value")
    private int stayValue;

    public Account(int number, String name, int merit, int demerit, int room, int seat, int stayValue) {
        this.number = number;
        this.name = name;
        this.merit = merit;
        this.demerit = demerit;
        this.room = room;
        this.seat = seat;
        this.stayValue = stayValue;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMerit() {
        return merit;
    }

    public void setMerit(int merit) {
        this.merit = merit;
    }

    public int getDemerit() {
        return demerit;
    }

    public void setDemerit(int demerit) {
        this.demerit = demerit;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getStayValue() {
        return stayValue;
    }

    public void setStayValue(int stayValue) {
        this.stayValue = stayValue;
    }
}
