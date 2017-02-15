package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.MODIFY_MERIT_APPLY)
public class ModifyMeritApply implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String date = requestObject.getString("date");
		
		int status = 1;
		if(requestObject.containsKey("content")) {
			status = database.executeUpdate("UPDATE merit_apply SET value=", requestObject.getString("content"), " WHERE id='", id, "' AND date='", date, "'");
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
