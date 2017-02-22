package com.dms.planb.action.post.faq;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_FAQ)
public class LoadFaqWithList implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int page = requestObject.getInt("page");
		int limit = requestObject.getInt("limit");
		
		// Both list and content
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM faq limit ", ((page - 1) * 10), ", ", limit);
		
		int postCount = 0;
		if(resultSet.next()) {
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("content", resultSet.getString("content"));
				
				array.add(tempObject);
				
				postCount++;
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("num_of_post", postCount);
		responseObject.put("result", array);
		
		return responseObject;
	}
}
