package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

@RouteRegist
public class LoadStayDefault implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM stay_apply_default WHERE id='", id, "'");
		
		if(resultSet.next()) {
			responseObject.put("value", resultSet.getString("value"));
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}

	@Override
	public void handle(RoutingContext event) {
		
	}
}
