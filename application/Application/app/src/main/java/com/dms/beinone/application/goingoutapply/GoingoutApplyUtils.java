package com.dms.beinone.application.goingoutapply;

/**
 * Created by BeINone on 2017-03-01.
 */

public class GoingoutApplyUtils {

    public static final boolean SATURDAY = false;
    public static final boolean SUNDAY = true;

    public static String getStringFromApplyDate(boolean applyDate) {
        if (applyDate == SATURDAY) {
            return "토요일";
        } else {
            return "일요일";
        }
    }

}
