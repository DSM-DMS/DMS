package com.dms.api.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
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
	public void handle(RoutingContext ctx) {

		EasyJsonObject responseObject = new EasyJsonObject();

        if(userManager.isLogined(ctx)) {
			try {
				userManager.getUserInfo(userManager.getIdFromSession(ctx));
				boolean[] status = userManager.getOutStatus(userManager.getIdFromSession(ctx));
				responseObject.put("sat", status[0]);
				responseObject.put("sun", status[1]);
				ctx.response().setStatusCode(200).end(responseObject.toString());
				ctx.response().close();
			} catch (SQLException e) {
				ctx.response().setStatusCode(500).end();
				ctx.response().close();

				Log.l("SQLException");
			}
		}else{
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}
	}
}
