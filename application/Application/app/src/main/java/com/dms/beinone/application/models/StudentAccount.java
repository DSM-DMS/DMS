package com.dms.beinone.application.models;

/**
 * Created by BeINone on 2017-02-24.
 */

public class StudentAccount {

    private String uid;
    private String id;
    private String password;

    public StudentAccount(String uid, String id, String password) {
        setUid(uid);
        setId(id);
        setPassword(password);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

}
