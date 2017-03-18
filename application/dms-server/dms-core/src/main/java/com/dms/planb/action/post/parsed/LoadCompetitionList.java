package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.post.PostModel;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/school/competition/list", method={HttpMethod.GET})
public class LoadCompetitionList implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		int page = Integer.parseInt(context.request().getParam("page"));
		
		context.response().setStatusCode(200);
		context.response().end(PostModel.getPostsAtPage(2, page).toString());
		context.response().close();
	}
}
