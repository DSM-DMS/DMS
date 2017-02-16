package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_FAQ)
public class LoadFaq implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		// Both list and content
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM faq");
		
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("content", resultSet.getString("content"));
				
				array.add(tempObject);
				System.out.println(array);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
