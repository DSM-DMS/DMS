package com.dms.beinone.application;

/**
 * Created by BeINone on 2017-02-23.
 */

public class StudentUtils {

    public static String numberToString(int number) {
        String sNumber = String.valueOf(number);

        StringBuilder numberStringBuilder = new StringBuilder();

        // grade
        numberStringBuilder.append(sNumber.charAt(0)).append("학년 ");

        // class
        if (sNumber.charAt(1) != '0') {
            numberStringBuilder.append(sNumber.charAt(1));
        }
        numberStringBuilder.append(sNumber.charAt(2)).append("반 ");

        // number
        if (sNumber.charAt(3) != '0') {
            numberStringBuilder.append(sNumber.charAt(3));
        }
        numberStringBuilder.append(sNumber.charAt(4)).append("번");

        return numberStringBuilder.toString();
    }

}
