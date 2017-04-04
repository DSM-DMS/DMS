package com.dms.boxfox.networking;

import android.content.Context;
import android.util.Log;

import com.dms.boxfox.networking.datamodel.HeaderProperty;
import com.dms.boxfox.networking.datamodel.Request;
import com.dms.boxfox.networking.datamodel.Response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class HttpBox {
    //아래와 같이 사용
    //HttpBox.post().setCommand(1234).putBodyData(JsonObject).push();

    //해당 url 수정 필요
    public static final String SERVER_URL = "http://dsm2015.cafe24.com:80";

    public static Request post(Context context) {
        return new Request(context, SERVER_URL, Request.TYPE_GET);
    }

    public static Request post(Context context, String url) {
        return new Request(context, url, Request.TYPE_POST);
    }

    public static Request post(Context context, String path, String type) {
        return new Request(context, SERVER_URL + path, type);
    }

    public static Response push(Request request) throws HttpBoxException, IOException {
        URL serverUrl = new URL(request.getUrl());
        HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();
        urlConnection.setRequestMethod(request.getType());

        for (int i = 0; i < request.getHeaderPropertiesSize(); i++) {
            HeaderProperty property = request.getProperty(i);
            urlConnection.setRequestProperty(property.getKey(), property.getValue());
        }

        if (!urlConnection.getRequestMethod().equals(Request.TYPE_GET)) {
            urlConnection.setDoOutput(true);
        }

        if (request.getBodyData() != null) {
            BufferedWriter httpRequestBodyWriter = new BufferedWriter(
                    new OutputStreamWriter(urlConnection.getOutputStream()));
            Log.d("testLog", request.getBodyData());
            httpRequestBodyWriter.write(request.getBodyData());
            httpRequestBodyWriter.close();
        }

        Response response = new Response(urlConnection.getResponseCode(),
                urlConnection.getResponseMessage(), urlConnection.getHeaderFields());

        Scanner httpResponseScanner = new Scanner(urlConnection.getInputStream());
        while (httpResponseScanner.hasNextLine()) {
            response.appendBody(httpResponseScanner.nextLine());
        }
        httpResponseScanner.close();

        urlConnection.getHeaderFields();

        return response;
    }
}
