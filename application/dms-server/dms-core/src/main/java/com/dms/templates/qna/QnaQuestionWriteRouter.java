package com.dms.templates.qna;

import java.io.IOException;

import org.boxfox.dms.util.UserManager;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/question/write", method={HttpMethod.GET})
public class QnaQuestionWriteRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public QnaQuestionWriteRouter() {
		userManager = new UserManager();
	}
	
	public void handle(RoutingContext ctx) {

		boolean isLogin = userManager.isLogined(ctx);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("editor");
			
			try {
				templates.put("category", "qnaQuestion");
				templates.put("type", "write");
				
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
