package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_AFTERSCHOOL_APPLY_STATUS)
public class LoadAfterschoolApplyStatus implements Handler<RoutingContext> {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		SafeResultSet resultSet = database.executeQuery("SELECT no FROM afterschool_apply WHERE id='", id, "'");
		
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
				
				array.add(tempObject);
			} while(resultSet.next());
			
			responseObject.put("result", array);
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
