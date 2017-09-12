package com.dms.boxfox.templates.resources;

import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/css/:filename", method = {HttpMethod.GET})
public class CssRouter implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        if (context.request().path().contains("..")) {
            context.response().end();
        } else {
            context.response().sendFile("./WEB-INF/css/" + context.request().getParam("filename"));
            if (!context.response().ended())
                context.response().end("Resource Not Found");
        }
    }
}
