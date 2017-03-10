package com.dms.boxfox.templates.post.data;

/**
 * Created by boxfox on 2017-03-10.
 */
public class PostTemplate {
    private String category, koreanName;
    private String [] heads;
    private String [] columns;

    public PostTemplate(String category, String koreanName, String [] heads, String [] columns){
        this.category = category;
        this.koreanName = koreanName;
        this.heads = heads;
        this.columns = columns;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getHeads() {
        return heads;
    }

    public void setHeads(String[] heads) {
        this.heads = heads;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public void setKoreanName(String koreanName) {
        this.koreanName = koreanName;
    }
}
