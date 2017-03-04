package com.dms.boxfox.xlsx;

/**
 * Created by boxfox on 2017-03-04.
 */
public class ResidualData {
    private String id, name, phone, p_name, p_phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_phone() {
        return p_phone;
    }

    public void setP_phone(String p_phone) {
        this.p_phone = p_phone;
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
