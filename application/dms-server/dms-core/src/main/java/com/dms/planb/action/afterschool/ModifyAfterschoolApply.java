package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="apply/afterschool", method={HttpMethod.PATCH})
public class ModifyAfterschoolApply implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String id = context.request().getParam("id");
		int targetNo = Integer.parseInt(context.request().getParam("no"));
		
		try {
			database.executeUpdate("UPDATE afterschool_apply SET no=", no, " WHERE no=", targetNo, " AND id='", id, "'");
		
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
