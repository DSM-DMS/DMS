package com.dms.planb.action.goingout;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_GOINGOUT_APPLY_STATUS)
public class LoadGoingoutApplyStatus implements Handler<RoutingContext> {
	SafeResultSet resultSet;
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		try {
			resultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE id='", id, "'");
		} catch(NullPointerException e) {
			responseObject.put("status", 404);
			
			return responseObject;
		}
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("date", resultSet.getBoolean("date"));
				tempObject.put("reason", resultSet.getString("reason"));
				
				array.add(tempObject);
			} while(resultSet.next());
			
			responseObject.put("result", array);
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
