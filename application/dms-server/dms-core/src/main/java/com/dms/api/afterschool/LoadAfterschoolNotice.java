package com.dms.api.afterschool;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool/notice", method = { HttpMethod.GET})
public class LoadAfterschoolNotice implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		ResultSet rs = DB.executeQuery("SELCET * FROM afterschool_notice");
		JSONObject response = new JSONObject();
		try {
			if(rs.next()) {
				response.put("start_date", rs.getString("start_date"));
				response.put("end_date", rs.getString("end_date"));
				response.put("content", rs.getString("content"));
				
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
