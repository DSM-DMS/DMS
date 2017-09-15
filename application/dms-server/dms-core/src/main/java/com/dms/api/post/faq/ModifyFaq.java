package com.dms.api.post.faq;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/faq", method = { HttpMethod.PATCH })
public class ModifyFaq implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		int no = Integer.parseInt(ctx.request().getParam("no"));
		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");

		if (!Guardian.checkParameters(no, title, content)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		DB.executeUpdate("UPDATE faq SET title=? WHERE no=?", title, no);
		DB.executeUpdate("UPDATE faq SET content=? WHERE no=?", content, no);

		ctx.response().setStatusCode(200).end();
		ctx.response().end();
	}
}
