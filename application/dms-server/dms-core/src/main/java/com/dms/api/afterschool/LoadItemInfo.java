package com.dms.api.afterschool;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/afterschool/item", method = { HttpMethod.GET })
public class LoadItemInfo implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		int no = Integer.parseInt(ctx.request().getParam("no"));
		
		ResultSet rs = DB.executeQuery("SELECT * FROM afterschool_items WHERE no=?", no);
		try {
			if(rs.next()) {
				JSONObject response = new JSONObject();
				
				response.put("no", rs.getInt("no"));
				response.put("title", rs.getString("title"));
				response.put("on_monday", rs.getBoolean("on_monday"));
				response.put("on_tuesday", rs.getBoolean("on_tuesday"));
				response.put("on_saturday", rs.getBoolean("on_saturday"));
				
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
