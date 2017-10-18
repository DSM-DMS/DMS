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

@Route(path = "/survey/question", method = { HttpMethod.GET })
public class LoadSurveyQuestions implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		int surveyNo = Integer.parseInt(ctx.request().getParam("survey_no"));
		
		ResultSet rs = DB.executeQuery("SELECT * FROM survey_question WHERE survey_no=?", surveyNo);
		
		JSONArray response = new JSONArray();
		try {
			while(rs.next()) {
				JSONObject question = new JSONObject();
				
				question.put("question_no", rs.getInt("question_no"));
				question.put("title", rs.getString("title"));
				question.put("is_objective", rs.getBoolean("is_objective"));
				if(question.getBoolean("is_objective")) {
					question.put("objects", rs.getString("objects"));
				}
				
				response.put(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(response.length() == 0) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
		} else {
			ctx.response().setStatusCode(200);
			ctx.response().end(response.toString());
			ctx.response().close();
		}
	}
}
