package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.post.PostModel;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="post/notice/list", method={HttpMethod.GET})
public class LoadNoticeList implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		int page = Integer.parseInt(context.request().getParam("page"));
		
		context.response().setStatusCode(200).end();
		context.response().end(PostModel.getPostsAtPage(0, page).toString());
		context.response().close();
	}
}
