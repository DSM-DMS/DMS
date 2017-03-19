package com.dms.planb.core;

import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import org.boxfox.dms.utilities.actions.RouteRegister;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class DmsVerticle extends AbstractVerticle {
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create().setUploadsDirectory("upload-files"));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        RouteRegister.registerRouters(router, "org.boxfox.dms.secure", "com.dms.planb", "com.dms.boxfox.templates");
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}
