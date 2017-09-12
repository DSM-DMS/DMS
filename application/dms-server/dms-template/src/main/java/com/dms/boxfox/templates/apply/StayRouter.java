package com.dms.boxfox.templates.apply;

import java.io.IOException;

import org.boxfox.dms.util.UserManager;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/stay", method={HttpMethod.GET})
public class StayRouter implements Handler<RoutingContext> {
private UserManager userManager;
	
	public StayRouter() {
		userManager = new UserManager();
	}
	
	public void handle(RoutingContext context) {

		boolean isLogin = userManager.isLogined(context);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("stay_apply");
			try {
				context.response().setStatusCode(200);
				context.response().end(templates.process());
				context.response().close();
			} catch(IOException e) {
				e.printStackTrace();
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
