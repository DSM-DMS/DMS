package com.dms.beinone.application.stayapply;

/**
 * Created by BeINone on 2017-01-31.
 */

public class StayApplyUtils {

    public static final int FRIDAY_GO = 1;
    public static final int SATURDAY_GO = 2;
    public static final int SATURDAY_COME = 3;
    public static final int STAY = 4;

    public static String getString(int stayStatus) {
        String stayStatusString = null;

        if (stayStatus == FRIDAY_GO) stayStatusString = "금요귀가";
        else if (stayStatus == SATURDAY_GO) stayStatusString = "토요귀가";
        else if (stayStatus == SATURDAY_COME) stayStatusString = "토요귀사";
        else if (stayStatus == STAY) stayStatusString = "잔류";

        return stayStatusString;
    }

}
