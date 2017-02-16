package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_MERIT)
public class ApplyMerit implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply merit - about target and content
		 * 
		 * Table Name : merit_apply
		 * 
		 * no INT(11) PK NN AI
		 * id VARCHAR(20) NN
		 * target VARCHAR(45) Default NULL
		 * content VARCHAR(500) NN
		 */
		String id = requestObject.getString("id");
		String content = requestObject.getString("content");
		
		int status = 1;
		if(requestObject.containsKey("target")) {
			// Case that merit recommendation
			String recommendTarget = requestObject.getString("target");
			status = database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES('", id, "', '", recommendTarget, "', '", content, "')");
		} else {
			status = database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES('", id, "', '", content, "')");
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
