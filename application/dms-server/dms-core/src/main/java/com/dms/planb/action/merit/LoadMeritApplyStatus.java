package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="apply/merit", method={HttpMethod.GET})
public class LoadMeritApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		String id = context.request().getParam("id");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM merit_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("content", resultSet.getString("content"));
				if(!resultSet.getString("target").isEmpty()) {
					responseObject.put("has_target", true);
					responseObject.put("target", resultSet.getString("target"));
				} else {
					responseObject.put("has_target", false);
				}
				
				context.response().setStatusCode(200).end();
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(404).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
