package com.dms.planb.action.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/qna/question", method={HttpMethod.POST})
public class UploadQnaQuestion implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public UploadQnaQuestion() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		boolean privacy = Boolean.parseBoolean(context.request().getParam("privacy"));
		String uid = null;
		try {
			uid = userManager.getUid(userManager.getIdFromSession(context));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!Guardian.checkParameters(title, content, privacy)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO qna(title, question_content, question_date, privacy, owner) VALUES('", title, "', '", content, "', now(), ", privacy, ", '", uid, ")");
			
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
		}
	}
}
