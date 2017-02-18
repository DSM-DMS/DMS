package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_QNA_LIST)
public class LoadQnaList implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int page = requestObject.getInt("page");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM qna limit ", ((page - 1) * 10), ", 10");
		
		if(resultSet.next()) {
			// There're any posts in qna
			do {
				tempObject = new EasyJsonObject();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("question_date", resultSet.getString("question_date"));
				tempObject.put("writer", resultSet.getString("writer"));
				tempObject.put("privacy", resultSet.getBoolean("privacy"));
				
				array.add(tempObject);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		// Arrays in object
		
		return responseObject;
	}
}
