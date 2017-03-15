package com.dms.boxfox.templates.resources;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by boxfox on 2017-03-15.
 */

@RouteRegistration(path="/upload/image/", method = {HttpMethod.POST})
public class ImageUploadRouter implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {
        MultiMap attributes = context.request().formAttributes();
        System.out.println(attributes);
        for (FileUpload f : context.fileUploads()) {
            System.out.println("Filename: " + f.fileName());
            System.out.println("Size: " + f.size());
        }
        System.out.println("a222svasv");
        context.response().setStatusCode(200);
        context.response().end();
    }
}
