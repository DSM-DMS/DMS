package com.dms.planb.action.post.notice;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/notice", method={HttpMethod.PATCH})
public class ModifyNotice implements Handler<RoutingContext> {
	public ModifyNotice() {
		
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		if (!Guardian.isAdmin(context)) {
			context.response().setStatusCode(400).end();
			context.response().close();
			return;
		}
		
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		
		if(!Guardian.checkParameters(no, title, content)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE notice SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE notice SET content='", content, "' WHERE no=", no);
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
