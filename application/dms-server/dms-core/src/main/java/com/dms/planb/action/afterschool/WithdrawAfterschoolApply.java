package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.DELETE})
public class WithdrawAfterschoolApply implements Handler<RoutingContext> {
	UserManager userManager;
	
	public WithdrawAfterschoolApply() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		
		String id = userManager.getIdFromSession(ctx);
        String uid = null;
        
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		int no = Integer.parseInt(ctx.request().getParam("no"));
		
		if(!Guardian.checkParameters(id, uid, no)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("DELETE FROM afterschool_apply WHERE uid='", uid, "' AND no=", no);
			
			ctx.response().setStatusCode(200).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
