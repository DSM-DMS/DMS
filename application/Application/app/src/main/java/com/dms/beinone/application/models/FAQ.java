package com.dms.beinone.application.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-01-19.
 */

public class FAQ implements Parent<FAQContent> {

    private int no;
    private String title;
    private List<FAQContent> faqContentList;

    public FAQ(int no, String title, List<FAQContent> faqContentList) {
        setNo(no);
        setTitle(title);
        setChildList(faqContentList);
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
