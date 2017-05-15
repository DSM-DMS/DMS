package com.dms.planb.action.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/goingout", method={HttpMethod.GET})
public class LoadGoingoutApplyStatus implements Handler<RoutingContext> {
	UserManager userManager;
	
	public LoadGoingoutApplyStatus() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {

		EasyJsonObject responseObject = new EasyJsonObject();

		String id = userManager.getIdFromSession(context);
        String uid = null;

        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(userManager.isLogined(context)) {
			try {
				userManager.getUserInfo(userManager.getIdFromSession(context));
				boolean[] status = userManager.getOutStatus(userManager.getIdFromSession(context));
				responseObject.put("sat", !status[0]);
				responseObject.put("sun", !status[1]);
			} catch (SQLException e) {
				context.response().setStatusCode(500).end();
				context.response().close();

				Log.l("SQLException");
			}
		}else{
			context.response().setStatusCode(400).end();
			context.response().close();
			return;
		}
	}
}
