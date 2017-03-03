package com.dms.beinone.application.mypage;

/**
 * Created by BeINone on 2017-02-20.
 */

public class Student {

    public int number;
    public String name;
    public boolean sex;
    public int status;
    public String phone;
    public String parent;
    public int merit;
    public int demerit;

    public Student() {

    }

    public Student(int number, String name, int merit, int demerit) {
        setNumber(number);
        setName(name);
        setMerit(merit);
        setDemerit(demerit);
    }

    public Student(int number, String name, boolean sex, int status, String phone,
                   String parent, int merit, int demerit) {
        setNumber(number);
        setName(name);
        setSex(sex);
        setStatus(status);
        setPhone(phone);
        setParent(parent);
        setMerit(merit);
        setDemerit(demerit);
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

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
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
}
