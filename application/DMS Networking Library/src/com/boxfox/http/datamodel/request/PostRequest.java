package com.boxfox.http.datamodel.request;

public class PostRequest extends Request{

	public PostRequest(String url) {
		super(url, TYPE_POST);
	}

}
