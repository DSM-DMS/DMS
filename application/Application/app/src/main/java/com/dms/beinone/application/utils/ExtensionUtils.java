package com.dms.beinone.application.utils;

/**
 * Created by BeINone on 2017-06-01.
 */

public class ExtensionUtils {

    private static final String[] CLASS_STRING = {"가온실", "나온실", "다온실", "라온실", "3층 독서실", "4층 독서실", "5층 독서실"};

    public static String getStringFromClass(int clazz) {
        return CLASS_STRING[clazz - 1];
    }
}
