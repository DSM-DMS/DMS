package com.dms.templates.faq;

import java.io.IOException;
import java.sql.SQLException;

import org.boxfox.dms.util.AdminManager;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.boxfox.templates.DmsTemplate;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import freemarker.template.TemplateException;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/faq/modify", method={HttpMethod.GET})
public class FaqModifyRouter implements Handler<RoutingContext> {
	private AdminManager adminManager;
	
	public FaqModifyRouter() {
		adminManager = new AdminManager();
	}
	
	public void handle(RoutingContext ctx) {
		if (!AdminManager.isAdmin(ctx)) return;
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		
		boolean isLogin = adminManager.isLogined(ctx);
		if(isLogin) {
			int no = Integer.parseInt(ctx.request().getParam("no"));
			if(!Guardian.checkParameters(no)) {
	            ctx.response().setStatusCode(400).end();
	            ctx.response().close();
	        	return;
	        }
			
			DmsTemplate templates = new DmsTemplate("editor");
			
			try {
				resultSet = database.executeQuery("SELECT * FROM faq WHERE no=", no);
				resultSet.next();
				
				templates.put("category", "faq");
				templates.put("type", "modify");
				templates.put("title", resultSet.getString("title"));
				templates.put("content", resultSet.getString("content"));
				
				ctx.response().setStatusCode(200);
				ctx.response().end(templates.process());
				ctx.response().close();
			} catch(IOException e) {
				Log.l("IOException");
			} catch(TemplateException e) {
				Log.l("TemplateException");
			} catch(SQLException e) {
				Log.l("SQLException");
			}
		} else {
			ctx.response().setStatusCode(200);
            ctx.response().putHeader("Content-type", "text/html; utf-8");
            ctx.response().end("<script>window.location.href='/'</script>");
            ctx.response().close();
		}
	}
}