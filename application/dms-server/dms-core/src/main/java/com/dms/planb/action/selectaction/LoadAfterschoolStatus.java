package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_AFTERSCHOOL_STATUS)
public class LoadAfterschoolStatus implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE id='", id, "'");
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
			
				tempObject.put("no", resultSet.getInt("no"));
				
				array.add(tempObject);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
