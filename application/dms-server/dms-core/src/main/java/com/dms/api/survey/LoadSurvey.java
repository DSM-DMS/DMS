package com.dms.api.survey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/survey", method = { HttpMethod.GET })
public class LoadSurvey implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		ResultSet rs = DB.executeQuery("SELECT * FROM survey");
		
		JSONArray response = new JSONArray();
		try {
			while(rs.next()) {
				JSONObject survey = new JSONObject();
				
				survey.put("start_date", rs.getString("start_date"));
				survey.put("end_date", rs.getString("end_date"));
				survey.put("title", rs.getString("title"));
				survey.put("is_objective", rs.getBoolean("is_objective"));
				if(rs.getBoolean("is_objective")) {
					survey.put("objects", rs.getString("objects"));
				}
				
				response.put(survey);
			}
			
			ctx.response().setStatusCode(200);
			ctx.response().end(response.toString());
			ctx.response().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

