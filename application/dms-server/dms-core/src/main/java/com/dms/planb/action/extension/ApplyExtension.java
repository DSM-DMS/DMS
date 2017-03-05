package com.dms.planb.action.extension;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.LimitConfig;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/extension", method={HttpMethod.PUT})
public class ApplyExtension implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		DataBase database = DataBase.getInstance();
		Calendar currentTime = Calendar.getInstance();
		
		int classId = Integer.parseInt(context.request().getParam("class"));
		int seatId = Integer.parseInt(context.request().getParam("seat"));
		String name = context.request().getParam("name");
		String id = context.request().getParam("id");
		
		try {
			int hour = currentTime.get(Calendar.HOUR_OF_DAY);
			int minute = currentTime.get(Calendar.MINUTE);
			
			int setHour = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[0]);
			int setMinute = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[1]);
			if(hour < setHour || (hour == setHour && minute < setMinute)) {
				context.response().setStatusCode(404).end();
				context.response().close();
			} else {
				database.executeUpdate("DELETE FROM extension_apply WHERE id='", id, "'");
				database.executeUpdate("INSERT INTO extension_apply(class, seat, name, id) VALUES(", classId, ", ", seatId, ", '", name, "', '", id, "')");
				
				context.response().setStatusCode(200).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
