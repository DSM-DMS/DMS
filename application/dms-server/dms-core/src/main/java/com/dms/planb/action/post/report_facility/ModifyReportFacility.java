package com.dms.planb.action.post.report_facility;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/report", method={HttpMethod.PATCH})
public class ModifyReportFacility implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		int room = Integer.parseInt(context.request().getParam("room"));
		String writer = context.request().getParam("writer");
		
		try {
			database.executeUpdate("UPDATE facility_report SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET content='", content, "' WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET room=", room, " WHERE no=", no);
			database.executeUpdate("UPDATE facility_report SET writer='", writer, "' WHERE no=", no);
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}	
}