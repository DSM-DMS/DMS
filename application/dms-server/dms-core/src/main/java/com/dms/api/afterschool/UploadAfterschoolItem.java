package com.dms.api.afterschool;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool/item", method = { HttpMethod.POST })
public class UploadAfterschoolItem implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String title = ctx.request().getParam("title");

		// int target = Integer.parseInt(ctx.request().getParam("target"));
		// String place = ctx.request().getParam("place");
		boolean onMonday = Boolean.parseBoolean(ctx.request().getParam("on_monday"));
		boolean onTuesday = Boolean.parseBoolean(ctx.request().getParam("on_tuesday"));
		boolean onSaturday = Boolean.parseBoolean(ctx.request().getParam("on_saturday"));
		// String instructor = ctx.request().getParam("instructor");
		// int personnel = Integer.parseInt(ctx.request().getParam("personnel"));

		if (!Guardian.checkParameters(title, onMonday, onTuesday, onSaturday)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		DB.executeUpdate("INSERT INTO afterschool_items(title, on_monday, on_tuesday, on_saturday) VALUES(?, ?, ?, ?)", title, onMonday, onTuesday, onSaturday);

		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
