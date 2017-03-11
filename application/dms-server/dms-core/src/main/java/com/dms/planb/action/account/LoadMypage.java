package com.dms.planb.action.account;

import java.sql.SQLException;
import java.util.Map;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.JobResult;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;
import com.dms.planb.support.ProfileImage;

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

		String id = context.request().getParam("id");

		try {
			JobResult result = userManager.getUserInfo(id);
			if (result.isSuccess()) {
				responseObject.put("profile_image", ProfileImage.getProfileImage(id));
				
				Map<String, Object> datas = (Map) result.getArgs()[0];
				responseObject.put("number", datas.get("number"));
				responseObject.put("name", datas.get("name"));
				responseObject.put("merit", datas.get("merit"));
				responseObject.put("demerit", datas.get("demerit"));

				context.response().setStatusCode(200).end();
				System.out.println("200 responsed");
				context.response().close();
			} else {
				context.response().setStatusCode(404).end();
				System.out.println("404 responsed");
				context.response().close();
			}
		} catch (SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();

			Log.l("SQLException");
		}
	}
}
