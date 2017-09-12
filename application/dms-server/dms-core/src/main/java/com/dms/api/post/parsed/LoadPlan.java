package com.dms.api.post.parsed;

import com.dms.parser.dataio.plan.PlanModel;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/school/plan", method={HttpMethod.GET})
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
