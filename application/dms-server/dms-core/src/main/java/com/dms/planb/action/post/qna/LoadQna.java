package com.dms.planb.action.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/qna", method={HttpMethod.GET})
public class LoadQna implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		int no = Integer.parseInt(ctx.request().getParam("no"));
		
		if(!Guardian.checkParameters(no)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
			
			if(resultSet.next()) {
				responseObject.put("title", resultSet.getString("title"));
				responseObject.put("question_content", resultSet.getString("question_content"));
				responseObject.put("question_date", resultSet.getString("question_date"));
				responseObject.put("privacy", resultSet.getBoolean("privacy"));
				responseObject.put("writer", resultSet.getString("writer"));
				if(resultSet.getString("answer_content") != null) {
					responseObject.put("has_answer", true);
					responseObject.put("answer_content", resultSet.getString("answer_content"));
					responseObject.put("answer_date", resultSet.getString("answer_date"));
				} else {
					responseObject.put("has_answer", false);
				}
				
				ctx.response().setStatusCode(200);
				ctx.response().end(responseObject.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
