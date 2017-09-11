package com.dms.templates.qna;

import java.io.IOException;
import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.log.Log;

import com.dms.boxfox.templates.DmsTemplate;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/question", method={HttpMethod.GET})
public class QnaPageRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public QnaPageRouter() {
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		
		boolean isLogin = userManager.isLogined(context);
		if(isLogin) {
			int no = Integer.parseInt(context.request().getParam("no"));
			
			if(!Guardian.checkParameters(no)) {
				context.response().setStatusCode(400).end();
	            context.response().close();
	        	return;
			}
			
			DmsTemplate templates = new DmsTemplate("qna");
			
			try {
				resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
				resultSet.next();
				
				templates.put("title", resultSet.getString("title"));
				templates.put("subinfo", resultSet.getString("writer"));
				templates.put("content", resultSet.getString("question_content"));
				templates.put("answer_subinfo", resultSet.getString("answer_date"));
				templates.put("answer_content", resultSet.getString("answer_content"));
				if(Guardian.isAdmin(context)) {
					templates.put("isWriter", false);
					templates.put("isAdmin", true);
				} else {
					String uid = userManager.getUid(userManager.getIdFromSession(context));
					if(resultSet.getString("owner") == uid) {
						templates.put("isWriter", true);
						templates.put("isAdmin", false);
					} else {
						templates.put("isWriter", false);
						templates.put("isAdmin", false);
					}
				}
				
				context.response().setStatusCode(200);
				context.response().end(templates.process());
				context.response().close();
			} catch(IOException e) {
				Log.l("IOException");
			} catch(TemplateException e) {
				Log.l("TemplateException");
			} catch(SQLException e) {
				Log.l("SQLException");
			}
		} else {
			context.response().setStatusCode(200);
            context.response().putHeader("Content-type", "text/html; utf-8");
            context.response().end("<script>window.location.href='/'</script>");
            context.response().close();
		}
	}
}
