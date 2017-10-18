package com.dms.api.post.bug;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/bug", method = { HttpMethod.POST })
public class UploadBug implements Handler<RoutingContext> {
	public void handle(RoutingContext context) {
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");

		if (!Guardian.checkParameters(title, content)) {
			context.response().setStatusCode(400).end();
			context.response().close();
			return;
		}

		DB.executeUpdate("INSERT INTO bug_report(title, content) VALUES(?, ?)", title, content);

		context.response().setStatusCode(201).end();
		context.response().close();
	}
}
