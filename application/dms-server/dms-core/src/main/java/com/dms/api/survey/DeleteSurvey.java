package com.dms.api.survey;

import com.dms.account_manager.AdminManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/survey", method = { HttpMethod.DELETE })
public class DeleteSurvey implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if(!AdminManager.isAdmin(ctx)) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
			return;
		}
		
		int no = Integer.parseInt(ctx.request().getFormAttribute("no"));
		
		DB.executeUpdate("DELETE FROM survey WHERE no=?", no);
		
		ctx.response().setStatusCode(200).end();
		ctx.response().close();
	}
}
