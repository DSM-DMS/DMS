package com.dms.planb.action.stay;

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

@RouteRegistration(path="/apply/stay/default", method={HttpMethod.PATCH})
public class ModifyStayDefault implements Handler<RoutingContext> {
	private UserManager userManager;

	public ModifyStayDefault(){
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
		int value = Integer.parseInt(context.request().getParam("value"));
		
		if(!Guardian.checkParameters(id, uid, value)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE stay_apply_default SET value=", value, " WHERE uid='", uid, "'");
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
