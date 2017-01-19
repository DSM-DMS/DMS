package org.boxfox.dms.utilities.dataio.post;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.dataio.Parser;
import org.boxfox.dms.utilities.dataio.ParserUtils;
import org.boxfox.dms.utilities.datamodel.post.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PostChangeDetector {
	private static final String CHECK_QUERY = "SELECT COUNT(*) from app_content where category=? AND number=?";
	private boolean run;
	private static PostChangeDetector instance;

	private PostChangeDetector() {
	}
	
	public static PostChangeDetector getInstance(){
		if(instance==null)
			instance = new PostChangeDetector();
		return instance;
	}

	public void start() {
		System.out.println("test");
		run = true;
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int currentCategory = Post.CATEGORY_BROAD;
				while(run){
					postDataSave(currentCategory);
					if(currentCategory>Post.CATEGORY_CHALLENGE){
						currentCategory = Post.CATEGORY_BROAD;
					}
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	public void stop(){
		run = false;
	}

	private void postDataSave(int category) {
		String url = PostParser.getUrlFromCategory(category);
		int page = 1;
		while (true) {
			Document doc = ParserUtils.getDoc(QueryUtils.querySetter(url, page++));
			String html = doc.html();
			html = html.substring(html.indexOf("var Posts=") + "var Posts=".length(), html.indexOf("post_list(Posts);"));
			html = html.substring(0,html.lastIndexOf(";"));
			JSONArray posts = (JSONArray) JSONValue.parse(html);
			if(posts.size()==1) break;
			for (int i = 1; i < posts.size(); i++) {
				JSONArray post = ((JSONArray)posts.get(i));
				int postNum = Integer.valueOf(((String) ((JSONArray) ((JSONArray) post.get(0)).get(0)).get(1)));
				if (!check(category, postNum)) {
					PostParser parser = new PostParser(category,((String)((JSONArray)post.get(0)).get(2)));
					parser.parse();
				}
			}
		}
	}

	private boolean check(int category, int num) {
		boolean result = false;
		try {
			ResultSet rs = DataBase.getInstance().executeQuery(QueryUtils.querySetter(CHECK_QUERY, category, num));
			if (rs.next())
				if (rs.getInt(1) == 1)
					result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
