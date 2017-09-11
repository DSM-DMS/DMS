package com.dms.api.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;

import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.RouteRegistration;

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
		int targetNo = Integer.parseInt(ctx.request().getParam("target_no"));
		
		if(!Guardian.checkParameters(no, id, uid, targetNo)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }
		
		try {
			database.executeUpdate("UPDATE afterschool_apply SET no=", no, " WHERE no=", targetNo, " AND uid='", uid, "'");
		
			ctx.response().setStatusCode(200).end();
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
