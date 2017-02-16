package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_REPORT_FACILITY_LIST)
public class LoadReportFacilityList implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM facility_report");
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("room", resultSet.getInt("room"));
				tempObject.put("write_day", resultSet.getString("write_day"));
				tempObject.put("writer", resultSet.getString("writer"));
				if(resultSet.getString("result") != null) {
					tempObject.put("has_result", true);
				} else {
					tempObject.put("has_result", false);
				}
				
				array.add(tempObject);
			} while(resultSet.next());
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
