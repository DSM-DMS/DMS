package com.dms.boxfox.templates.resources;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

/**
 * Created by boxfox on 2017-03-15.
 */

@RouteRegistration(path = "/lookup/image/:filename", method = {HttpMethod.GET})
public class ImageLookupRouter implements Handler<RoutingContext> {
    private UserManager userManager;

    public ImageLookupRouter() {
        userManager = new UserManager();
    }

    @Override
    public void handle(RoutingContext context) {
        String fileName = context.request().getParam("filename");
        System.out.println(fileName);
        context.response().sendFile("upload-files/" + fileName);
    }
}
