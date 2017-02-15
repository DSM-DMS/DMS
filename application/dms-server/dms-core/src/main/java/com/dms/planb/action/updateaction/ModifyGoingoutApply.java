package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_GOINGOUT_APPLY)
public class ModifyGoingoutApply implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String date = requestObject.getString("date");
		String reason = requestObject.getString("reason");
		
		int status = 1;
		if(requestObject.containsKey("reason")) {
			status = database.executeUpdate("UPDATE goingout_apply SET reason='", reason, "' WHERE id='", id, "' AND date='", date, "'");
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
