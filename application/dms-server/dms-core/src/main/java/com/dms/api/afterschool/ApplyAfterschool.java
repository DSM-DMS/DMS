package com.dms.api.afterschool;

import java.sql.SQLException;

import com.dms.account_manager.Guardian;
import com.dms.account_manager.UserManager;
import com.dms.support.Afterschool;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.log.Log;
import com.dms.utilities.routing.Route;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@Route(path="/apply/afterschool", method={HttpMethod.POST})
public class ApplyAfterschool implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public ApplyAfterschool() {
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
		
		if(!Guardian.checkParameters(id, uid ,no)) {
        	ctx.response().setStatusCode(400).end();
        	ctx.response().close();
        	return;
        }
		
		try {
			if(Afterschool.canApply(id, no)) {
				database.executeUpdate("INSERT INTO afterschool_apply(uid, no) VALUES('", uid, "', ", no, ")");
				
				ctx.response().setStatusCode(201).end();
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(409).end();
				ctx.response().close();
				// conflict
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
