package com.dms.beinone.application.models;

/**
 * Created by BeINone on 2017-02-20.
 */

public class Account {

    private int number;
    private String name;
    private int merit;
    private int demerit;
    private int room;
    private int seat;

    public Account() {

    }

    public Account(int number, String name, int merit, int demerit) {
        setNumber(number);
        setName(name);
        setMerit(merit);
        setDemerit(demerit);
    }

    public Account(int number, String name, int merit, int demerit, int room, int seat) {
        setNumber(number);
        setName(name);
        setMerit(merit);
        setDemerit(demerit);
        setRoom(room);
        setSeat(seat);
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

}
