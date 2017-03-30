package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import org.boxfox.dms.utilities.actions.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/merit", method={HttpMethod.POST})
public class ApplyMerit implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public ApplyMerit() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
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
        
		String content = context.request().getParam("content");
		
		if(!Guardian.checkParameters(id, uid, content)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			if(context.request().params().contains("target")) {
				String target = context.request().getParam("target");
				
				database.executeUpdate("INSERT INTO merit_apply(uid, target, content) VALUES('", uid, "', '", target, "', '", content, "')");
			} else {
				database.executeUpdate("INSERT INTO merit_apply(uid, content) VALUES('", uid, "', '", content, "')");
			}
			
			context.response().setStatusCode(201).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
