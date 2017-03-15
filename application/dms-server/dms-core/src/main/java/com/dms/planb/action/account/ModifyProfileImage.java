package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;

import com.dms.planb.support.PrecedingWork;
//import com.dms.planb.support.ProfileImage;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/profile-image", method={HttpMethod.PATCH})
public class ModifyProfileImage implements Handler<RoutingContext> {
	UserManager userManager;
	
	public ModifyProfileImage() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null)
                uid = userManager.getUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		String data = context.request().getParam("profile_image");
		
		//ProfileImage.setProfileImage(id, data);
		
		context.response().setStatusCode(200).end();
		context.response().close();
	}
}
