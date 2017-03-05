package com.dms.planb.core;

import org.boxfox.dms.utilities.actions.RouteRegister;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class DmsVerticle extends AbstractVerticle {	
	public void start() throws Exception {
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);
		RouteRegister.registerRouters(router, "org.boxfox.dms.secure", "com.dms.planb");
		server.requestHandler(router::accept).listen(8081);
	}
	
	@SuppressWarnings("rawtypes")
	public void stop(Future stopFuture) throws Exception {
		
	}
}
