package com.dms.planb.action.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/faq", method={HttpMethod.PATCH})
public class ModifyFaq implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		
		try {
			database.executeUpdate("UPDATE faq SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE faq SET content='", content, "' WHERE no=", no);
			
			context.response().setStatusCode(200).end();
			context.response().end();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
		}
	}
}
