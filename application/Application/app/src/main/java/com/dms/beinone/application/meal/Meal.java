package com.dms.beinone.application.meal;

/**
 * Created by BeINone on 2017-01-24.
 */

public class Meal {

    private String breakfast;
    private String lunch;
    private String dinner;
    private String breakfastAllergy;
    private String lunchAllergy;
    private String dinnerAllergy;

    public Meal() {

    }

    public Meal(String breakfast, String lunch, String dinner,
                String breakfastAllergy, String lunchAllergy, String dinnerAllergy) {
        setBreakfast(breakfast);
        setLunch(lunch);
        setDinner(dinner);
        setBreakfast(breakfastAllergy);
        setLunch(lunchAllergy);
        setDinner(dinnerAllergy);
    }

    public String get(int index) {
        switch (index) {
            case 0: return breakfast;
            case 1: return lunch;
            case 2: return dinner;
            default: return null;
        }
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getBreakfastAllergy() {
        return breakfastAllergy;
    }

    public void setBreakfastAllergy(String breakfastAllergy) {
        this.breakfastAllergy = breakfastAllergy;
    }

    public String getLunchAllergy() {
        return lunchAllergy;
    }

    public void setLunchAllergy(String lunchAllergy) {
        this.lunchAllergy = lunchAllergy;
    }

    public String getDinnerAllergy() {
        return dinnerAllergy;
    }

    public void setDinnerAllergy(String dinnerAllergy) {
        this.dinnerAllergy = dinnerAllergy;
    }

}
