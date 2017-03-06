package com.dms.planb.action.account;

import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.planb.support.ProfileImage;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/profile-image", method={HttpMethod.PATCH})
public class ModifyProfileImage implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		String id = context.request().getParam("id");
		String data = context.request().getParam("profile_image");
		
		ProfileImage.setProfileImage(id, data);
		
		context.response().setStatusCode(200).end();
		context.response().close();
	}
}
