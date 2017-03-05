package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/merit", method={HttpMethod.POST})
public class ApplyMerit implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		EasyJsonObject responseObject = new EasyJsonObject();
		
		String id = context.request().getParam("id");
		String content = context.request().getParam("content");
		
		try {
			if(context.request().params().contains("target")) {
				String target = context.request().getParam("target");
				
				database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES('", id, "', '", target, "', '", content, "')");
			} else {
				database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES('", id, "', '", content, "')");
			}
			
			context.response().setStatusCode(201).end();
			context.response().end(responseObject.toString());
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
