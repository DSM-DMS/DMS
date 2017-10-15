package com.dms.api.survey;

import com.dms.account_manager.AdminManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/survey", method = { HttpMethod.POST })
public class AddSurvey implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if(!AdminManager.isAdmin(ctx)) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
			return;
		}
		
		String startDate = ctx.request().getFormAttribute("start_date");
		String endDate = ctx.request().getFormAttribute("end_date");
		String title = ctx.request().getFormAttribute("title");
		
		DB.executeUpdate("INSERT INTO survey(start_date, end_date, title) VALUES(?, ?, ?)", startDate, endDate, title);
		
		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
