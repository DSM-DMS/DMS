package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_PASSWORD)
public class ModifyPassword implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String password = requestObject.getString("password");
		
		int status = database.executeUpdate("UPDATE account SET password='", password, "' WHERE id='", id, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
