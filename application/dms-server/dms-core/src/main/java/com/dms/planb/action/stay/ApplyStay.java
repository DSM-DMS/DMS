package com.dms.planb.action.stay;

import java.sql.SQLException;

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
		/**
		 * Apply stay - about value of date
		 * 
		 * Table Name : stay_apply
		 * 
		 * id VARCHAR(20) NN
		 * value INT(1) NN
		 * week DATE NN
		 * 
		 * DATE format : YYYY-MM-DD
		 * Friday home coming : 1
		 * Saturday home coming : 2
		 * Saturday dormitory coming : 3
		 * Stay : 4
		 */
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		String id = context.request().getParam("id");
		int value = Integer.parseInt(context.request().getParam("value"));
		String week = context.request().getParam("week");
		
		try {
			if(LimitConfig.canApplyStay(week)) {
				database.executeUpdate("DELETE FROM stay_apply WHERE id='", id, "' AND week='", week, "'");
				database.executeUpdate("INSERT INTO stay_apply(id, value, week) VALUES('", id, "', ", value, ", '", week, "')");
			
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
