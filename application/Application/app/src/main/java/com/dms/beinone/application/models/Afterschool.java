package com.dms.beinone.application.models;

/**
 * Created by BeINone on 2017-01-31.
 */

public class Afterschool {

    private int no;
    private String title;
    private int target;
    private String place;
    private boolean onMonday;
    private boolean onTuesday;
    private boolean onWednesday;
    private boolean onSaturday;
    private String instructor;

    public Afterschool(int no, String title, int target, String place, boolean onMonday,
                       boolean onTuesday, boolean onWednesday, boolean onSaturday,
                       String instructor) {
        setNo(no);
        setTitle(title);
        setTarget(target);
        setPlace(place);
        setOnMonday(onMonday);
        setOnTuesday(onTuesday);
        setOnWednesday(onWednesday);
        setOnSaturday(onSaturday);
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

    public boolean isOnMonday() {
        return onMonday;
    }

    public void setOnMonday(boolean onMonday) {
        this.onMonday = onMonday;
    }

    public boolean isOnTuesday() {
        return onTuesday;
    }

    public void setOnTuesday(boolean onTuesday) {
        this.onTuesday = onTuesday;
    }

    public boolean isOnWednesday() {
        return onWednesday;
    }

    public void setOnWednesday(boolean onWednesday) {
        this.onWednesday = onWednesday;
    }

    public boolean isOnSaturday() {
        return onSaturday;
    }

    public void setOnSaturday(boolean onSaturday) {
        this.onSaturday = onSaturday;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

}
