package com.dms.api.afterschool;

import com.dms.account_manager.AdminManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool/notice", method = { HttpMethod.POST })
public class AddAfterschoolNotice implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if(!AdminManager.isAdmin(ctx)) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
			return;
		}
		
		String startDate = ctx.request().getFormAttribute("start_date");
		String endDate = ctx.request().getFormAttribute("end_date");
		String content = ctx.request().getFormAttribute("content");
		
		DB.executeUpdate("INSERT INTO afterschool_notice(start_date, end_date, content) VALUES(?, ?, ?)", startDate, endDate, content);
		
		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
