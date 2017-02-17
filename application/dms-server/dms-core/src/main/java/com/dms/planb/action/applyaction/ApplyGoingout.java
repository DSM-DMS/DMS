package com.dms.planb.action.applyaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

public class ApplyGoingout implements Actionable {
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply goingout - about departure date and reason
		 * 
		 * Table Name : goingout_apply
		 * 
		 * id VARCHAR(20) PK NN
		 * dept_date DATE NN
		 * reason VARCHAR(100) NN
		 * 
		 * DATE format : YYYY-MM-DD
		 */
		String id = requestObject.getString("id");
		String deptDate = requestObject.getString("dept_date");
		String reason = requestObject.getString("reason");
		
		int status = database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES('", id, "', '", deptDate, "', '", reason, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
