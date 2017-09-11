package com.dms.api.goingout;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

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
        
        String target = ctx.request().getParam("date");
        
        if(!Guardian.checkParameters(id, uid, target)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		try {
			if(target == "sat") {
				database.executeUpdate("UPDATE goingout_apply SET sat=false WHERE uid='", uid, "'");
			} else if(target == "sun") {
				database.executeUpdate("UPDATE goingout_apply SET sun=false WHERE uid='", uid, "'");
			}
			
			ctx.response().setStatusCode(200).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
