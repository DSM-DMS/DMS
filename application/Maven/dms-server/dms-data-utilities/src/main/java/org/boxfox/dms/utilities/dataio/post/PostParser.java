package org.boxfox.dms.utilities.dataio.post;

import org.boxfox.dms.utilities.dataio.Parser;
import org.boxfox.dms.utilities.dataio.ParserUtills;
import org.boxfox.dms.utilities.datamodel.post.Attachment;
import org.boxfox.dms.utilities.datamodel.post.AttachmentList;
import org.boxfox.dms.utilities.datamodel.post.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.nodes.Document;

public class PostParser<T> extends Parser {
	public static int CATEGORY_BROAD = 0;
	public static int CATEGORY_FAMILER = 1;
	public static int CATEGORY_MISSION = 2;
	public static int CATEGORY_CHALLENGE = 3;
	private int category;

	public PostParser(int category, String postKey) {
		this.category = category;
		url = LINK + postKey;
	}

	public Post parse() {
		Document doc = ParserUtills.getDoc(url);
		String title = doc.getElementsByClass("read_title").get(0).text();
		String writer = doc.getElementsByClass("user_icon").get(0).text();
		String dateTime = doc.getElementsByClass("text").get(0).text();
		String content = doc.getElementsByClass("context_view").get(0).html();
		String html = doc.html();
		html = html.substring(html.indexOf("var PostFiles = ") + "var PostFiles = ".length(),
				html.indexOf("var POST_ID="));
		JSONArray files = (JSONArray) JSONValue.parse(html);
		AttachmentList<Attachment> list = new AttachmentList<Attachment>(Attachment.class);
		
		//post number key
		int number = 1223;
		
		for (Object file : files) {
			JSONArray arr = (JSONArray) file;
			Attachment attachment = new Attachment(number, arr.get(0).toString(), arr.get(2).toString());
			list.add(attachment);
		}
		Post post = new Post(number, title, writer, dateTime, content, list);
		return post;
	}

}
