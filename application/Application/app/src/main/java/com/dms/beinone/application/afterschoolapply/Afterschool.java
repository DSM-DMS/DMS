package com.dms.beinone.application.afterschoolapply;

/**
 * Created by BeINone on 2017-01-31.
 */

public class Afterschool {

    private int no;
    private String title;
    private int target;
    private String place;
    private int day;
    private String instructor;

    public Afterschool(int no, String title, int target, String place, int day, String instructor) {
        setNo(no);
        setTitle(title);
        setTarget(target);
        setPlace(place);
        setDay(day);
        setInstructor(instructor);
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

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

}
