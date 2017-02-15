package com.dms.planb.action.deleteaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=333)
public class WithdrawMerit implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		int status = database.executeUpdate("DELETE merit_apply WHERE id='", id, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
