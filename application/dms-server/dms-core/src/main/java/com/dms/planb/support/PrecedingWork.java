package com.dms.planb.support;

import com.google.common.net.HttpHeaders;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.ext.web.RoutingContext;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class PrecedingWork {
    public static RoutingContext putHeaders(RoutingContext context) {
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/*");
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com/");
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://dsm2015.cafe24.com");
        context.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        Log.l("Requested : " + context.request().host());
        Log.l("Absolute URI : " + context.request().absoluteURI());
        Log.l("Parameters : " + context.request().params());

        return context;
    }

    public static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params) {
            String name = param.split("=")[0];
            String value = "";

            try {
                value = URLDecoder.decode(param.split("=")[1], "UTF-8");

            } catch (Exception e) {
                System.out.println("wtf exception: " + e.getMessage());
            }


            map.put(name, value);
        }

        return map;
    }
}
