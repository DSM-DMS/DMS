package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_FAQ)
public class UploadFaq implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Frequently asked questions
		 * 
		 * Table Name : faq
		 * 
		 * no INT(11) PK NN AI
		 * title VARCHAR(45) NN
		 * content VARCHAR(5000) NN
		 */
		String title = requestObject.getString("title");
		String content = requestObject.getString("content");
		
		int status = database.executeUpdate("INSERT INTO faq(title, content) VALUES('", title, "', '", content, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
