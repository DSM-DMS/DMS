package com.dms.planb.action.goingout;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/goingout/download", method={HttpMethod.POST})
public class GoingoutDownloadRouter implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		
	}
}
