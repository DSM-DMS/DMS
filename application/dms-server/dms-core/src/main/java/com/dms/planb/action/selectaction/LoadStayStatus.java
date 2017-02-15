package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.LOAD_STAY_STATUS)
public class LoadStayStatus implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String week = requestObject.getString("week");
		/*
		 * week format : YYYY-MM-DD
		 */
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "' AND date='", week, "'");
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("value", resultSet.getInt("value"));
				tempObject.put("day", resultSet.getString("day"));
				
				array.add(tempObject);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
