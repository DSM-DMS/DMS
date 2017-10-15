package com.dms.api.survey;

import java.sql.SQLException;

import com.dms.account_manager.UserManager;
import com.dms.utilities.database.DB;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/survey/answer", method = { HttpMethod.POST })
public class AnswerSurvey implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		int questionNo = Integer.parseInt(ctx.request().getFormAttribute("question_no"));
		
		String uid = null;
		try {
			uid = UserManager.getUid(UserManager.getIdFromSession(ctx));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String data = ctx.request().getFormAttribute("data");
		
		DB.executeUpdate("INSERT INTO survey_answer VALUES(?, ?, ?)", questionNo, uid, data);
		
		ctx.response().setStatusCode(201).end();
		ctx.response().close();
	}
}
