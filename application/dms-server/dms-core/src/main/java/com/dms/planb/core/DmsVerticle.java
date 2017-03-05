package com.dms.planb.core;

import org.boxfox.dms.utilities.actions.RouteRegister;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

public class DmsVerticle extends AbstractVerticle {	
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(Router.router(vertx)::accept)
		.requestHandler(request -> {
			RouteRegister.executeRouter(request);
		}).listen(8080);
	}
	
	@SuppressWarnings("rawtypes")
	public void stop(Future stopFuture) throws Exception {
		
	}
}
