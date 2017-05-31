package com.dms.boxfox.networking.datamodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by boxfox on 2017-01-22.
 */
public class Response {
    //ÀÌ ¿Ü¿¡ ´Ù¸¥ ÄÚµå Ãß°¡ ÇÊ¿ä
    public static final int SUCCESS = 200;

    private int code;
    private String message;
    private StringBuffer buffer;
    private Map<String, List<String>> headers;

    public Response(int code, String msg, Map<String, List<String>> headers){
        this.code = code;
        this.message = msg;
        this.headers = headers;
        buffer = new StringBuffer();
    }

    public void appendBody(String str){
        buffer.append(str);
        buffer.append("\n");
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public JSONObject getJsonObject() throws JSONException {
        return new JSONObject(buffer.toString());
    }

    public JSONArray getJsonArray() throws JSONException {
        return new JSONArray(buffer.toString());
    }

}
