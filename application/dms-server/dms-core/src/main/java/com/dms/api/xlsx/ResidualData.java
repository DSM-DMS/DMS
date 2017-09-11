package com.dms.api.xlsx;

/**
 * Created by boxfox on 2017-03-04.
 */
public class ResidualData {
    private String id, name;
    private int number, status, merit, demerit, residualDefault;

    public int getResidualDefault() {
        return residualDefault;
    }

    public void setResidualDefault(int residualDefault) {
        this.residualDefault = residualDefault;
    }

    private boolean sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
