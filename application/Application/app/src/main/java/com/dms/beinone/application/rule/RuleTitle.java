package com.dms.beinone.application.rule;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-01-20.
 */

public class RuleTitle implements Parent<RuleContent> {

    private String title;
    private List<RuleContent> ruleContentList;

    public RuleTitle(String title, List<RuleContent> ruleContentList) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
