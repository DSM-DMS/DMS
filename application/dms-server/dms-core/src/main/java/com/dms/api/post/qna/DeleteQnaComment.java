package com.dms.api.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.SafeResultSet;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/qna/comment", method={HttpMethod.DELETE})
public class DeleteQnaComment implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public DeleteQnaComment() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();

		int idx = Integer.parseInt(context.request().getParam("no"));
		
		if(!Guardian.checkParameters(idx)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		String uid = null;
		try {
			uid = userManager.getUid(userManager.getIdFromSession(context));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			SafeResultSet rs = database.executeQuery("SELECT * FROM qna_comment WHERE idx=", idx);
			if(uid == null || rs.getString("owner") != uid || !Guardian.isAdmin(context)) {
				// 권한 없는 경우
				context.response().setStatusCode(400).end();
				context.response().close();
				return;
			}
			database.executeUpdate("DELETE FROM qna_comment WHERE idx=", idx);
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
