package com.dms.planb.action.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/goingout", method={HttpMethod.DELETE})
public class WithdrawGoingoutApply implements Handler<RoutingContext> {
	UserManager userManager;
	
	public WithdrawGoingoutApply() {
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
        String target = context.request().getParam("date");
        
        if(!Guardian.checkParameters(id, uid, target)) {
            context.response().setStatusCode(400).end();
            context.response().close();
        	return;
        }
		
		try {
			if(target == "sat") {
				database.executeUpdate("UPDATE goingout_apply SET sat=false WHERE uid='", uid, "'");
			} else if(target == "sun") {
				database.executeUpdate("UPDATE goingout_apply SET sun=false WHERE uid='", uid, "'");
			}
			
			context.response().setStatusCode(200).end();
			context.response().close();
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
