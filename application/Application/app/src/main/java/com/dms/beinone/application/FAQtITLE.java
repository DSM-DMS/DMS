package com.dms.beinone.application;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQTitle implements Parent<FAQContent> {

    private String title;

    @Override
    public List<FAQContent> getChildList() {
        return null;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
