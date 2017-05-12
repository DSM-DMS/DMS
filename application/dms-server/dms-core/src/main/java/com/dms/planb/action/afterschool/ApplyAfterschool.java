package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.Afterschool;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.POST})
public class ApplyAfterschool implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public ApplyAfterschool() {
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
		
		if(!Guardian.checkParameters(id, uid ,no)) {
        	context.response().setStatusCode(400).end();
        	context.response().close();
        	return;
        }
		
		try {
			if(Afterschool.canApply(id, no)) {
				database.executeUpdate("INSERT INTO afterschool_apply(uid, no) VALUES('", uid, "', ", no, ")");
				
				context.response().setStatusCode(201).end();
				context.response().close();
			} else {
				context.response().setStatusCode(409).end();
				context.response().close();
				// conflict
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
