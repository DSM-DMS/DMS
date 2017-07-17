package com.dms.beinone.application.models;

import com.google.gson.JsonDeserializer;

import java.util.List;

/**
 * Created by BeINone on 2017-01-24.
 */

public class Meal {

    private List<String> breakfast;
    private List<String> lunch;
    private List<String> dinner;

    public Meal(List<String> breakfast, List<String> lunch, List<String> dinner) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
}
