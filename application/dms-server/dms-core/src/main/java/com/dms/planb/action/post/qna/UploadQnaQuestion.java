package com.dms.planb.action.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/qna/question", method={HttpMethod.POST})
public class UploadQnaQuestion implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		String writer = context.request().getParam("writer");
		boolean privacy = Boolean.parseBoolean(context.request().getParam("privacy"));
		
		if(!Guardian.checkParameters(title, content, writer, privacy)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO qna(title, question_content, question_date, writer, privacy) VALUES('", title, "', '", content, "', now(), '", writer, "', ", privacy, ")");
			
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
		}
	}
}
