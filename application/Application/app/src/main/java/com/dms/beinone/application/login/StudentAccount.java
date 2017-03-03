package com.dms.beinone.application.login;

/**
 * Created by BeINone on 2017-02-24.
 */

public class StudentAccount {

    public StudentAccount(String id, String password, String name, int number, boolean sex) {
        setId(id);
        setPassword(password);
        setName(name);
        setNumber(number);
        setSex(sex);
    }

    private String id;
    private String password;
    private String name;
    private int number;
    private boolean sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

}
