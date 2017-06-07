package com.dms.planb.template_routers.notice;

import java.io.IOException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/notice/write", method={HttpMethod.GET})
public class NoticeWriteRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public NoticeWriteRouter() {
		userManager = new UserManager();
	}
	
	public void handle(RoutingContext ctx) {
		if (!Guardian.isAdmin(ctx)) return;

		boolean isLogin = userManager.isLogined(ctx);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("editor");
			try {
				templates.put("category", "notice");
				templates.put("type", "write");
				templates.put("content", "");
				
				ctx.response().setStatusCode(200);
				ctx.response().end(templates.process());
				ctx.response().close();
			} catch(IOException e) {
				Log.l("IOException");
			} catch(TemplateException e) {
				Log.l("TemplateException");
			}
		} else {
			ctx.response().setStatusCode(200);
            ctx.response().putHeader("Content-type", "text/html; utf-8");
            ctx.response().end("<script>window.location.href='/'</script>");
            ctx.response().close();
		}
	}
}