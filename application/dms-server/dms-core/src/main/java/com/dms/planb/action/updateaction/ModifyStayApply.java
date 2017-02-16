package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_STAY_APPLY)
public class ModifyStayApply implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/*
		 * Date Format : YYYY-MM-DD
		 */
		String id = requestObject.getString("id");
		String week = requestObject.getString("week");
		int value = requestObject.getInt("value");
		
		int status = database.executeUpdate("UPDATE stay_apply SET value=", value, " WHERE id='", id, "' AND date='", week, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
