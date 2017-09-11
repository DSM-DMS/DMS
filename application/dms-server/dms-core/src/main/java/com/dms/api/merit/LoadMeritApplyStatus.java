package com.dms.api.merit;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;


import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/merit", method={HttpMethod.GET})
public class LoadMeritApplyStatus implements Handler<RoutingContext> {
	private UserManager userManager;
	
	public LoadMeritApplyStatus() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext ctx) {

		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		String id = userManager.getIdFromSession(ctx);
        String uid = null;
        
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        if(!Guardian.checkParameters(id, uid)) {
            ctx.response().setStatusCode(400).end();
            ctx.response().close();
        	return;
        }
        
		try {
			resultSet = database.executeQuery("SELECT * FROM merit_apply WHERE uid='", uid, "'");
			
			if(resultSet.next()) {
				do {
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("content", resultSet.getString("content"));
					if(!resultSet.getString("target").isEmpty()) {
						tempObject.put("has_target", true);
						tempObject.put("target", resultSet.getString("target"));
					} else {
						tempObject.put("has_target", false);
					}
					
					tempArray.add(tempObject);
				} while(resultSet.next());
				
				ctx.response().setStatusCode(200);
				ctx.response().end(responseObject.toString());
				ctx.response().close();
			} else {
				ctx.response().setStatusCode(204).end();
				ctx.response().close();
			}
		} catch(SQLException e) {
			ctx.response().setStatusCode(500).end();
			ctx.response().close();
			
			Log.l("SQLException");
		}
	}
}
