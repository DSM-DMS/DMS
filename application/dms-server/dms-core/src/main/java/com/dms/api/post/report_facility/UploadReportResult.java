package com.dms.api.post.report_facility;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Deprecated
@Route(path="/post/report", method={HttpMethod.PUT})
public class UploadReportResult implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String content = context.request().getParam("content");
		
		if(!Guardian.checkParameters(no, content)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
			
			context.response().setStatusCode(200).end();;
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}