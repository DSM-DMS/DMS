package com.dms.planb.core;

/**
 * Created by boxfox on 2017-03-11.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import org.boxfox.dms.utilities.actions.RouteRegister;

public class TestVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(ClusteredSessionStore.create(vertx)));
        RouteRegister.registerRouters(router, "org.boxfox.dms.secure", "com.dms.planb", "com.dms.boxfox.templates");
        router.route("/test/").handler(new Handler<RoutingContext>(){

            @Override
            public void handle(RoutingContext ctx) {
                Session session = ctx.session();
                Cookie someCookie = ctx.getCookie("visits");

                long visits = 0;
                if (someCookie != null) {
                    String cookieValue = someCookie.getValue();
                    System.out.println(cookieValue);
                    try {
                        visits = Long.parseLong(cookieValue);
                    } catch (NumberFormatException e) {
                        visits = 0l;
                    }
                }

                // increment the tracking
                visits++;

                // Add a cookie - this will get written back in the response automatically
                ctx.addCookie(Cookie.cookie("visits", "" + visits));
                ctx.response().end("asvasv");
            }
        });

        // Serve the static resources
        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }
}