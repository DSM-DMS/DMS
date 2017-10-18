package com.dms.templates.resource;

import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by boxfox on 2017-03-15.
 */

@Route(path = "/lookup/image/:filename", method = {HttpMethod.GET})
public class ImageLookupRouter implements Handler<RoutingContext> {
    public void handle(RoutingContext context) {
        String fileName = context.request().getParam("filename");
        context.response().sendFile("upload-files/" + fileName);
    }
}
