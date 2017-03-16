package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool/item", method={HttpMethod.POST})
public class UploadAfterschoolItem implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String title = context.request().getParam("title");
		
		int target = Integer.parseInt(context.request().getParam("target"));
		String place = context.request().getParam("place");
		boolean onMonday = Boolean.parseBoolean(context.request().getParam("on_monday"));
		boolean onTuesday = Boolean.parseBoolean(context.request().getParam("on_tuesday"));
		boolean onWednesday = Boolean.parseBoolean(context.request().getParam("on_wednesday"));
		boolean onSaturday = Boolean.parseBoolean(context.request().getParam("on_saturday"));
		String instructor = context.request().getParam("instructor");
		int personnel = Integer.parseInt(context.request().getParam("personnel"));
		
		if(!Guardian.checkParameters(no, title, target, place, onMonday, onTuesday, onWednesday, onSaturday, instructor, personnel)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, on_monday, on_tuesday, on_wednesday, on_saturday, instructor, personnel) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", onMonday, ", ", onTuesday, ", ", onWednesday, ", ", onSaturday, ", '", instructor, "', ", personnel, ")");
			
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
