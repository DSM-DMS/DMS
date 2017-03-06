package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.meal.MealModel;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/school/meal", method={HttpMethod.GET})
public class LoadMeal implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		int year = Integer.parseInt(context.request().getParam("year"));
		int month = Integer.parseInt(context.request().getParam("month"));
		int day = Integer.parseInt(context.request().getParam("day"));
		
		context.response().setStatusCode(200);
		context.response().end(MealModel.getMealAtDate(year, month, day).toString());
		context.response().close();
	}
}
