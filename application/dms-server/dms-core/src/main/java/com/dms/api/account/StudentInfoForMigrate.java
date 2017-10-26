package com.dms.api.account;

import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.account_manager.UserManager;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/info/student/id_based", method = { HttpMethod.GET })
public class StudentInfoForMigrate implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getParam("id");
		System.out.println(id);

		try {
			JSONObject response = UserManager.getUserInfo(id);
			if (response.length() != 0) {
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();

			Log.l("SQLException");
		}
	}
}
