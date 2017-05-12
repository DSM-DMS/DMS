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

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.PATCH})
public class ModifyAfterschoolApply implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public ModifyAfterschoolApply() {
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
        
        int no = Integer.parseInt(context.request().getParam("no"));
		int targetNo = Integer.parseInt(context.request().getParam("target_no"));
		
		if(!Guardian.checkParameters(no, id, uid, targetNo)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE afterschool_apply SET no=", no, " WHERE no=", targetNo, " AND uid='", uid, "'");
		
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
