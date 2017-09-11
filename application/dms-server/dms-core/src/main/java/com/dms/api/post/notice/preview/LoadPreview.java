package com.dms.api.post.notice.preview;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/notice/preivew", method={HttpMethod.GET})
public class LoadPreview implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		SafeResultSet noticeSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		
		try {
			resultSet = database.executeQuery("SELECT * FROM notice_preview");
			
			if(resultSet.next()) {
				int no = resultSet.getInt("no");
				
				noticeSet = database.executeQuery("SELECT * FROM notice WHERE no=", no);
				
				if(noticeSet.next()) {
					responseObject.put("title", resultSet.getString("title"));
					responseObject.put("content", resultSet.getString("content"));
					
					ctx.response().setStatusCode(200);
					ctx.response().end(responseObject.toString());
					ctx.response().close();
				} else {
					ctx.response().setStatusCode(204).end();
					ctx.response().close();
				}
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
