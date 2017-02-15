package com.dms.planb.action.deleteaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.WITHDRAW_EXTENTION_APPLY)
public class WithdrawExtensionApply implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		int status = database.executeUpdate("DELETE extension_apply WHERE id='", id, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
