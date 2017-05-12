package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/merit", method={HttpMethod.DELETE})
public class WithdrawMeritApply implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public WithdrawMeritApply() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {

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
        
        if(!Guardian.checkParameters(id, uid)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("DELETE FROM merit_apply WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
