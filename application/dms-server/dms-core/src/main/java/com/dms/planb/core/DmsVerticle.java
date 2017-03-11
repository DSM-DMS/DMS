package com.dms.planb.core;

import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import org.boxfox.dms.utilities.actions.RouteRegister;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class DmsVerticle extends AbstractVerticle {
    public void start() throws Exception {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        /**
         * @see org.boxfox.dms.utilities.actions
         * .RouteRegister
         */

        router.route().handler(CookieHandler.create());
        SessionStore store = ClusteredSessionStore.create(vertx);
        SessionHandler sessionHandler = SessionHandler.create(store);
        router.route().handler(sessionHandler);
        RouteRegister.registerRouters(router, "org.boxfox.dms.secure", "com.dms.planb", "com.dms.boxfox.templates");
        /*
		 * Using Reflection. Find @RouteRegistration annotation of classes in
		 * package, and register actions to RouteRegister class.
		 */

        server.requestHandler(router::accept).listen(8088);
    }

    @SuppressWarnings("rawtypes")
    public void stop(Future stopFuture) throws Exception {

    }

}
