package com.dms.planb.action.afterschool;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Afterschool;
import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_AFTERSCHOOL)
public class ApplyAfterschool implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply after school - target number 
		 * 
		 * Table Name : afterschool_apply
		 * 
		 * id VARCHAR(20) NN
		 * no INT(11) NN
		 * CONSTRAINT afterschool_apply_ibfk_1 FOREIGN KEY no REFERENCES afterschool_list(no)
		 */
		String id = requestObject.getString("id");
		int no = requestObject.getInt("no");
		
		if(Afterschool.canApply(id, no)) {
			database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", id, "', ", no, ")");
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
