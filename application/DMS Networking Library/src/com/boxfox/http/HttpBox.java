package com.boxfox.http;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.boxfox.http.datamodel.PostRequest;
import com.boxfox.http.datamodel.Request;
import com.boxfox.http.datamodel.Response;

public class HttpBox {

	public static PostRequest post(String url) {
		return new PostRequest(url);
	}

	public static Response push(Request request) throws HttpBoxException,IOException {
		URL serverUrl = new URL(request.getUrl());
		HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
		
		urlConnection.setRequestProperty("Cache-Control", "no-cache");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("Accept", "application/json");
		// Indicate that we want to write to the HTTP request body
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");

		// Writing the post data to the HTTP request body
		BufferedWriter httpRequestBodyWriter = new BufferedWriter(
				new OutputStreamWriter(urlConnection.getOutputStream()));
		httpRequestBodyWriter.write("visitorName=Johnny+Jacobs&luckyNumber=1234");
		httpRequestBodyWriter.close();

		// Reading from the HTTP response body
		Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
		while (httpResponseScanner.hasNextLine()) {
			System.out.println(httpResponseScanner.nextLine());
		}
		httpResponseScanner.close();
	}
}
