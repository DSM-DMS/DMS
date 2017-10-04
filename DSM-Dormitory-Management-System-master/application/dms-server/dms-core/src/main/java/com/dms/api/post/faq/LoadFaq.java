package com.dms.api.post.faq;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/faq", method={HttpMethod.GET})
public class LoadFaq implements Handler<RoutingContext> {
	@Deprecated
	@Override
	public void handle(RoutingContext ctx) {
		int no = Integer.parseInt(ctx.request().getParam("no"));
		if(!Guardian.checkParameters(no)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			ResultSet rs = DB.executeQuery("SELECT * FROM faq WHERE no=", no);
			JSONObject response = new JSONObject();
			
			if(rs.next()) {
				response.put("title", rs.getString("title"));
				response.put("content", rs.getString("content"));
				
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
