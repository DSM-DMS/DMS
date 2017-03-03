package com.dms.boxfox.networking.datamodel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by boxfox on 2017-01-22.
 */
public class Response {
    //�� �ܿ� �ٸ� �ڵ� �߰� �ʿ�
    public static final int SUCCESS = 200;

    private int code;
    private String message;
    private StringBuffer buffer;
    public Response(int code, String msg){
        this.code = code;
        this.message = msg;
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

    public JSONObject getJsonObject() throws JSONException {
        return new JSONObject(buffer.toString());
    }
}
