package com.dms.boxfox.networking;

import android.content.Context;

import com.dms.beinone.application.utils.Cookie;
import com.dms.beinone.application.utils.CookieManager;
import com.dms.boxfox.networking.datamodel.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class MultipartUtility {

    public static final String KEY_HEADER_COOKIE = "Cookie";

    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private Context context;
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    public MultipartUtility(Context context, String requestURL, String charset)
            throws IOException {
        this.context = context;
        this.charset = charset;

        boundary = "===" + System.currentTimeMillis() + "===";

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

        List<Cookie> cookieList = new CookieManager(context).getAllCookies();
        addCookies(cookieList);
    }

    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"")
                .append(LINE_FEED);
        writer.append("Content-Type: text/plain; charset=" + charset).append(
                LINE_FEED);
        writer.append(LINE_FEED);
        writer.append(value).append(LINE_FEED);
        writer.flush();
    }

    public void addFilePart(String fileName, byte[] fileData) throws IOException {
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        outputStream.write(fileData);
        outputStream.flush();

        writer.append(LINE_FEED);
        writer.flush();
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     * @throws IOException
     */
    public void addFilePart(String fieldName, File uploadFile) throws IOException {
        String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }

    private void addCookie(Cookie cookie) {
        String cookieValue = cookie.getName() + "=" + cookie.getValue();
        addHeaderField(KEY_HEADER_COOKIE, cookieValue);
    }

    private void addCookies(List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            addCookie(cookie);
        }
    }

//    public void addFilePart(String fieldName, File uploadFile)
//            throws IOException {
//        String fileName = uploadFile.getName();
//        writer.append("--" + boundary).append(LINE_FEED);
//        writer.append(
//                "Content-Disposition: form-data; name=\"" + fieldName
//                        + "\"; filename=\"" + fileName + "\"")
//                .append(LINE_FEED);
//        writer.append(
//                "Content-Type: "
//                        + URLConnection.guessContentTypeFromName(fileName))
//                .append(LINE_FEED);
//        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
//        writer.append(LINE_FEED);
//        writer.flush();
//
//        FileInputStream inputStream = new FileInputStream(uploadFile);
//        byte[] buffer = new byte[4096];
//        int bytesRead = -1;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
//        outputStream.flush();
//        inputStream.close();
//
//        writer.append(LINE_FEED);
//        writer.flush();
//    }

    public void addHeaderField(String name, String value) {
        writer.append(name + ": " + value).append(LINE_FEED);
        writer.flush();
    }

    public Response finish() throws IOException {
//                    List<String> response = new ArrayList<String>();

        writer.append(LINE_FEED).flush();
        writer.append("--" + boundary + "--").append(LINE_FEED);
        writer.close();

        Response response = new Response(httpConn.getResponseCode(),
                httpConn.getResponseMessage(), httpConn.getHeaderFields());

        Scanner httpResponseScanner = new Scanner(httpConn.getInputStream());
        while (httpResponseScanner.hasNextLine()) {
            response.appendBody(httpResponseScanner.nextLine());
        }
        httpResponseScanner.close();

//                    int status = httpConn.getResponseCode();
//                    if (status == HttpURLConnection.HTTP_OK) {
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(
//                                httpConn.getInputStream()));
//                        String line = null;
//                        while ((line = reader.readLine()) != null) {
//                            response.add(line);
//                        }
//                        reader.close();
//                        httpConn.disconnect();
//                    } else {
//                        throw new IOException("Server returned non-OK status: " + status);
//                    }

        return response;
    }
}