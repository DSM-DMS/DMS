package com.dms.planb.action.account.modify;

import java.sql.SQLException;

import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.actions.support.PrecedingWork;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/account/password/student", method={HttpMethod.PATCH})
public class ModifyPassword implements Handler<RoutingContext> {
	UserManager userManager;
	
	public ModifyPassword() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeadersOnly(context);
		
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
        
		String encryptedPassword = SHA256.encrypt(context.request().getParam("password"));
		
		if(!Guardian.checkParameters(uid, encryptedPassword)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE account SET password='", encryptedPassword, "' WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
