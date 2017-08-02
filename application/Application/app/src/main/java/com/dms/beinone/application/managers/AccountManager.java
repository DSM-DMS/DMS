package com.dms.beinone.application.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BeINone on 2017-08-02.
 */

public class AccountManager {

    private static final String PREFS_NAME_ACCOUNT = "accountPrefs";
    private static final String PREFS_KEY_LOGINED = "logined";

    public static void setLogined(Context context, boolean logined) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME_ACCOUNT, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(PREFS_KEY_LOGINED, logined).apply();
    }

    public static boolean isLogined(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME_ACCOUNT, Context.MODE_PRIVATE);
        return prefs.getBoolean(PREFS_KEY_LOGINED, false);
    }
}
