package com.dms.beinone.application.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BeINone on 2017-03-16.
 */

public class CookieManager {

    private static final String HEADER_KEY_COOKIE = "Set-Cookie";

    private static final String NAME_PREFS_COOKIE = "CookiePrefs";
    private static final String KEY_PREFS_COOKIE_NAMES = "names";

    private static final String DELIMITER_COOKIE_NAMES = ",";

    private SharedPreferences mCookiePrefs;
    private SharedPreferences.Editor mCookieEditor;

    public CookieManager(Context context) {
        mCookiePrefs = context.getSharedPreferences(NAME_PREFS_COOKIE, Context.MODE_PRIVATE);
        mCookieEditor = mCookiePrefs.edit();
    }

    public String[] findCookieValue(Map<String, List<String>> headers, String key) {
        List<String> cookies = headers.get(HEADER_KEY_COOKIE);
        if (cookies != null) {
            for (String cookie : cookies) {
                String[] parsedCookies = cookie.split(";");
                for (int index = 0; index < parsedCookies.length; index++) {
                    String[] parsedValues = parsedCookies[index].split("=");
                    String finalKey = parsedValues[0];
                    String finalValue = parsedValues[1];
                    if (finalKey.equals(key)) {
                        return new String[]{finalKey, finalValue};
                    }
                }
            }
        }

        return null;
    }

    public void addCookie(Cookie cookie) {
        String cookieNames = mCookiePrefs.getString(KEY_PREFS_COOKIE_NAMES, null);
        if (cookieNames != null) {
            cookieNames += DELIMITER_COOKIE_NAMES + cookie.getName();
        } else {
            cookieNames = cookie.getName();
        }
        mCookieEditor.putString(KEY_PREFS_COOKIE_NAMES, cookieNames);

        mCookieEditor.putString(cookie.getName(), cookie.getValue()).apply();
    }

    public List<Cookie> getAllCookies() {
        List<Cookie> cookieList = new ArrayList<>();

        String cookieNames = mCookiePrefs.getString(KEY_PREFS_COOKIE_NAMES, null);
        if (cookieNames != null) {
            String[] parsedCookieNames = cookieNames.split(",");
            for (String key : parsedCookieNames) {
                String value = mCookiePrefs.getString(key, null);
                cookieList.add(new Cookie(key, value));
            }
        }

        return cookieList;
    }

}
