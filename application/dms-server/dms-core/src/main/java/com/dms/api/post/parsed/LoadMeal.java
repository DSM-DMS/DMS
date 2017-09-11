package com.dms.api.post.parsed;

import com.dms.parser.dataio.meal.MealModel;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/school/meal", method={HttpMethod.GET})
public class LoadMeal implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		int year = Integer.parseInt(ctx.request().getParam("year"));
		int month = Integer.parseInt(ctx.request().getParam("month"));
		int day = Integer.parseInt(ctx.request().getParam("day"));
		
		ctx.response().setStatusCode(200);
		ctx.response().end(MealModel.getMealAtDate(year, month, day).toString());
		ctx.response().close();
	}
}
