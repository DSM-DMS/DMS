package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/stay/default", method={HttpMethod.PATCH})
public class ModifyStayDefault implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		String uid = context.request().getParam("uid");
		int value = Integer.parseInt(context.request().getParam("value"));
		
		try {
			database.executeUpdate("UPDATE stay_apply_default SET value=", value, " WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
