package com.boxfox.dms.response;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class JsonBuilder {
	private int code;
	private String msg;
	private HashMap<String, Object> map;
	
	public JsonBuilder(int code, String msg){
		this.code = code;
		this.msg = msg;
		this.map = new HashMap<String, Object>();
	}
	
	public JsonBuilder put(String key, Object value){
		map.put(key, value);
		return this;
	}
	
	@Override
	public String toString(){
		JSONObject obj = new JSONObject();
		obj.put("ResultCode", code);
		obj.put("Message", msg);
		for(Object key : map.keySet()){
			obj.put(key, map.get(key));
		}
		return obj.toJSONString();
	}
	
	public static JsonBuilder build(int code, String msg){
		return new JsonBuilder(code, msg);
	}

}
