package com.dms.beinone.application.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by BeINone on 2017-07-15.
 */

public class ApplyStatus {

    @SerializedName("extension_applied")
    private boolean extensionApplied;
    @SerializedName("class")
    private int extensionClass;
    @SerializedName("name")
    private String extensionName;

    @SerializedName("goingout_applied")
    private boolean goingoutApplied;
    @SerializedName("sat")
    private boolean goingoutSat;
    @SerializedName("sun")
    private boolean goingoutSun;

    @SerializedName("stay_applied")
    private boolean stayApplied;
    @SerializedName("value")
    private int stayValue;

    public boolean isExtensionApplied() {
        return extensionApplied;
    }

    public void setExtensionApplied(boolean extensionApplied) {
        this.extensionApplied = extensionApplied;
    }

    public int getExtensionClass() {
        return extensionClass;
    }

    public void setExtensionClass(int extensionClass) {
        this.extensionClass = extensionClass;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public boolean isGoingoutApplied() {
        return goingoutApplied;
    }

    public void setGoingoutApplied(boolean goingoutApplied) {
        this.goingoutApplied = goingoutApplied;
    }

    public boolean isGoingoutSat() {
        return goingoutSat;
    }

    public void setGoingoutSat(boolean goingoutSat) {
        this.goingoutSat = goingoutSat;
    }

    public boolean isGoingoutSun() {
        return goingoutSun;
    }

    public void setGoingoutSun(boolean goingoutSun) {
        this.goingoutSun = goingoutSun;
    }

    public boolean isStayApplied() {
        return stayApplied;
    }

    public void setStayApplied(boolean stayApplied) {
        this.stayApplied = stayApplied;
    }

    public int getStayValue() {
        return stayValue;
    }

    public void setStayValue(int stayValue) {
        this.stayValue = stayValue;
    }
}
