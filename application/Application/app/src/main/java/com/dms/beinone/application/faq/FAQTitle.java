package com.dms.beinone.application.faq;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQTitle implements Parent<FAQContent> {

    private String title;
    private List<FAQContent> faqContentList;

    public FAQTitle(String title) {
        setTitle(title);
    }

    @Override
    public List<FAQContent> getChildList() {
        return faqContentList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setChildList(List<FAQContent> childitemList) {
        faqContentList = childitemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
