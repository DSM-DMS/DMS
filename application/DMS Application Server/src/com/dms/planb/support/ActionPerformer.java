package com.dms.planb.support;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;

public class ActionPerformer {

	private static JSONObject responseObject;
	private static DataBase database = DataBase.getInstance();

	public static JSONObject doInsert(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.REGISTER_ACCOUNT:
			String id = requestObject.getString("id");
			String password = requestObject.getString("password");
			String sessionKey = requestObject.getString("session_key");
			int permission = requestObject.getInt("permission");
			
			database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", id, "', '", password, "', '", sessionKey, "', '", Integer.toString(permission), "')");
			break;
		case Commands.UPLOAD_NOTICE:
			String title = requestObject.getString("title");
			String content = requestObject.getString("content");
			String writer = requestObject.getString("writer");
			
			database.executeUpdate("INSERT INTO notice(title, content, writer) VALUES('", title, "', '", content, "', '", writer, "')");
			break;
		case Commands.UPLOAD_RULE:
			break;
		case Commands.UPLOAD_QNA:
			break;
		case Commands.UPLOAD_CONTACT:
			break;
		case Commands.UPLOAD_COMPETITION:
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			break;
		case Commands.UPLOAD_MEAL:
			break;
		case Commands.UPLOAD_PLAN:
			break;
		case Commands.APPLY_EXTENTION:
			break;
		case Commands.APPLY_STAY:
			break;
		case Commands.APPLY_GOINGOUT:
			break;
		case Commands.APPLY_MERIT:
			break;
		case Commands.APPLY_AFTERSCHOOL:
			break;
		}

		return responseObject;
	}

	public static JSONObject doUpdate(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.MODIFY_ACCOUNT:
			break;
		case Commands.MODIFY_NOTICE:
			break;
		case Commands.MODIFY_RULE:
			break;
		case Commands.MODIFY_QNA:
			break;
		case Commands.MODIFY_CONTACT:
			break;
		case Commands.MODIFY_COMPETITION:
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			break;
		case Commands.MODIFY_EXTENTION:
			break;
		case Commands.MODIFY_STAY:
			break;
		case Commands.MODIFY_GOINGOUT:
			break;
		case Commands.MODIFY_MERIT:
			break;
		case Commands.MODIFY_AFTERSCHOOL:
			
		}

		return responseObject;
	}

	public static JSONObject doDelete(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.DELETE_ACCOUNT:
			String id = requestObject.getString("id");
			
			database.executeUpdate("DELETE FROM account WHERE id=", id);
			break;
		case Commands.DELETE_NOTICE:
			break;
		case Commands.DELETE_RULE:
			break;
		case Commands.DELETE_QNA:
			break;
		case Commands.DELETE_CONTACT:
			break;
		case Commands.DELETE_COMPETITION:
			break;
		case Commands.DELETE_REPORT_FACILITY:
			break;
		case Commands.DEAPPLY_EXTENTION:
			break;
		case Commands.DEAPPLY_GOINGOUT:
			break;
		case Commands.DEAPPLY_MERIT:
			break;
		}

		return responseObject;
	}

	public static JSONObject doSelect(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.LOAD_MYPAGE:
			break;
		case Commands.LOAD_ACCOUNT:
			// when login
			String id = requestObject.getString("id");
			String password = requestObject.getString("password");
			
			ResultSet resultSet = database.executeQuery("SELECT FROM id=", id);
			if(resultSet.getString("password").equals("password")) {
				responseObject.put("status", true);
			}
			
			break;
		case Commands.LOAD_POST_LIST:
			break;
		case Commands.LOAD_NOTICE_LIST:
			break;
		case Commands.LOAD_QNA_LIST:
			break;
		case Commands.LOAD_CONTACT_LIST:
			break;
		case Commands.LOAD_COMPETITION_LIST:
			break;
		case Commands.LOAD_REPORT_FACILITY_LIST:
			break;
		case Commands.LOAD_NOTICE:
			break;
		case Commands.LOAD_RULE:
			break;
		case Commands.LOAD_QNA:
			break;
		case Commands.LOAD_CONTACT:
			break;
		case Commands.LOAD_COMPETITION:
			break;
		case Commands.LOAD_REPORT_FACILITY:
			break;
		case Commands.LOAD_EXTENTION_STATUS:
			break;
		case Commands.LOAD_STAY_STATUS:
			break;
		case Commands.LOAD_GOINGOUT_STATUS:
			break;
		case Commands.LOAD_MERIT_APPLY_STATUS:
			break;
		case Commands.LOAD_AFTERSCHOOL_STATUS:
			break;
		case Commands.LOAD_MEAL:
			break;
		case Commands.LOAD_PLAN:
			break;
		case Commands.LOAD_SCORE:
			break;
		}

		return responseObject;
	}

	// Class for test
//	public static JSONObject accept() throws JSONException {
//		responseObject = new JSONObject();
//		responseObject.put("Command", 3);
//
//		return responseObject;
//	}
}
