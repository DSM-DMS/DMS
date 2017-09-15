package com.dms.api.post.faq;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/faq", method = { HttpMethod.DELETE })
public class DeleteFaq implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		int no = Integer.parseInt(ctx.request().getParam("no"));

		if (!Guardian.checkParameters(no)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		DB.executeUpdate("DELETE FROM faq WHERE no=?", no);

		ctx.response().setStatusCode(200).end();
		ctx.response().close();
	}
}
