package com.dms.api.meal;

import java.sql.SQLException;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/meal", method = { HttpMethod.GET })
public class GetMeal implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		EasyJsonObject responseObject = new EasyJsonObject();
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		
		String date = ctx.request().getParam("date");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM meal WHERE date='", date, "'");
			resultSet.next();
			
			responseObject.put("breakfast", resultSet.getString("breakfast"));
			responseObject.put("lunch", resultSet.getString("lunch"));
			responseObject.put("dinner", resultSet.getString("dinner"));
			
			ctx.response().setStatusCode(200);
			ctx.response().end(responseObject.toString());
			ctx.response().close();
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
		}
	}
}
