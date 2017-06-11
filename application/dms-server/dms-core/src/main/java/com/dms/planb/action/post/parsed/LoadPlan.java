package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.plan.PlanModel;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/school/plan", method={HttpMethod.GET})
public class LoadPlan implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		int year = Integer.parseInt(ctx.request().getParam("year"));
		int month = Integer.parseInt(ctx.request().getParam("month"));
		
		ctx.response().setStatusCode(200);
		ctx.response().end(PlanModel.getPlan(year, month).toString());
		ctx.response().close();
	}
}
