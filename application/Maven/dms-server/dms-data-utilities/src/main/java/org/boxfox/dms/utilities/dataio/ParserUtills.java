package org.boxfox.dms.utilities.dataio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ParserUtills {

	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2017-01-14</date>
	// <summary>주어진 url에 접속하여 html을 가져옴</summary>
	// <parameter>접속하고자 하는 url</parameter>
	/// <remarks></remarks>
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

	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2017-01-14</date>
	/// <summary>url에 데이터를 삽입</summary>
	/// <parameter>
	/// url : 타겟 사이트의 url 파라미터 규격
	/// args : 규격에 채워질 argment
	/// </parameter>
	/// <remarks>
	/// url : http://dsm2015.cafe24.com?one=?&two=?
	/// args : abc, def
	/// return : http://dsm2015.cafe24.com?one=abc&two=def
	/// </remarks>
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
