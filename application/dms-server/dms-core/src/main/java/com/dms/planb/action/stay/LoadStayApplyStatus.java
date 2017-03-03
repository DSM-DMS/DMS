package com.dms.planb.action.stay;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_STAY_APPLY_STATUS)
public class LoadStayApplyStatus implements Actionable {
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
			responseObject.put("value", resultSet.getInt("value"));
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
