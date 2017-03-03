package com.dms.beinone.application.goingoutapply;

/**
 * Created by BeINone on 2017-03-01.
 */

public class Goingout {

    private boolean date;
    private String reason;

    public Goingout(boolean date, String reason) {
        setDate(date);
        setReason(reason);
    }

    public boolean getDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
