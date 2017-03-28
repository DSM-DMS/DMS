package com.dms.planb.support;

import java.io.IOException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/admin", method = { HttpMethod.GET })
public class AdminPageRouter implements Handler<RoutingContext> {
	private UserManager userManager;

	public AdminPageRouter() {
		userManager = new UserManager();
	}

	public void handle(RoutingContext context) {
		boolean isLogin = userManager.isLogined(context);
		if (isLogin) {
			DmsTemplate templates = new DmsTemplate("admin_page");
			try {
				context.response().setStatusCode(200);
				context.response().end(templates.process());
				context.response().close();
			} catch (TemplateException e) {
				Log.l("TemplateException");
			} catch (IOException e) {
				Log.l("IOException");
			}
		} else {
			context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "text/html; utf-8");
            context.response().end("<script>window.location.href='/'</script>");
            context.response().close();
		}
	}
}
