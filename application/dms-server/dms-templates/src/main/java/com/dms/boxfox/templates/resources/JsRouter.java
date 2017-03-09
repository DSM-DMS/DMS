package com.dms.boxfox.templates.resources;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;

@RouteRegistration(path = "/js/:filename", method = {HttpMethod.GET})
public class JsRouter implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        if (context.request().path().contains("..")){
            context.response().end();
        } else {
            context.response().sendFile("WEB-INF\\js\\" + context.request().getParam("filename"));
            if (!context.response().ended())
                context.response().end("Resource Not Found");
        }
    }
}
