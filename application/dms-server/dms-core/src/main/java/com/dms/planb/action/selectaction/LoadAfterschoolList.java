package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_AFTERSCHOOL_LIST)
public class LoadAfterschoolList implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM afterschool_list");
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("target", resultSet.getInt("target"));
				tempObject.put("place", resultSet.getString("place"));
				tempObject.put("on_monday", resultSet.getBoolean("on_monday"));
				tempObject.put("on_tuesday", resultSet.getBoolean("on_tuesday"));
				tempObject.put("on_wednesday", resultSet.getBoolean("on_wednesday"));
				tempObject.put("on_saturday", resultSet.getBoolean("on_saturday"));
				tempObject.put("instuctor", resultSet.getString("instructor"));

				array.add(tempObject);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
