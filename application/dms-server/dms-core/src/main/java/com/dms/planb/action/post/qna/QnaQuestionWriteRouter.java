package com.dms.planb.action.post.qna;

import java.io.IOException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.planb.support.PrecedingWork;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/question/modify", method={HttpMethod.GET})
public class QnaQuestionWriteRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public QnaQuestionWriteRouter() {
		userManager = new UserManager();
	}
	
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		boolean isLogin = userManager.isLogined(context);
		if(isLogin) {
			DmsTemplate templates = new DmsTemplate("qnaQuestionWrite");
			
			try {
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
