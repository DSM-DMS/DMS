package com.dms.api.goingout;

import java.sql.SQLException;

import com.dms.account_manager.UserManager;
import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/apply/goingout", method={HttpMethod.GET})
public class LoadGoingoutApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		EasyJsonObject responseObject = new EasyJsonObject();

        if(UserManager.isLogined(ctx)) {
			try {
				UserManager.getUserInfo(UserManager.getIdFromSession(ctx));
				boolean[] status = UserManager.getOutStatus(UserManager.getIdFromSession(ctx));
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
