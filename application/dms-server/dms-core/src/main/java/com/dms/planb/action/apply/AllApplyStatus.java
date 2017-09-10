package com.dms.planb.action.apply;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path = "/apply/all", method = {HttpMethod.GET})
public class AllApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		UserManager userManager = new UserManager();
		DataBase database = DataBase.getInstance();
		
		String uid = null;
		try {
			uid = userManager.getUid(userManager.getIdFromSession(ctx));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(!Guardian.checkParameters(uid)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
		
		EasyJsonObject responseObject = new EasyJsonObject();
		try {
			SafeResultSet resultSet = database.executeQuery("SELECT * FROM extension_apply WHERE uid='", uid, "'");
			
			if(resultSet.next()) {
				responseObject.put("extension_applied", true);
				responseObject.put("class", resultSet.getInt("class"));
				responseObject.put("name", resultSet.getString("name"));
			} else {
				responseObject.put("extension_applied", false);
			}
			
			resultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE uid='", uid, "'");
			if(resultSet.next()) {
				responseObject.put("goingout_applied", true);
				responseObject.put("sat", resultSet.getBoolean("sat"));
				responseObject.put("sun", resultSet.getBoolean("sun"));
			} else {
				responseObject.put("goingout_applied", false);
			}
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE uid='", uid, "'");
			if(resultSet.next()) {
				responseObject.put("stay_applied", true);
				responseObject.put("value", resultSet.getInt("value"));
			} else {
				responseObject.put("stay_applied", false);
			}
			
			ctx.response().setStatusCode(200);
			ctx.response().end(responseObject.toString());
			ctx.response().close();
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			e.printStackTrace();
			Log.l("SQLException");
		}
	}
}
