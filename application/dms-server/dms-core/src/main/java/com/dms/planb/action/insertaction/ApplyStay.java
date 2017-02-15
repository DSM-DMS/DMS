package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=132)
public class ApplyStay implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply stay - about value of date
		 * 
		 * Table Name : stay_apply
		 * 
		 * id VARCHAR(20) NN
		 * value INT(1) NN
		 * date DATE NN
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
		
		int status = database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES('", id, "', ", value, ", '", week, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
