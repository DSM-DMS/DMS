package com.dms.planb.action.post.rule;

import java.io.IOException;

import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/post/rule/write", method = {HttpMethod.GET})
public class RuleWriteRouter implements Handler<RoutingContext> {
	private AdminManager adminManager;
	
	public RuleWriteRouter() {
		adminManager = new AdminManager();
	}
	
	public void handle(RoutingContext context) {
		if (!AdminManager.isAdmin(context)) return;
		boolean isLogin = adminManager.isLogined(context);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("editor");
			try {
				templates.put("category", "rule");
				templates.put("type", "write");
				
				context.response().setStatusCode(200);
				context.response().end(templates.process());
				context.response().close();
			} catch(IOException e) {
				Log.l("IOException");
			} catch(TemplateException e) {
				Log.l("TemplateException");
			}
		} else {
			context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "text/html; utf-8");
            context.response().end("<script>window.location.href='/'</script>");
            context.response().close();
		}
	}
}