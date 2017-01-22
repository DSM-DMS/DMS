package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;
import com.dms.planb.support.Commands;

public class UpdateAction implements Actionable {
	/*
	 * The data to be modified are received in JSON form at once.
	 * If there is key in request object, do action automatically.
	 * Reference : case Commands.MODIFY_STUDENT_DATA
	 */
	@Override
	public JSONObject action(int command, JSONObject requestObject) throws JSONException, SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
		// For account
		String id;
		
		// For post
		int no;
		
		switch(command) {
		case Commands.MODIFY_PASSWORD:
			id = requestObject.getString("id");
			String password = requestObject.getString("password");
			
			database.executeQuery("UPDATE account SET password='", password, "' WHERE id='", id, "'");
			
			break;
		case Commands.MODIFY_STUDENT_DATA:
			id = requestObject.getString("id");
			
			if(requestObject.has("sex")) {
				database.executeQuery("UPDATE student_data SET sex=", requestObject.getInt("sex"), " WHERE id='", id, "'");
			}
			if(requestObject.has("status")) {
				database.executeQuery("UPDATE student_data SET status=", requestObject.getInt("status"), " WHERE id='", id, "'");
			}
			if(requestObject.has("name")) {
				database.executeQuery("UPDATE student_data SET name='", requestObject.getString("name"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("phone")) {
				database.executeQuery("UPDATE student_data SET phone='", requestObject.getString("phone"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("p_name")) {
				database.executeQuery("UPDATE student_data SET p_name='", requestObject.getString("p_name"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("p_phone")) {
				database.executeQuery("UPDATE student_data SET p_phone='", requestObject.getString("p_phone"), "'WHERE id='", id, "'");
			}
			
			break;
		case Commands.MODIFY_NOTICE:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				database.executeQuery("UPDATE notice SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				database.executeQuery("UPDATE notice SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_RULE:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				database.executeQuery("UPDATE rule SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				database.executeQuery("UPDATE rule SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_QUESTION:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				database.executeQuery("UPDATE qna SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("question_content")) {
				database.executeQuery("UPDATE qna SET question_content='", requestObject.getString("question_content"), "' WHERE no=", no);
			}
			if(requestObject.has("writer")) {
				database.executeQuery("UPDATE qna SET writer='", requestObject.getString("writer"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_ANSWER:
			if(requestObject.has("content")) {
				
			}
			
			break;
		case Commands.MODIFY_QNA_COMMENT:
			break;
		case Commands.MODIFY_FAQ:
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
}
