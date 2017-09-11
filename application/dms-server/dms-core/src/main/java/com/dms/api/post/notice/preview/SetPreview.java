package com.dms.api.post.notice.preview;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/notice/preivew", method={HttpMethod.POST})
public class SetPreview implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(ctx.request().getFormAttribute("no"));
		
		if(!Guardian.checkParameters(no)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("DELETE FROM notice_preview");
			database.executeUpdate("INSERT INTO notice_preview VALUES(" + no + ")");
			
			ctx.response().setStatusCode(201).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
