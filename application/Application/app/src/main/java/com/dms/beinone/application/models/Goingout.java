package com.dms.beinone.application.models;

/**
 * Created by BeINone on 2017-07-15.
 */

public class Goingout {

    private boolean sat;
    private boolean sun;

    public Goingout(boolean sat, boolean sun) {
        this.sat = sat;
        this.sun = sun;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }
}
