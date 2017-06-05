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
		
		if(!Guardian.checkParameters(id, uid, value)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		try {
			database.executeUpdate("DELETE FROM stay_apply WHERE uid='", uid, "'");
			database.executeUpdate("INSERT INTO stay_apply(uid, value) VALUES('", uid, "', ", value, ")");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
