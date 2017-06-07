package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool/item", method={HttpMethod.POST})
public class UploadAfterschoolItem implements Handler<RoutingContext> {
	public UploadAfterschoolItem() {
		
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(ctx.request().getParam("no"));
		String title = ctx.request().getParam("title");
		
		int target = Integer.parseInt(ctx.request().getParam("target"));
		String place = ctx.request().getParam("place");
		boolean onMonday = Boolean.parseBoolean(ctx.request().getParam("on_monday"));
		boolean onTuesday = Boolean.parseBoolean(ctx.request().getParam("on_tuesday"));
		boolean onWednesday = Boolean.parseBoolean(ctx.request().getParam("on_wednesday"));
		boolean onSaturday = Boolean.parseBoolean(ctx.request().getParam("on_saturday"));
		String instructor = ctx.request().getParam("instructor");
		int personnel = Integer.parseInt(ctx.request().getParam("personnel"));
		
		if(!Guardian.checkParameters(no, title, target, place, onMonday, onTuesday, onWednesday, onSaturday, instructor, personnel)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, on_monday, on_tuesday, on_wednesday, on_saturday, instructor, personnel) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", onMonday, ", ", onTuesday, ", ", onWednesday, ", ", onSaturday, ", '", instructor, "', ", personnel, ")");
			
			ctx.response().setStatusCode(201).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
