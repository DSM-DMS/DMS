package org.boxfox.dms.utilities.dataio.post;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class PostChangeDetector{
	
	public PostChangeDetector(){
	}
	
	public void start(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				
			}
		});
		thread.setDaemon(true);
		thread.start();
		list.add(thread);
	}

	/*html = doc.html();
	html = html.substring(html.indexOf("var Posts=")+"var Posts=".length(), html.indexOf("post_list(Posts);"));
	JSONArray posts = (JSONArray)JSONValue.parse(html);*/
}
