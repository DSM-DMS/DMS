package com.dms.beinone.application.models;

import java.util.List;

/**
 * Created by BeINone on 2017-07-10.
 */

public class Class {

    public static final String OPTION_MAP = "map";
    public static final String OPTION_STATUS = "status";

    public static final int CLASS_GA = 1;
    public static final int CLASS_NA = 2;
    public static final int CLASS_DA = 3;
    public static final int CLASS_RA = 4;
    public static final int CLASS_3 = 5;
    public static final int CLASS_4 = 6;
    public static final int CLASS_5 = 7;

    private int no;
    private String name;
    private List<List<String>> map;

    public Class(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<String>> getMap() {
        return map;
    }

    public void setMap(List<List<String>> map) {
        this.map = map;
    }
}
