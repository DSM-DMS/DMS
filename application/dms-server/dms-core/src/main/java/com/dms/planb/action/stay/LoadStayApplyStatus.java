package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="apply/stay", method={HttpMethod.GET})
public class LoadStayApplyStatus implements Handler<RoutingContext> {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String week = requestObject.getString("week");
		/*
		 * week format : YYYY-MM-DD
		 */
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "' AND week='", week, "'");
		
		if(resultSet.next()) {
			responseObject.put("value", resultSet.getInt("value"));
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}

	@Override
	public void handle(RoutingContext context) {
		SafeResultSet resultSet = null;
		EasyJsonObject responseObject = new EasyJsonObject();
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		String week = context.request().getParam("week");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "' AND week='", week, "'");
			
			if(resultSet.next()) {
				responseObject.put("value", resultSet.getInt("value"));
				
				context.response().setStatusCode(200).end();
			} else {
				responseObject.put("status", 404);
			}
		} catch(SQLException e) {
			
		}
	}
}
