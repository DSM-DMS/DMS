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

	/// <author>BoxFox (rlatjdfo112@naver.com)</author>
	/// <date>2017-01-14</date>
	// <summary>二쇱뼱吏� url�뿉 �젒�냽�븯�뿬 html�쓣 媛��졇�샂</summary>
	// <parameter>�젒�냽�븯怨좎옄 �븯�뒗 url</parameter>
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
	/// <summary>url�뿉 �뜲�씠�꽣瑜� �궫�엯</summary>
	/// <parameter>
	/// url : ��寃� �궗�씠�듃�쓽 url �뙆�씪誘명꽣 洹쒓꺽
	/// args : 洹쒓꺽�뿉 梨꾩썙吏� argment
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
