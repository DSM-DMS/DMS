package com.boxfox.http.datamodel;

public class PostRequest extends Request{

	public PostRequest(String url) {
		super(url, TYPE_POST);
	}

}
