package com.dms.planb.action.account;

import org.boxfox.dms.user.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="account/register/student/idcheck", method={ HttpMethod.POST })
public class StudentIdCheck implements Handler<RoutingContext> {
	private UserManager userManager;

	public StudentIdCheck() {
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext context) {
		String id = context.request().getParam("id");

		if (userManager.checkIdExists(id)) {
			context.response().setStatusCode(404).end();
			context.response().close();
		} else {
			context.response().setStatusCode(201).end();
			context.response().close();
		}
	}
}
