package com.dms.api.post.faq;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/faq", method = { HttpMethod.POST })
public class UploadFaq implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");

		if (!Guardian.checkParameters(title, content)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		DB.executeUpdate("INSERT INTO faq(title, content) VALUES(?, ?)", title, content);

		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
