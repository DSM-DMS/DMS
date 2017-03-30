package com.dms.planb.template_routers;

import java.io.IOException;

import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/admin", method = { HttpMethod.GET })
public class AdminPageRouter implements Handler<RoutingContext> {
	private AdminManager adminManager;

	public AdminPageRouter() {
		adminManager = new AdminManager();
	}

	public void handle(RoutingContext context) {
		boolean isLogin = adminManager.isLogined(context);
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
