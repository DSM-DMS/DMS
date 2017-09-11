package com.dms.api.post.rule;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/rule", method={HttpMethod.POST})
public class UploadRule implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}
		
		DataBase database = DataBase.getInstance();
		
		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");
		
		if(!Guardian.checkParameters(title, content)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO rule(title, content) VALUES('", title, "', '", content, "')");
			
			ctx.response().setStatusCode(201).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
