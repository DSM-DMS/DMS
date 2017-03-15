package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.PATCH})
public class ModifyAfterschoolApply implements Handler<RoutingContext> {
	UserManager userManager;
	
	public ModifyAfterschoolApply() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		
		int no = Integer.parseInt(context.request().getParam("no"));
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null)
                uid = userManager.getUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		int targetNo = Integer.parseInt(context.request().getParam("target_no"));
		
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
