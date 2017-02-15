package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=401)
public class LoadMypage implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int number = requestObject.getInt("number");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM student_data WHERE number=", number);
		
		if(resultSet.next()) {
			responseObject.put("sex", resultSet.getInt("sex"));
			responseObject.put("status", resultSet.getInt("status"));
			responseObject.put("name", resultSet.getString("name"));
			responseObject.put("phone", resultSet.getString("phone"));
			responseObject.put("p_name", resultSet.getString("p_name"));
			responseObject.put("merit", resultSet.getInt("merit"));
			responseObject.put("demerit", resultSet.getInt("demerit"));
		} else {
			responseObject.put("status", 404);
			/**
			 *  Can't find from DB, set status 404 and return 404 status code.
			 *  Below from here, same about this context will same meaning.
			 *  
			 *  When didn't set "status", auto-set status code 200 in Verticle.
			 */
		}
		
		return responseObject;
	}
}
