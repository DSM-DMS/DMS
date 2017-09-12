package com.dms.parser.dataio.post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dms.parser.dataio.ParserUtils;
import com.dms.parser.datamodel.post.Post;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.QueryUtils;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;

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
					try {
						postDataSave(currentCategory);
					}catch(Exception e){
					}
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

	private void postDataSave(int category) throws Exception{
		String url = PostParser.getUrlFromCategory(category);
		int page = 1;
		int count = 0;
		while (true) {
			Document doc = ParserUtils.getDoc(ParserUtils.getUrl(url, page++));
			Element body = doc.getElementsByTag("tbody").get(0);
			Elements elements = body.getElementsByTag("tr");
			if (elements.size() == 1)
				break;
			for (int i = 1; i < elements.size(); i++) {
				Element element = elements.get(i);
				int postNum = Integer.valueOf(element.getElementsByTag("td").get(0).text());
				if (!check(category, postNum)) {
					String onClick = element.getElementsByTag("a").get(0).attr("onclick");
					Pattern pattern = Pattern.compile("'([a-zA-Z0-9]*)'");
					Matcher matcher = pattern.matcher(onClick);
					List<String> list = new ArrayList<String>();
					while(matcher.find()){
						list.add(matcher.group(0).replaceAll("'", ""));
					}
					PostParser parser = new PostParser(category, postNum, list.toArray());
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
