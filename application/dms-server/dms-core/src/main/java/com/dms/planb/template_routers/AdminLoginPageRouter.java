package com.dms.planb.template_routers;

import java.io.IOException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/admin/login", method = { HttpMethod.GET })
public class AdminLoginPageRouter implements Handler<RoutingContext> {
	public void handle(RoutingContext context) {
		DmsTemplate templates = new DmsTemplate("admin_login");
		try {
			context.response().setStatusCode(200);
			context.response().end(templates.process());
			context.response().close();
		} catch (TemplateException e) {
			Log.l("TemplateException");
		} catch (IOException e) {
			Log.l("IOException");
		}
	}
}
