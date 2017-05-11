package com.dms.beinone.application.utils;

import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by BeINone on 2017-03-30.
 */

public class MultipartUtility {

    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private String charset;

    private StringBuilder dataStringBuilder;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     *
     * @param charset
     */
    public MultipartUtility(String charset) {
        this.charset = charset;
        dataStringBuilder = new StringBuilder();
        // creates a unique boundary based on time stamp
        boundary = "===" + System.currentTimeMillis() + "===";
//        httpConn = (HttpURLConnection) url.openConnection();
//        httpConn.setUseCaches(false);
//        httpConn.setDoOutput(true);    // indicates POST method
//        httpConn.setDoInput(true);
//        httpConn.setRequestProperty("Content-Type",
//                "multipart/form-data; boundary=" + boundary);
//        outputStream = httpConn.getOutputStream();
//        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
//                true);
    }

    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    public void addFormField(String name, String value) {
        dataStringBuilder.append("--").append(boundary).append(LINE_FEED);
        dataStringBuilder.append("Content-Disposition: form-data; name=\"")
                .append(name).append("\"").append(LINE_FEED);
        dataStringBuilder.append("Content-Type: text/plain; charset=")
                .append(charset).append(LINE_FEED);
        dataStringBuilder.append(LINE_FEED);
        dataStringBuilder.append(value).append(LINE_FEED);
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName  name attribute in <input type="file" name="..." />
     * @param uploadFile a File to be uploaded
     */
    public void addFilePart(String fieldName, byte[] uploadFile, String fileName)
            throws IOException {
        dataStringBuilder.append("--").append(boundary).append(LINE_FEED);
        dataStringBuilder.append("Content-Disposition: form-data; name=\"").append(fieldName)
                .append("\"; filename=\"").append(fileName).append("\"").append(LINE_FEED);
        dataStringBuilder.append("Content-Type: ")
                .append(URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
        dataStringBuilder.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        dataStringBuilder.append(LINE_FEED);

        dataStringBuilder.append(new String(uploadFile, 0, uploadFile.length));
        dataStringBuilder.append(LINE_FEED);
    }

    /**
     * Adds a header field to the request.
     *
     * @param name  - name of the header field
     * @param value - value of the header field
     */
    public void addHeaderField(String name, String value) {
        dataStringBuilder.append(name).append(": ").append(value).append(LINE_FEED);
    }

    /**
     * Completes the request and receives response from the server.
     *
     * @return String of body data
     */
    public String finish() {
        dataStringBuilder.append(LINE_FEED);
        dataStringBuilder.append("--").append(boundary).append("--").append(LINE_FEED);
        return dataStringBuilder.toString();
    }

    public String getBoundary() {
        return boundary;
    }

}