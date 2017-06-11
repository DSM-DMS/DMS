package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.post.PostModel;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/school/newsletter", method={HttpMethod.GET})
public class LoadNewsletter implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		int no = Integer.parseInt(ctx.request().getParam("no"));
		
		ctx.response().setStatusCode(200);
		ctx.response().end(PostModel.getPost(1, no).toString());
		ctx.response().close();
	}
}
