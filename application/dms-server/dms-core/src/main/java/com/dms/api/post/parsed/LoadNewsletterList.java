package com.dms.api.post.parsed;

import com.dms.parser.dataio.post.PostModel;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/school/newsletter/list", method={HttpMethod.GET})
public class LoadNewsletterList implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		int page = Integer.parseInt(ctx.request().getParam("page"));
		
		ctx.response().setStatusCode(200);
		ctx.response().end(PostModel.getPostsAtPage(1, page).toString());
		ctx.response().close();
	}
}
