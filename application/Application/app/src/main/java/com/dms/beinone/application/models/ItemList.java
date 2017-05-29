package com.dms.beinone.application.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by BeINone on 2017-05-19.
 */

public class ItemList implements Parent<ItemListContent> {

    private String title;
    private List<ItemListContent> itemListContents;

    public ItemList(String title, List<ItemListContent> itemListContents) {
        setTitle(title);
        setChildList(itemListContents);
    }

    @Override
    public List<ItemListContent> getChildList() {
        return itemListContents;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public void setChildList(List<ItemListContent> childitemList) {
        itemListContents = childitemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
