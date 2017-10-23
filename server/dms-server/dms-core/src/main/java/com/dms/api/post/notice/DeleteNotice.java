package com.dms.api.post.notice;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/post/notice", method = { HttpMethod.DELETE })
public class DeleteNotice implements Handler<RoutingContext> {

	@Override
	public void handle(RoutingContext ctx) {
		int no = Integer.parseInt(ctx.request().getParam("no"));

		if (!Guardian.isAdmin(ctx) && Guardian.checkParameters(no)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		DB.executeUpdate("DELETE FROM notice WHERE no=?", no);

		ctx.response().setStatusCode(200).end();
		ctx.response().close();
	}
}
