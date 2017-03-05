package com.dms.planb.action.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/faq", method={HttpMethod.DELETE})
public class DeleteFaq implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		EasyJsonObject responseObject = new EasyJsonObject();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		
		try {
			database.executeUpdate("DELETE FROM faq WHERE no=", no);
			
			context.response().setStatusCode(200).end();
			context.response().end(responseObject.toString());
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
