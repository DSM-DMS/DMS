package com.dms.planb.action.post.parsed;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.parser.dataio.post.PostModel;
import com.dms.planb.support.CORSHeader;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/school/competition", method={HttpMethod.GET})
public class LoadCompetition implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = CORSHeader.putHeaders(context);
		
		int no = Integer.parseInt(context.request().getParam("no"));
		
		context.response().setStatusCode(200);
		context.response().end(PostModel.getPost(2, no).toString());
		context.response().close();
	}
}
