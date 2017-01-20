package com.boxfox.http.datamodel;

public class GetRequest extends Request{

	public GetRequest(String url) {
		super(url, TYPE_GET);
	}

}
