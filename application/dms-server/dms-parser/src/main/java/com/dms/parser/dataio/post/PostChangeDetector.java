package com.dms.parser.dataio.post;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.log.Log;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.nodes.Document;

import com.dms.parser.dataio.ParserUtils;
import com.dms.parser.datamodel.post.Post;

public class PostChangeDetector {
	private static final String BROAD = "Broad";
	private static final String FAMILER = "Familer";
	private static final String CHALLENGE = "Challenge";
	private static final String CHECK_QUERY = "SELECT COUNT(*) from app_content where category=? AND number=?";

	private boolean run;
	private static PostChangeDetector instance;
	private PostUpdateListener listener;

	private PostChangeDetector() {
	}

	public static PostChangeDetector getInstance() {
		if (instance == null)
			instance = new PostChangeDetector();
		return instance;
	}

	public void start() {
		if (run)
			return;
		run = true;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				int currentCategory = Post.CATEGORY_NOTICE;
				while (run) {
					postDataSave(currentCategory);
					if (listener != null)
						listener.update(currentCategory);
					currentCategory++;
					if (currentCategory > Post.CATEGORY_COMPETITION) {
						currentCategory = Post.CATEGORY_NOTICE;
					}
				}
				run = false;
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public void stop() {
		run = false;
	}

	private void postDataSave(int category) {
		String url = PostParser.getUrlFromCategory(category);
		int page = 1;
		int count = 0;
		while (true) {
			Document doc = ParserUtils.getDoc(QueryUtils.querySetter(url, page++));
			String html = doc.html();
			html = html.substring(html.indexOf("var Posts=") + "var Posts=".length(),
					html.indexOf("post_list(Posts);"));
			html = html.substring(0, html.lastIndexOf(";"));
			JSONArray posts = (JSONArray) JSONValue.parse(html);
			if (posts.size() == 1)
				break;
			for (int i = 1; i < posts.size(); i++) {
				JSONArray post = ((JSONArray) posts.get(i));
				int postNum = Integer.valueOf(((String) ((JSONArray) ((JSONArray) post.get(0)).get(0)).get(1)));
				if (!check(category, postNum)) {
					PostParser parser = new PostParser(category, postNum, ((String) ((JSONArray) post.get(0)).get(2)));
					parser.parse();
					count++;
				}
			}
		}
		if (count > 0)
			Log.l(QueryUtils.queryBuilder(categoryToString(category), " finish parse count : ", count));
	}

	private boolean check(int category, int num) {
		boolean result = false;
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(QueryUtils.querySetter(CHECK_QUERY, category, num));
			if (rs.next())
				if (rs.getInt(1) == 1)
					result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private String categoryToString(int category) {
		switch (category) {
		case Post.CATEGORY_NOTICE:
			return BROAD;
		case Post.CATEGORY_NEWSLETTER:
			return FAMILER;
		case Post.CATEGORY_COMPETITION:
			return CHALLENGE;
		}
		return null;
	}

	public void setOnCategoryUpdateListener(PostUpdateListener postUpdateListener) {
		this.listener = postUpdateListener;
	}

}
