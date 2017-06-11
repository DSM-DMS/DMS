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

	public void handle(RoutingContext ctx) {
		boolean isLogin = adminManager.isLogined(ctx);
		if (isLogin) {
			DmsTemplate templates = new DmsTemplate("new_admin_page");
			try {
				ctx.response().setStatusCode(200);
				ctx.response().end(templates.process());
				ctx.response().close();
			} catch (TemplateException e) {
				Log.l("TemplateException");
			} catch (IOException e) {
				Log.l("IOException");
			}
		} else {
			ctx.response().setStatusCode(200);
            ctx.response().putHeader("Content-type", "text/html; utf-8");
            ctx.response().end("<script>window.location.href='/'</script>");
            ctx.response().close();
		}
	}
}
