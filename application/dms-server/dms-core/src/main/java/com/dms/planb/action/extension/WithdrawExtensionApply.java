package com.dms.planb.action.extension;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.WITHDRAW_EXTENSION_APPLY)
public class WithdrawExtensionApply implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String name = requestObject.getString("name");
		
		int status = database.executeUpdate("DELETE extension_apply WHERE name='", name, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
