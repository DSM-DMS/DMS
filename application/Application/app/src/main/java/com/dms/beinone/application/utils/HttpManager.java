package com.dms.beinone.application.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by BeINone on 2017-03-16.
 */

public class HttpManager {

    private static final String NAME_COOKIE = "Set-Cookie";

    public static List<String> getCookies(Map<String, List<String>> headers) {
        return headers.get(NAME_COOKIE);
    }

}
