package com.dms.boxfox.templates;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;

@RouteRegistration(path = "/images", method = {HttpMethod.GET})
public class ImagesRouter implements Handler<RoutingContext> {

    public void handle(RoutingContext context) {
        if (context.request().path().contains("..")) {
            context.response().end();
        } else {
            context.response().sendFile("WEB-INF" + context.request().path());
        }
    }
}
