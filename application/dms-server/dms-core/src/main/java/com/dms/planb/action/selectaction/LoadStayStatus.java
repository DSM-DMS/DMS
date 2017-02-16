package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_STAY_STATUS)
public class LoadStayStatus implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String week = requestObject.getString("week");
		/*
		 * week format : YYYY-MM-DD
		 */
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "' AND week='", week, "'");
		
		if(resultSet.next()) {
			responseObject.put("value", resultSet.getBoolean("value"));
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
