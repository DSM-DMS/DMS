package com.dms.planb.action.deleteaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=316)
public class DeleteReportFacility implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		int status = database.executeUpdate("DELETE facility_report WHERE no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
