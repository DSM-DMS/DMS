package com.dms.beinone.application.models;

/**
 * Created by BeINone on 2017-05-19.
 */

public class ItemListContent {

    private String status;

    public ItemListContent(String status) {
        setStatus(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
