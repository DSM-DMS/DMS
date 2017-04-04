package com.dms.boxfox.networking.datamodel;

import android.content.Context;

import com.dms.beinone.application.utils.Cookie;
import com.dms.beinone.application.utils.CookieManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxException;
import com.dms.boxfox.networking.secure.AES256;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Request {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT_TYPE = "Accept";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_POST = "application/x-www-form-urlencoded";
    public static final String KEY_HEADER_COOKIE = "Cookie";

    public static final String USER_AGENT = "User-Agent";

    public static final String TYPE_POST = "POST";
    public static final String TYPE_GET = "GET";
    public static final String TYPE_PATCH = "PATCH";
    public static final String TYPE_PUT = "PUT";
    public static final String TYPE_DELETE = "DELETE";

    public static final String COMMAND = "Command";

    private String url, type, bodyData;
    private HashMap<String, String> header;

    private AES256 aes;

    public Request(Context context, String url, String type) {
        header = new HashMap<String, String>();
        this.url = url;
        this.type = type;
        setUserAgent();
        putHeaderProperty(CONTENT_TYPE, CONTENT_TYPE_POST);
        putHeaderProperty(ACCEPT_TYPE, "*/*");
        aes = AES256.getDefault();
        List<Cookie> cookieList = new CookieManager(context).getAllCookies();
        setCookies(cookieList);
    }

    private Request setUserAgent() {
        //User Agent에 필요한 정보에 대해 회의 필요
        try {
            JSONObject obj = new JSONObject();
            obj.put("Version", "1.0.5 SNAPSHOOT");
            obj.put("UUID", "test");
            putHeaderProperty(USER_AGENT, obj.toString());
//            putHeaderProperty(USER_AGENT, "Mozilla/5.0 ( compatible )");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Response push() throws IOException {
        return HttpBox.push(this);
    }

    public Request setCommand(int cmd) {
        putHeaderProperty(COMMAND, cmd + "");
        return this;
    }

    private Request setCookie(Cookie cookie) {
        String cookieValue = cookie.getName() + "=" + cookie.getValue();
        putHeaderProperty(KEY_HEADER_COOKIE, cookieValue);
        return this;
    }

    private Request setCookies(List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            setCookie(cookie);
        }
        return this;
    }

    public Request putHeaderProperty(String key, String value) {
        if(aes!=null)
            value = aes.encrypt(value);
        header.put(key, value);
        return this;
    }

    public String getHeaderProperty(String key) {
        return header.get(key);
    }

    public int getHeaderPropertiesSize() {
        return header.keySet().size();
    }

    public String getProperty(String key) {
        return header.get(key);
    }

    public HeaderProperty getProperty(int index) throws HttpBoxException {
        if (index > getHeaderPropertiesSize())
            throw new HttpBoxException("Out of range in header properties");
        Iterator lists = header.keySet().iterator();
        while (lists.hasNext()) {
            String key = lists.next().toString();
            if (index <= 0)
                return new HeaderProperty(key, header.get(key));
            index--;
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Request setType(String type) {
        this.type = type;
        return this;
    }

    public String getUrl() {
        return this.url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public Request putBodyData(Map<String, String> map) {
        String str = getPostString(map);
        if (aes != null) {
            str = aes.encrypt(str);
        }

        if (type != null && type.equals(TYPE_GET)) {
            url += "?" + str;
        } else {
            bodyData = str;
        }

        return this;
    }

    public Request putBodyData(String str) {
        if(aes!=null)
            str = aes.encrypt(str);
        this.bodyData = str;
        return this;
    }

    public Request putBodyData(JSONObject obj) {
        String str = obj.toString();
        return putBodyData(str);
    }

    public Request putBodyData(){
        return putBodyData(new JSONObject());
    }

    public String getBodyData() {
        return this.bodyData;
    }

    public String getPostString(Map<String, String> map) {
        StringBuilder result = new StringBuilder();
        boolean first = true; // 첫 번째 매개변수 여부

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (first)
                first = false;
            else // 첫 번째 매개변수가 아닌 경우엔 앞에 &를 붙임
                result.append("&");

            try { // UTF-8로 주소에 키와 값을 붙임
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

}
