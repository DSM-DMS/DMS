package com.dms.templates.qna;

import java.io.IOException;
import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.templates.DmsTemplate;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/question/modify", method={HttpMethod.GET})
public class QnaQuestionModifyRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public QnaQuestionModifyRouter() {
		userManager = new UserManager();
	}
	
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
			
			DmsTemplate templates = new DmsTemplate("editor");
			
			try {
				resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
				resultSet.next();
				
				templates.put("category", "qnaQuestion");
				templates.put("type", "modify");
				templates.put("title", resultSet.getString("title"));
				templates.put("content", resultSet.getString("question_content"));
				
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
