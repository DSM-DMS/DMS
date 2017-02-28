package com.dms.planb.action.post.report_facility;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_REPORT_FACILITY)
public class ModifyReportFacility implements Actionable {
	@Override
	@Deprecated
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		int status = 1;
		if(requestObject.containsKey("title")) {
			status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("title"), "' WHERE no=", no);
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		if(requestObject.containsKey("content")) {
			status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("content"), "' WHERE no=", no);
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
