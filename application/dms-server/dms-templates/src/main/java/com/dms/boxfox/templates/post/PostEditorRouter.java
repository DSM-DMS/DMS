package com.dms.boxfox.templates.post;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/editor", method = {HttpMethod.GET})
public class PostEditorRouter implements Handler<RoutingContext> {
	public void handle(RoutingContext arg0) {
		
	}
}
