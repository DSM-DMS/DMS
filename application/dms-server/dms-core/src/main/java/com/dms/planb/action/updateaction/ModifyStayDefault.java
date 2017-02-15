package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_STAY_DEFAULT)
public class ModifyStayDefault implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		int value = requestObject.getInt("value");
		
		int status = database.executeUpdate("UPDATE stay_apply_default SET value=", value, " WHERE id='", id, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
