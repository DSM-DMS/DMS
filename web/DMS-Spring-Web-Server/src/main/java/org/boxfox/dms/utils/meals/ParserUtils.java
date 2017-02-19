package org.boxfox.dms.utils.meals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParserUtils {

	public static Document getDoc(String url) {
		Document resultDocument = null;
		try {
			StringBuffer buffer = new StringBuffer();
			URL urlObj = new URL(url);
			URLConnection connection = urlObj.openConnection();
			connection.setDoInput(true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			resultDocument = Jsoup.parse(buffer.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultDocument;
	}

	public static String getUrl(String url, Object... args) {
		StringBuilder builder = new StringBuilder();
		String[] subUrl = url.split("[?]");
		builder.append(subUrl[0]);
		builder.append("?");
		for (int i = 1; i < subUrl.length && i - 1 < args.length; i++) {
			builder.append(subUrl[i]);
			builder.append(args[i - 1]);
		}
		return builder.toString();
	}
}
