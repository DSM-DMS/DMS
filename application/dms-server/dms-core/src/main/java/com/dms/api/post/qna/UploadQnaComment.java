package com.dms.api.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/qna/comment", method={HttpMethod.POST})
public class UploadQnaComment implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public UploadQnaComment() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		
		int targetQna = Integer.parseInt(context.request().getParam("no"));
		String content = context.request().getParam("content");
		
		if(!Guardian.checkParameters(targetQna, content)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		String uid = null;
		try {
			uid = userManager.getUid(userManager.getIdFromSession(context));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			database.executeUpdate("INSERT INTO qna_comment(no, comment_date, content, owner) VALUES(", targetQna, ", now(), '", content, "', '", uid, "')");
			
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
