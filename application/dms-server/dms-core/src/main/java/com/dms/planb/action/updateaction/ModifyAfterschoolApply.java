package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.MODIFY_AFTERSCHOOL_APPLY)
public class ModifyAfterschoolApply implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		// Modify apply
		int targetNo = requestObject.getInt("target_no");
		
		int status = 1;
		if(requestObject.containsKey("no")) {
			status = database.executeUpdate("UPDATE afterschool_apply SET no=", requestObject.getInt("no"), " WHERE no=", targetNo);
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
