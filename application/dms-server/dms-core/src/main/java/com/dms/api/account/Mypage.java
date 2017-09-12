package com.dms.api.account;

import java.sql.SQLException;

import org.json.JSONObject;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path = "/account/student", method = { HttpMethod.GET })
public class Mypage implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		if(!UserManager.isLogined(ctx)){
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		String id = UserManager.getIdFromSession(ctx);
        String uid = null;
        try {
            if (id != null) {
                uid = UserManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(!Guardian.checkParameters(id, uid)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }

		try {
			JSONObject response = UserManager.getUserInfo(UserManager.getIdFromSession(ctx));
			if (response.length() != 0) {
				ctx.response().setStatusCode(200);
				ctx.response().end(response.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch (SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();

			Log.l("SQLException");
		}
	}
}
