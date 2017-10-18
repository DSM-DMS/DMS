package com.dms.api.post.notice.preview;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/notice/preview", method={HttpMethod.GET})
public class LoadPreview implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		JSONObject response = new JSONObject();
		
		try {
			ResultSet previewSet = DB.executeQuery("SELECT * FROM notice_preview");
			
			if(previewSet.next()) {
				int no = previewSet.getInt("no");
				
				ResultSet noticeSet = DB.executeQuery("SELECT * FROM notice WHERE no=?", no);
				
				if(noticeSet.next()) {
					response.put("title", noticeSet.getString("title"));
					response.put("content", noticeSet.getString("content"));
					
					ctx.response().setStatusCode(200);
					ctx.response().end(response.toString());
					ctx.response().close();
				} else {
					ctx.response().setStatusCode(204).end();
					ctx.response().close();
				}
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			e.printStackTrace();
		}
	}
}
