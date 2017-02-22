package com.dms.planb.action.merit;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_MERIT_APPLY_STATUS)
public class LoadMeritApplyStatus implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM merit_apply WHERE id='", id, "'");
		
		if(resultSet.next()) {
			responseObject.put("content", resultSet.getString("content"));
			if(!resultSet.getString("target").isEmpty()) {
				responseObject.put("has_target", true);
				responseObject.put("target", resultSet.getString("target"));
			} else {
				responseObject.put("has_target", false);
			}
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
