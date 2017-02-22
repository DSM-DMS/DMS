package com.dms.planb.action.account;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOGIN_STUDENT_REQUEST)
public class LoginStudentRequest implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String password = requestObject.getString("password");
		
		SafeResultSet resultSet = database.executeQuery("SELECT password FROM account WHERE id='", id, "'");
		
		if(!resultSet.next() || !resultSet.getString("password").equals(password)) {
			/**
			 * !resultSet.next() : Can't find any row of id
			 * !resultSet.getString("password").equals(password) : Incorrect password
			 * 
			 * Can't find id or incorrect password, set permit to false
			 */
			responseObject.put("permit", false);
		}
		else if(resultSet.getString("password").equals(password)) {
			/*
			 * Correct password
			 */
			responseObject.put("permit", true);
			
			SafeResultSet tempResultSet = database.executeQuery("SELECT number, name, merit, demerit FROM student_data WHERE id='", id, "'");
			
			responseObject.put("number", tempResultSet.getInt("number"));
			responseObject.put("name", tempResultSet.getString("name"));
			responseObject.put("merit", tempResultSet.getInt("merit"));
			responseObject.put("demerit", tempResultSet.getInt("demerit"));
		}
		
		return responseObject;
	}
}
