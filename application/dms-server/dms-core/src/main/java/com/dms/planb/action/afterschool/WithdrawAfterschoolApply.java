package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.WITHDRAW_AFTERSCHOOL_APPLY)
public class WithdrawAfterschoolApply implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		int no = requestObject.getInt("no");
		
		int status =database.executeUpdate("DELETE FROM afterschool_apply WHERE id='", id, "' AND no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
