package com.dms.planb.action.applyaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_STAY)
public class ApplyStay implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply stay - about value of date
		 * 
		 * Table Name : stay_apply
		 * 
		 * id VARCHAR(20) NN
		 * value INT(1) NN
		 * week DATE NN
		 * 
		 * DATE format : YYYY-MM-DD
		 * Friday home coming : 1
		 * Saturday home coming : 2
		 * Saturday dormitory coming : 3
		 * Stay : 4
		 */
		String id = requestObject.getString("id");
		int value = requestObject.getInt("value");
		String week = requestObject.getString("week");
		
		database.executeUpdate("DELETE FROM stay_apply WHERE id='", id, "', AND week='", week, "'");
		int status = database.executeUpdate("INSERT INTO stay_apply(id, value, week) VALUES('", id, "', ", value, ", '", week, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
