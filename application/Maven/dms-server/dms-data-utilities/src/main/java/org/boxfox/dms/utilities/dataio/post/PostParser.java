package org.boxfox.dms.utilities.dataio.post;

import org.boxfox.dms.utilities.dataio.Parser;
import org.boxfox.dms.utilities.dataio.ParserUtills;
import org.boxfox.dms.utilities.datamodel.post.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.nodes.Document;

public class PostParser<T> extends Parser {
	private static final String link = "http://dsm.hs.kr/";
	private PostCategory category;

	public PostParser(PostCategory category, String postKey) {
		this.category = category;
		String targetURL = null;
		switch (category) {
		case BROAD:
			targetURL = URL_BROAD;
			break;
		case FAMILER:
			targetURL = URL_FAMILER;
			break;
		case MISSION:
			targetURL = URL_MISSION;
			break;
		case CHALLENGE:
			targetURL = URL_CHALLENGE;
			break;
		}
		url = targetURL.replaceFirst("?", postKey);
	}

	public Post parse() {
		Document doc = ParserUtills.getDoc(url);
		String title = doc.getElementsByClass("read_title").get(0).text();
		String writer = doc.getElementsByClass("user_icon").get(0).text();
		String dateTime = doc.getElementsByClass("text").get(0).text();
		String content = doc.getElementsByClass("context_view").get(0).html();
		String html = doc.html();
		html = html.substring(html.indexOf("var PostFiles = ") + "var PostFiles = ".length(),html.indexOf("var POST_ID="));
		JSONArray files = (JSONArray) JSONValue.parse(html);
		JSONArray pureFileList = new JSONArray();
		for (Object file : files) {
			JSONArray arr = (JSONArray) file;
			JSONObject fileObj = new JSONObject();
			fileObj.put("Name", arr.get(0).toString());
			fileObj.put("Link", arr.get(2).toString());
			pureFileList.add(fileObj);
		}
		Post post = new Post(title, writer, dateTime, content, pureFileList);
		return post;
	}

}
