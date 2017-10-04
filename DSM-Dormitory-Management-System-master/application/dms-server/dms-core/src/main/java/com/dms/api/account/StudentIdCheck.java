package com.dms.api.account;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/account/idcheck/student", method={ HttpMethod.POST })
public class StudentIdCheck implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		
		if(!Guardian.checkParameters(id)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }

		if (UserManager.checkIdExists(id)) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
		} else {
			ctx.response().setStatusCode(201).end();
			ctx.response().close();
		}
	}
}
