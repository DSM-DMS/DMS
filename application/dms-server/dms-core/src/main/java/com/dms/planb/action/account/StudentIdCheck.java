package com.dms.planb.action.account;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/idcheck/student", method={ HttpMethod.POST })
public class StudentIdCheck implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		String id = context.request().getParam("id");
		
		if(!Guardian.checkParameters(id)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }

		if (UserManager.checkIdExists(id)) {
			context.response().setStatusCode(404).end();
			context.response().close();
		} else {
			context.response().setStatusCode(201).end();
			context.response().close();
		}
	}
}
