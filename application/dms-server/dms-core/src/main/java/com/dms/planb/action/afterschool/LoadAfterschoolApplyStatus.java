package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.RouteRegistration;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.boxfox.dms.utilities.log.Log;

import com.dms.planb.support.CORSHeader;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RouteRegistration(path="/apply/afterschool", method={HttpMethod.GET})
public class LoadAfterschoolApplyStatus implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext context) {
		context = CORSHeader.putHeaders(context);
		
		DataBase database = DataBase.getInstance();
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray tempArray = new EasyJsonArray();
		
		int no;
		String id = context.request().getParam("id");
		
		try {
			resultSet = database.executeQuery("SELECT no FROM afterschool_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					no = resultSet.getInt("no");
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
				context.response().setStatusCode(404).end();
				context.response().close();
			}
		} catch(SQLException e) {
			context.response().setStatusCode(500).end();
			context.response().close();
			
			Log.l("SQLException");
		}
	}
}
