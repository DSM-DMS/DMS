package com.dms.planb.action.post.rule;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_RULE)
public class UploadRule implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Rules of dormitory
		 * 
		 * Table Name : rule
		 * 
		 * no INT(11) PK NN AI
		 * title VARCHAR(45) NN
		 * content VARCHAR(5000) NN
		 */
		
		String title = requestObject.getString("title");
		String content = requestObject.getString("content");
		
		int status = 1;
		
		if(requestObject.containsKey("no")) {
			/*
			 * Judge modify
			 */
			int no = requestObject.getInt("no");
			
			database.executeUpdate("UPDATE rule SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE rule SET content='", content, "' WHERE no=", no);
		} else {
			/*
			 * Judge upload
			 */
			status = database.executeUpdate("INSERT INTO rule(title, content) VALUES('", title, "', '", content, "')");
		}
				
		responseObject.put("status", status);
		
		return responseObject;
	}
}
