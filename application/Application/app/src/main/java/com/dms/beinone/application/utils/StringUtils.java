package com.dms.beinone.application.utils;

/**
 * Created by BeINone on 2017-07-11.
 */

public class StringUtils {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
