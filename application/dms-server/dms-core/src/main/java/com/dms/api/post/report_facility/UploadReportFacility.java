package com.dms.api.post.report_facility;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;
import com.dms.utilities.support.JobResult;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/report", method={HttpMethod.POST})
public class UploadReportFacility implements Handler<RoutingContext> {
	private UserManager userManager;

	public UploadReportFacility(){
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();

		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		int room = Integer.parseInt(context.request().getParam("room"));
        String writer = null;
		if(userManager.isLogined(context)) {
			try {
                JobResult result = userManager.getUserInfo(userManager.getIdFromSession(context));
                if (result != null && result.isSuccess()) {
                    writer = ((Map<String, Object>)result.getArgs()[0]).get("name").toString();
                }
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (Guardian.checkParameters(title, content, room, writer)) {

			try {
				database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", title, "', '", content, "', ", room, ", NOW(), '", writer, "')");

				context.response().setStatusCode(201).end();
				context.response().close();
			} catch (SQLException e) {
				context.response().setStatusCode(500).end();
				context.response().close();
				Log.l("SQLException");
			}
            }
		}
		if(!context.response().closed()) {
            context.response().setStatusCode(400).end();
            context.response().close();
        }
	}
}
