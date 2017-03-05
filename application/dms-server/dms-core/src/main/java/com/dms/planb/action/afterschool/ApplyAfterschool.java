package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.Afterschool;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.POST})
public class ApplyAfterschool implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		int no = Integer.parseInt(context.request().getParam("no"));
		
		try {
			if(Afterschool.canApply(id, no)) {
				database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", id, "', ", no, ")");
				
				context.response().setStatusCode(201).end();
				context.response().close();
			} else {
				context.response().setStatusCode(409).end();
				context.response().close();
				// conflict
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
