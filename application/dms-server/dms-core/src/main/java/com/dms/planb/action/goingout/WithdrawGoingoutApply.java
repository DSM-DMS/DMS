package com.dms.planb.action.goingout;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/goingout", method={HttpMethod.DELETE})
public class WithdrawGoingoutApply implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		boolean date = Boolean.parseBoolean(context.request().getParam("date"));
		
		try {
			database.executeUpdate("DELETE FROM goingout_apply WHERE id='", id, "' AND date=", date);
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
