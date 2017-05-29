package com.dms.beinone.application.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class Rule implements Parent<RuleContent> {

    private int no;
    private String title;
    private List<RuleContent> ruleContentList;

    public Rule(int no, String title, List<RuleContent> ruleContentList) {
        setTitle(title);
        setChildList(ruleContentList);
    }

    @Override
    public List<RuleContent> getChildList() {
        return ruleContentList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setChildList(List<RuleContent> ruleContentList) {
        this.ruleContentList = ruleContentList;
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
