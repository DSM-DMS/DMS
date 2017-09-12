package com.dms.api.post.report_facility;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Deprecated
@Route(path="/post/report", method={HttpMethod.PATCH})
public class ModifyReportFacility implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(ctx.request().getParam("no"));
		String title = ctx.request().getParam("title");
		String content = ctx.request().getParam("content");
		int room = Integer.parseInt(ctx.request().getParam("room"));
		String writer = ctx.request().getParam("writer");
		
		if(!Guardian.checkParameters(no, title, content, room, writer)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE facility_report SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET content='", content, "' WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET room=", room, " WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET writer='", writer, "' WHERE no=", no);
			
			ctx.response().setStatusCode(200).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}	
}
