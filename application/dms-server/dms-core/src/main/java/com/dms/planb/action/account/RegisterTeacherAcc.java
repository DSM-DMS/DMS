package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.REGISTER_TEACHER_ACC)
public class RegisterTeacherAcc implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Table Name : teacher_account
		 * 
		 * idx INT(11) PK NN AI
		 * id VARCHAR(20) NN UQ
		 * password VARCHAR(500) NN
		 * session_key VARCHAR(40) NN
		 * permission INT(11) NN Default 0
		 * name VARCHAR(20) NN
		 */
		String id = requestObject.getString("id");
		String password = requestObject.getString("password");
		String sessionKey = requestObject.getString("session_key");
		int permission = requestObject.getInt("permission");
		String teacherName = requestObject.getString("name");
		
		int status = database.executeUpdate("INSERT INTO teacher_account(id, password, session_key, permission, name) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ", '", teacherName, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
