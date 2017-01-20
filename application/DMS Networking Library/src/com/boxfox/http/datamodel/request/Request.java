package com.boxfox.http.datamodel.request;

import java.util.HashMap;
import java.util.Iterator;

import com.boxfox.http.HttpBoxException;
import com.boxfox.http.datamodel.HeaderProperty;

public abstract class Request {
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String ACCEPT_TYPE = "Accept";
	public static final String CONTENT_TYPE_HTML = "text/html";
	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String USER_AGENT = "User-Agent";
	
	public static final String TYPE_POST = "POST";
	public static final String TYPE_GET = "GET";
	
	private String url, type;
	private HashMap<String, String> header;
	
	public Request(String url, String type){
		header = new HashMap<String, String>();
		this.url = url;
		this.type = type;
	}
	
	public void putHeaderProperty(String key, String value){
		header.put(key, value);
	}
	
	public String getHeaderProperty(String key){
		return header.get(key);
	}
	
	public int getHeaderPropertiesSize(){
		return header.keySet().size();
	}
	
	public HeaderProperty getProperty(int index) throws HttpBoxException{
		if(index > getHeaderPropertiesSize())
			throw new HttpBoxException("Out of range in header properties");
		Iterator lists = header.keySet().iterator();
		while(lists.hasNext()){
			lists.next();
			index--;
			if(index<=0)
			return new HeaderProperty(lists.next().toString(),header.get(lists.next()));
		}
		return null;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}

}
