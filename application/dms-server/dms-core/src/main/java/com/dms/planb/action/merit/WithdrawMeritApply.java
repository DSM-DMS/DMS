package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.WITHDRAW_MERIT_APPLY)
public class WithdrawMeritApply implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		int status = database.executeUpdate("DELETE FROM merit_apply WHERE id='", id, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
