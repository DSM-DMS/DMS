package com.dms.api.post;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import org.json.simple.JSONArray;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/list/:category", method={HttpMethod.GET})
public class LoadPostsToList implements Handler<RoutingContext> {
	private static final String [] CATEGORY_LIST = {"faq", "rule", "notice"};
	@SuppressWarnings("unchecked")
	@Override
	public void handle(RoutingContext ctx) {
		String category = ctx.request().getParam("category");
		if(Guardian.matchParameters(category, CATEGORY_LIST)) {
			try {
				ResultSet rsForPreview = DB.executeQuery("SELECT * FROM notice_preview");
				rsForPreview.next();
				int previewNo = rsForPreview.getInt("no");
				
				ResultSet rs;
				
				if(!Guardian.checkParameters(ctx, "page", "limit")) {
					rs = DB.executeQuery("SELECT * FROM "+category+" order by no desc");
				} else {
					int page = Integer.parseInt(ctx.request().getParam("page"));
					int limit = Integer.parseInt(ctx.request().getParam("limit"));
					rs = DB.executeQuery("SELECT * FROM "+category+" order by no desc limit ", ((page - 1) * limit), ", ", limit);
				}
				
				JSONObject response = new JSONObject();
				JSONArray posts = new JSONArray();
				while(rs.next()) {
					JSONObject post = new JSONObject();
					
					post.put("no", rs.getInt("no"));
					post.put("title", rs.getString("title"));
					post.put("content", rs.getString("content"));
					
					if(category.equals("notice")) {
						post.put("writer", rs.getString("writer"));
						if(rs.getInt("no") == previewNo) {
							post.put("is_preview", true);
						} else {
							post.put("is_preview", false);
						}
					}
					
					posts.add(post);
				}
				
				response.put("num_if_post", posts.size());
				response.put("result", posts);
				
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}