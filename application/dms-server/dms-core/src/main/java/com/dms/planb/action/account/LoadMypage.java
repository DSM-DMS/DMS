package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;
//import com.dms.planb.support.ProfileImage;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/account/student", method = { HttpMethod.GET })
public class LoadMypage implements Handler<RoutingContext> {
	private UserManager userManager;

	public LoadMypage() {
		userManager = new UserManager();
	}

	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		EasyJsonObject responseObject = new EasyJsonObject();

		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null) {
            	System.out.println("Get uid success");
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(!Guardian.checkParameters(id, uid)) {
        	System.out.println("Parameter null");
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }

		try {
			JobResult result = userManager.getUserInfo(userManager.getIdFromSession(context));
			if (result.isSuccess()) {
				System.out.println("Get user info success");
				//aesponseObject.put("profile_image", ProfileImage.geProfileImage(id));
				
				Map<String, Object> datas = (Map) result.getArgs()[0];
				responseObject.put("number", datas.get("number"));
				responseObject.put("name", datas.get("name"));
				responseObject.put("merit", datas.get("merit"));
				responseObject.put("demerit", datas.get("demerit"));
				responseObject.put("room", datas.get("room"));
				responseObject.put("seat", datas.get("seat"));

				context.response().setStatusCode(200);
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				System.out.println("Get user info fail");
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch (SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
