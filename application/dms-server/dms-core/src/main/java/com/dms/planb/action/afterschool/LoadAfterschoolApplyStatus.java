package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.util.Guardian;
import org.boxfox.dms.util.UserManager;
import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.PrecedingWork;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.GET})
public class LoadAfterschoolApplyStatus implements Handler<RoutingContext> {
	UserManager userManager;
	
	public LoadAfterschoolApplyStatus() {
		userManager = new UserManager();
	}
	
	@Override
	public void handle(RoutingContext context) {
		context = PrecedingWork.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		String id = userManager.getIdFromSession(context);
        String uid = null;
        try {
            if (id != null) {
                uid = userManager.getUid(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(!Guardian.checkParameters(id, uid)) {
        	context.response().setStatusCode(404).end();
        	context.response().close();
        	return;
        }
		
		try {
			resultSet = database.executeQuery("SELECT no FROM afterschool_apply WHERE uid='", uid, "'");
			
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					int no = resultSet.getInt("no");
					SafeResultSet tempResultSet = database.executeQuery("SELECT on_monday, on_tuesday, on_wednesday, on_saturday FROM afterschool_list WHERE no=", no);
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("on_monday", tempResultSet.getBoolean("on_monday"));
					tempObject.put("on_tuesday", tempResultSet.getBoolean("on_tuesday"));
					tempObject.put("on_wednesday", tempResultSet.getBoolean("on_wednesday"));
					tempObject.put("on_saturday", tempResultSet.getBoolean("on_saturday"));
					
					tempArray.add(tempObject);
				} while(resultSet.next());
				
				responseObject.put("result", tempArray);
				
				context.response().setStatusCode(200);
				context.response().end(responseObject.toString());
				context.response().close();
			} else {
				context.response().setStatusCode(204).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
