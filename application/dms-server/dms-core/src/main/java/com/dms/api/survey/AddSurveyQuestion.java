package com.dms.api.survey;

import com.dms.account_manager.AdminManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/survey/question", method = { HttpMethod.POST })
public class AddSurveyQuestion implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if(!AdminManager.isAdmin(ctx)) {
			ctx.response().setStatusCode(204).end();
			ctx.response().close();
			return;
		}
		
		int surveyNo = Integer.parseInt(ctx.request().getFormAttribute("survey_no"));
		String title = ctx.request().getFormAttribute("title");
		boolean isObjective = Boolean.parseBoolean(ctx.request().getFormAttribute("is_objective"));
		
		if(isObjective) {
			// true면 객관식, false면 주관식
			String objects = ctx.request().getFormAttribute("objects");
			
			DB.executeUpdate("INSERT INTO survey_question(survey_no, title, is_objective, objects) VALUES(?, ?, ?, ?)", surveyNo, title, isObjective, objects);
		} else {
			DB.executeUpdate("INSERT INTO survey_question(survey_no, title, is_objective) VALUES(?, ?, ?)", surveyNo, title, isObjective);
		}
		
		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
