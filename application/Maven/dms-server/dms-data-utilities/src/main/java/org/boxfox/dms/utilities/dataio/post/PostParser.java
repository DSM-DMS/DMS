package org.boxfox.dms.utilities.dataio.post;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostParser {
	private static final String link = "http://dsm.hs.kr/";

	public void doParse() {
		try {
			HttpURLConnection httpConn = null;
			URL urls = new URL(url);
			httpConn = (HttpURLConnection) urls.openConnection();
			httpConn.setDoInput(true);
			InputStream is = httpConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			is.close();
			try {
				img = "'";
				String TEXT = sb.substring(
						sb.indexOf("<div class=\"read_title\">") + "<div class=\"read_title\">".length(),
						sb.indexOf("<div class=\"read_print\">"));
				TEXT = TEXT.substring(TEXT.indexOf("alt=\"\""), TEXT.indexOf("/div>"));
				TEXT = TEXT.substring(TEXT.indexOf("/>") + "/>".length(), TEXT.indexOf("<"));
				if (TEXT == null) {
					TEXT = sb.substring(sb.indexOf("<title>") + "<title>".length(), sb.indexOf("</title>"));
				}
				realtitle = TEXT;
				if (sb.indexOf("height=\"") > 0 && sb.indexOf("style=\"width:") > 0) {
					img = sb.substring(sb.indexOf("height=\""), sb.indexOf("\"width:"));
					if (img.contains("src")) {
						img = img.substring(img.indexOf("src=\"") + "src=\"".length(), img.indexOf("\" style="));
						if (!img.contains(link)) {
							img = link.substring(0, link.indexOf("?shell")) + img;
						}
						img = DataBase.queryBuilder("'", img, "'");
					} else {
						img = "NULL";
					}
				} else {
					img = "NULL";
				}
			} catch (StringIndexOutOfBoundsException e) {
				img = "NULL";
			}
		} catch (Exception e) {
			Log.e(e.toString());
		}

	}
	
	public String getRealTitle() {
		try {
			return realtitle.replaceAll("'", "`");
		} catch (NullPointerException e) {
			return "NULL";
		}
	}

	public void contents(String text) {
		text = text.replaceAll("'", "`");
		if (text.contains("\n") && text.split("\n").length > 0) {
			String list[] = text.split("\n");
			context = list[0];
			for (int i = 1; i < list.length; i++) {
				context += "/t/t/" + list[i];
			}
		} else
			context = text;
	}

	public String getImg() {
		if (img == null || img.equalsIgnoreCase("NULL") || img.equals("")) {
			return "NULL";
		}
		return img;

	}

	public void setFileNames(String[] fileNames) {
		String text;
		if (fileNames.length > 0) {
			JSONArray arr = new JSONArray();
			for (int i = 0; i < fileNames.length; i++) {
				if (fileNames[i].contains(link))
					arr.put(fileNames[i]);
				else
					arr.put(link + fileNames[i]);
			}
			text = arr.toString();
		} else {
			JSONArray arr = new JSONArray();
			text = arr.toString();
		}
		this.fileNames = text;
	}

	public void setFileLinks(String[] fileLinks) {
		String text;
		if (fileLinks.length > 0) {
			JSONArray arr = new JSONArray();
			for (int i = 0; i < fileLinks.length; i++) {
				if (fileLinks[i].contains(link))
					arr.put(fileLinks[i]);
				else
					arr.put(link + fileLinks[i]);
			}
			text = arr.toString();
		} else {
			JSONArray arr = new JSONArray();
			text = arr.toString();
		}
		this.fileLinks = text;
	}
	
}
