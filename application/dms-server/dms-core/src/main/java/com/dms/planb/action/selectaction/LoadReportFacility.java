package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_REPORT_FACILITY)
public class LoadReportFacility implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM facility_report WHERE no=", no);
		
		if(resultSet.next()) {
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("content", resultSet.getString("content"));
			responseObject.put("room", resultSet.getInt("room"));
			responseObject.put("write_day", resultSet.getString("write_day"));
			responseObject.put("writer", resultSet.getString("writer"));
			if(resultSet.getString("result") != null){
				responseObject.put("has_result", true);
				responseObject.put("result", resultSet.getString("result"));
				responseObject.put("result_day", resultSet.getString("result_day"));
			} else {
				responseObject.put("has_result", false);
			}
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
