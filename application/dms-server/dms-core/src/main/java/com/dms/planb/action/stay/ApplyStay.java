package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.LimitConfig;
import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/stay", method={HttpMethod.PUT})
public class ApplyStay implements Handler<RoutingContext> {
	UserManager userManager;
	public ApplyStay() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		String uid = null;
		try {
			uid = userManager.getUid(id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int value = Integer.parseInt(context.request().getParam("value"));
		String week = context.request().getParam("week");
		
		try {
			if(LimitConfig.canApplyStay(week)) {
				database.executeUpdate("DELETE FROM stay_apply WHERE uid='", uid, "' AND week='", week, "'");
				database.executeUpdate("INSERT INTO stay_apply(uid, value, week) VALUES('", uid, "', ", value, ", '", week, "')");
			
				context.response().setStatusCode(200).end();
				context.response().close();
			} else {
				context.response().setStatusCode(404).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
