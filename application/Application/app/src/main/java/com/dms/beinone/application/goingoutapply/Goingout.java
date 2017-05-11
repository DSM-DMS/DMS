package com.dms.beinone.application.goingoutapply;

/**
 * Created by BeINone on 2017-03-01.
 */

public class Goingout {

    private boolean sat;
    private boolean sun;

    public Goingout(boolean sat, boolean sun) {
        setSat(sat);
        setSun(sun);
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
