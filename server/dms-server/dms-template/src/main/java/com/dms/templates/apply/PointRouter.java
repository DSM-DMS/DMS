package com.dms.templates.apply;

import com.dms.account_manager.UserManager;
import com.dms.templates.DmsTemplate;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

import java.io.IOException;

@Route(path="/point", method={HttpMethod.GET})
public class PointRouter implements Handler<RoutingContext> {
private UserManager userManager;

	public PointRouter() {
		userManager = new UserManager();
	}
	
	public void handle(RoutingContext context) {

		boolean isLogin = userManager.isLogined(context);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("point_apply");
			try {
				context.response().setStatusCode(200);
				context.response().end(templates.process());
				context.response().close();
			} catch(IOException e) {
				e.printStackTrace();
				Log.l("IOException");
			} catch(TemplateException e) {
				e.printStackTrace();
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
