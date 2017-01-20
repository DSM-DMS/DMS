package com.boxfox.http.datamodel;

public abstract class Request {
	public static final String TYPE_POST = "POST";
	public static final String TYPE_GET = "GET";
	private String url, type;
	
	public Request(String url, String type){
		this.url = url;
		this.type = type;
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
