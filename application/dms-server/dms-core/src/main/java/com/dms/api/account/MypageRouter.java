package com.dms.api.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.json.EasyJsonObject;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;
import com.dms.utilities.support.JobResult;

//import com.dms.planb.support.ProfileImage;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/student", method = { HttpMethod.GET })
public class MypageRouter implements Handler<RoutingContext> {
	private UserManager userManager;

	public MypageRouter() {
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext ctx) {
		if(!userManager.isLogined(ctx)){
			ctx.response().setStatusCode(400).end();
			ctx.response().close();
			return;
		}

		EasyJsonObject responseObject = new EasyJsonObject();
		String id = userManager.getIdFromSession(ctx);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
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
			JobResult result = userManager.getUserInfo(userManager.getIdFromSession(ctx));
			if (result.isSuccess()) {
				Map<String, Object> datas = (Map) result.getArgs()[0];
				responseObject.put("number", datas.get("number"));
				responseObject.put("name", datas.get("name"));
				responseObject.put("merit", datas.get("merit"));
				responseObject.put("demerit", datas.get("demerit"));
				responseObject.put("room", datas.get("room"));
				responseObject.put("seat", datas.get("seat"));
				responseObject.put("stay_value", datas.get("stay_value"));

				ctx.response().setStatusCode(200);
				ctx.response().end(responseObject.toString());
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
