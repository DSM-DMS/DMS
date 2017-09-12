package com.dms.api.post.faq;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/post/faq", method={HttpMethod.PATCH})
public class ModifyFaq implements Handler<RoutingContext> {
	public ModifyFaq() {
		
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		if (!Guardian.isAdmin(ctx)) {
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}
		
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(ctx.request().getParam("no"));
		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");
		
		if(!Guardian.checkParameters(no, title, content)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE faq SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE faq SET content='", content, "' WHERE no=", no);
			
			ctx.response().setStatusCode(200).end();
			ctx.response().end();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
