package com.dms.boxfox.networking.datamodel;

import android.content.Context;
import android.util.Log;

import com.dms.beinone.application.utils.Cookie;
import com.dms.beinone.application.utils.CookieManager;
import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxCallback;
import com.dms.boxfox.networking.HttpBoxException;
import com.dms.boxfox.networking.secure.AES256;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
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
        putHeaderProperty(KEY_HEADER_COOKIE, new CookieManager(context).getCookies());
        Log.d("testLog", new CookieManager(context).getCookies());
        putHeaderProperty(CONTENT_TYPE, CONTENT_TYPE_POST);
        putHeaderProperty(ACCEPT_TYPE, "*/*");
        aes = AES256.getDefault();
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

    public void push(HttpBoxCallback callback) throws IOException {
        HttpBox.push(this, callback);
    }

    public Request setCommand(int cmd) {
        putHeaderProperty(COMMAND, cmd + "");
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

    public Request putBodyData(String str) {
        if(aes!=null)
            str = aes.encrypt(str);
        this.bodyData = str;
        return this;
    }

    public Request putBodyData(JSONObject data) {
        try {
            putBodyData(getFormString(data));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Request putBodyData(){
        return putBodyData(new JSONObject());
    }

    public Request putQueryString(JSONObject data) {
        try {
            url += "?" + getFormString(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getBodyData() {
        return this.bodyData;
    }

    public String getFormString(JSONObject params) throws JSONException {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true; // 첫 번째 매개변수 여부

        Iterator<String> keys = params.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = params.getString(key);

            if (isFirst) isFirst = false;
            else result.append("&"); // 첫 번째 매개변수가 아닌 경우엔 앞에 &를 붙임

            try { // UTF-8로 주소에 키와 값을 붙임
                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

}
