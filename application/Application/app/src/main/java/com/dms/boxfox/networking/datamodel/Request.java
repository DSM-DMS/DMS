package com.dms.boxfox.networking.datamodel;

import com.dms.boxfox.networking.HttpBox;
import com.dms.boxfox.networking.HttpBoxException;
import com.dms.boxfox.networking.secure.AES256;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Request {
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT_TYPE = "Accept";
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final String USER_AGENT = "User-Agent";

    public static final String TYPE_POST = "POST";
    public static final String TYPE_GET = "GET";

    public static final String COMMAND = "Command";

    private String url, type, bodyData;
    private HashMap<String, String> header;

    private AES256 aes;

    public Request(String url, String type) {
        header = new HashMap<String, String>();
        this.url = url;
        this.type = type;
        setUserAgent();
        putHeaderProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
        aes = AES256.getDefault();
    }

    private Request setUserAgent() {
        //User Agent에 필요한 정보에 대해 회의 필요
        try {
            JSONObject obj = new JSONObject();
            obj.put("Version", "1.0.5 SNAPSHOOT");
            obj.put("UUID", "test");
            putHeaderProperty(USER_AGENT, obj.toString());
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

    public Request putBodyData(JSONObject obj) {
        String str = obj.toString();
        if(aes!=null)
            str = aes.encrypt(str);
        this.bodyData = str;
        return this;
    }

    public Request putBodyData(){
        return putBodyData(new JSONObject());
    }

    public String getBodyData() {
        return this.bodyData;
    }


}
