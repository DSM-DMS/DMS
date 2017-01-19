package org.boxfox.dms.utilities.dataio.post;

import java.util.ArrayList;
import java.util.List;

import org.boxfox.dms.utilities.database.QueryUtills;
import org.boxfox.dms.utilities.dataio.Parser;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PostChangeDetector {

	public PostChangeDetector() {
	}

	public void start(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	private void postDataSave(String url){
		int page = 1;
		while(true){
			Document doc = Jsoup.parse(QueryUtills.querySetter(url, page));
			String html = doc.html();
			html = html.substring(html.indexOf("var Posts=")+"var Posts=".length(), html.indexOf("post_list(Posts);")-1);
			JSONArray posts = (JSONArray)JSONValue.parse(html);
			if(posts.size()==1) break;
			
		}
	}

}
