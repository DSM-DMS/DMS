package com.dms.planb.action.extension;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.LOAD_EXTENSION_CLASS_STATUS)
public class LoadExtensionClassStatus implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int classId = requestObject.getInt("class");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM extension_apply WHERE class=", classId);
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("seat", resultSet.getInt("seat"));
				tempObject.put("name", resultSet.getString("name"));
				tempObject.put("id", resultSet.getString("id"));
				
				array.add(tempObject);
			} while(resultSet.next());
			
			responseObject.put("result", array);
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
