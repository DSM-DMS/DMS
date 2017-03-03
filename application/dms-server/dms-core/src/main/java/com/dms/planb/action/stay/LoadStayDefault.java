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

@RouteRegistration(path="apply/stay/default", method={HttpMethod.GET})
public class LoadStayDefault implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		SafeResultSet resultSet = null;
		EasyJsonObject responseObject = new EasyJsonObject();
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM stay_apply_default WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("value", resultSet.getString("value"));
				
				context.response().setStatusCode(200).end();
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(404).end();
				context.response().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}