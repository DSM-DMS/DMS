package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=118)
public class UploadReportResult implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Table Name : Reference UPLOAD_REPORT_FACILITY
		 * Upload result based report no
		 */
		int no = requestObject.getInt("no");
		String content = requestObject.getString("content");
		
		int status = database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
