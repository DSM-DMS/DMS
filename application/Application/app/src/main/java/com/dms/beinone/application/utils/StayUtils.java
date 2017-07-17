package com.dms.beinone.application.utils;

/**
 * Created by BeINone on 2017-01-31.
 */

public class StayUtils {

    private static final String[] STAY_STRING = {"금요귀가", "토요귀가", "토요귀사", "잔류"};

    public static final int FRIDAY_GO = 1;
    public static final int SATURDAY_GO = 2;
    public static final int SATURDAY_COME = 3;
    public static final int STAY = 4;

    public static String getStringFromStayStatus(int stayStatus) {
        return STAY_STRING[stayStatus - 1];
    }

}
