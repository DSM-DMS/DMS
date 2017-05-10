package org.boxfox.dms.secure;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class VerifyRecaptcha {
	 
    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secret = "6LerpRgUAAAAACjXsLiXvBwSrmB1UdYCfliI8yME";
    private final static String USER_AGENT = "Mozilla/5.0";
 
    public static boolean verify(String gRecaptchaResponse){
    	if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }
         
        try{
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
        String postParams = "secret=" + secret + "&response="
                + gRecaptchaResponse;
 
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
 
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
 
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
 
        JSONObject object = (JSONObject)JSONValue.parse(new StringReader(response.toString()));
         
        return (Boolean)object.get("success");
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}