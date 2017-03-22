package com.dms.beinone.application.mypage;

/**
 * Created by BeINone on 2017-02-20.
 */

public class Student {

    private int number;
    private String name;
    private int merit;
    private int demerit;
    private String profileImage;

    public Student() {

    }

    public Student(int number, String name, int merit, int demerit) {
        setNumber(number);
        setName(name);
        setMerit(merit);
        setDemerit(demerit);
    }

    public Student(int number, String name, int merit, int demerit, String profileImage) {
        setNumber(number);
        setName(name);
        setMerit(merit);
        setDemerit(demerit);
        setProfileImage(profileImage);
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
