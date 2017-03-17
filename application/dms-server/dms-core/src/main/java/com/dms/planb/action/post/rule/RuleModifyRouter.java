package com.dms.planb.action.post.rule;

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

@RouteRegistration(path = "/post/rule/modify", method = {HttpMethod.GET})
public class RuleModifyRouter implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public RuleModifyRouter() {
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
			
			DmsTemplate templates = new DmsTemplate("ruleModify");
			
			try {
				resultSet = database.executeQuery("SELECT * FROM rule WHERE no=", no);
				
				templates.put("title", resultSet.getString("title"));
				templates.put("content", resultSet.getString("content"));
				
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