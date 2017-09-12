package com.dms.api.post.report_facility;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.json.JSONObject;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/post/report", method={HttpMethod.POST})
public class UploadReportFacility implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {

		DataBase database = DataBase.getInstance();

		String title = context.request().getParam("title");
		String content = context.request().getParam("content");
		int room = Integer.parseInt(context.request().getParam("room"));
        String writer = null;
		if(UserManager.isLogined(context)) {
			try {
                JSONObject result = UserManager.getUserInfo(UserManager.getIdFromSession(context));
                if (result.length() != 0) {
                    writer = result.getString("name");
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
