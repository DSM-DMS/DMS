package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.ApplyDataUtil;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;


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

		DataBase database = DataBase.getInstance();
		
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		int value = Integer.parseInt(context.request().getParam("value"));
		String week = context.request().getParam("week");
		
		if(!Guardian.checkParameters(id, uid, value, week)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			if(ApplyDataUtil.canApplyStay(week)) {
				database.executeUpdate("DELETE FROM stay_apply WHERE uid='", uid, "' AND week='", week, "'");
				database.executeUpdate("INSERT INTO stay_apply(uid, value, week) VALUES('", uid, "', ", value, ", '", week, "')");
			
				context.response().setStatusCode(200).end();
				context.response().close();
			} else {
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
