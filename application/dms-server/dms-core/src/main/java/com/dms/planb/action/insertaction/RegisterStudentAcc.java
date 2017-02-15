package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.REGISTER_STUDENT_ACC)
public class RegisterStudentAcc implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		// account
		String id = requestObject.getString("id");
		String password = requestObject.getString("password");
		String sessionKey = requestObject.getString("session_key");
		int permission = requestObject.getInt("permission");
		
		// student data
		String studentName = requestObject.getString("name");
		int studentNumber = requestObject.getInt("number");
		int studentSex = requestObject.getInt("sex");
		int studentStatus = requestObject.getInt("status");
		
		String studentPhone = null;
		String parentName = null;
		String parentPhone = null;
		if(requestObject.containsKey("phone")) {
			studentPhone = requestObject.getString("phone");
		}
		if(requestObject.containsKey("p_name")) {
			parentName = requestObject.getString("p_name");
		}
		if(requestObject.containsKey("p_phone")) {
			parentPhone = requestObject.getString("p_phone");
		}
		
		/**
		 * Set stay apply default value when register account
		 * 
		 * Table Name : stay_apply_default
		 * 
		 * id VARCHAR(20) PK NN
		 * value INT(1) Default 4
		 */
		
		int status = 1;
		
		status = database.executeUpdate("INSERT INTO stay_apply_default(id, value) VALUES('", id, "', 4)");
		if(status == 0) {
			responseObject.put("status", status);
			return responseObject;
		}
		
		/**
		 * Set id, password, .. to account table
		 * 
		 * Table Name : account
		 * 
		 * idx INT(11) PK NN AI
		 * id VARCHAR(20) NN UQ
		 * password VARCHAR(300) NN
		 * session_key VARCHAR(300) Default NULL
		 * permission TINYINT(1) NN
		 */
		
		status = database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ")");
		if(status == 0) {
			responseObject.put("status", status);
			return responseObject;
		}
		
		/**
		 * Set student's data to student_data table
		 * 
		 * Table Name : student_data
		 * 
		 * number INT(11) PK NN
		 * sex TINYINT(1) Default 1
		 * status INT(11) NN
		 * name VARCHAR(20) NN
		 * phone VARCHAR(20) Default NULL
		 * p_name VARCHAR(20) Default NULL
		 * p_phone VARCHAR(20) Default NULL
		 * merit INT(11) Default NULL
		 * demerit INT(11) Default NULL
		 */
		
		status = database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
		if(status == 0) {
			responseObject.put("status", status);
			return responseObject;
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
