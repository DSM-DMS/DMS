package com.dms.boxfox.networking;

import android.content.Context;
import android.os.AsyncTask;

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

    private static final String SERVER_URL = "http://dsm2015.cafe24.com";

    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_NO_CONTENT = 204;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_CONFLICT = 409;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;

    public static Request post(Context context, String path) {
        return init(context, SERVER_URL + path, Request.TYPE_POST);
    }

    public static Request get(Context context, String path) {
        return init(context, SERVER_URL + path, Request.TYPE_GET);
    }

    public static Request put(Context context, String path) {
        return init(context, SERVER_URL + path, Request.TYPE_PUT);
    }

    public static Request patch(Context context, String path) {
        return init(context, SERVER_URL + path, Request.TYPE_PATCH);
    }

    public static Request delete(Context context, String path) {
        return init(context, SERVER_URL + path, Request.TYPE_DELETE);
    }

    public static Request init(Context context, String path, String type) {
        return new Request(context, path, type);
    }

    public static void push(final Request request, final HttpBoxCallback callback) {
        new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(Void... params) {
                try {
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
                } catch (HttpBoxException | IOException e) {
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (o instanceof Response) {
                    callback.done((Response) o);
                } else {
                    callback.err((Exception) o);
                }
            }
        }.execute();
    }
}
