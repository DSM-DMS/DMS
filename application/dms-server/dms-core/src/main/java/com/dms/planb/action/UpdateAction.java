package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.json.simple.JSONObject;

import com.dms.planb.support.Commands;

public class UpdateAction implements Actionable {
	/*
	 * The data to be modified are received in JSON form at once.
	 * If there is key in request object, do action automatically.
	 * Reference : case Commands.MODIFY_STUDENT_DATA
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject action(int command, JSONObject requestObject) throws SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		
		// For post
		int no;
		
		String date = null;
		
		// For status
		int status = 0;
		
		switch(command) {
		case Commands.MODIFY_PASSWORD:
			id = (String)requestObject.get("id");
			String password = (String)requestObject.get("password");
			
			status = database.executeUpdate("UPDATE account SET password='", password, "' WHERE id='", id, "'");
			break;
		case Commands.MODIFY_STUDENT_DATA:
			id = (String)requestObject.get("id");
			
			if(requestObject.containsKey("sex")) {
				status = database.executeUpdate("UPDATE student_data SET sex=", (int)requestObject.get("sex"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("status")) {
				status = database.executeUpdate("UPDATE student_data SET status=", (int)requestObject.get("status"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("name")) {
				status = database.executeUpdate("UPDATE student_data SET name='", (String)requestObject.get("name"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("phone")) {
				status = database.executeUpdate("UPDATE student_data SET phone='", (String)requestObject.get("phone"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("p_name")) {
				status = database.executeUpdate("UPDATE student_data SET p_name='", (String)requestObject.get("p_name"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("p_phone")) {
				status = database.executeUpdate("UPDATE student_data SET p_phone='", (String)requestObject.get("p_phone"), "'WHERE id='", id, "'");
			}
			break;
		case Commands.MODIFY_NOTICE:
		case Commands.MODIFY_NEWSLETTER:
		case Commands.MODIFY_COMPETITION:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE notice SET title='", (String)requestObject.get("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE notice SET content='", (String)requestObject.get("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_RULE:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE rule SET title='", (String)requestObject.get("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE rule SET content='", (String)requestObject.get("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_QUESTION:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE qna SET title='", (String)requestObject.get("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("question_content")) {
				status = database.executeUpdate("UPDATE qna SET question_content='", (String)requestObject.get("question_content"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("writer")) {
				status = database.executeUpdate("UPDATE qna SET writer='", (String)requestObject.get("writer"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_ANSWER:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE qna SET answer_content='", (String)requestObject.get("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_QNA_COMMENT:
			no = (int)requestObject.get("no");
			
			status = database.executeUpdate("UPDATE qna_comment SET content='", (String)requestObject.get("content"), "' WHERE no=", no);
			break;
		case Commands.MODIFY_FAQ:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE faq SET title='", (String)requestObject.get("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE faq SET content='", (String)requestObject.get("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			no = (int)requestObject.get("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", (String)requestObject.get("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", (String)requestObject.get("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_EXTENTION:
			id = (String)requestObject.get("id");
			
			if(requestObject.containsKey("class")) {
				status = database.executeUpdate("UPDATE extension_apply SET class=", (int)requestObject.get("class"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("seat")) {
				status = database.executeUpdate("UPDATE extension_apply SET seat=", (int)requestObject.get("seat"), " WHERE id='", id, "'");
			}
			break;
		case Commands.MODIFY_STAY:
			/*
			 * Date Format : YYYY-MM-DD
			 */
			id = (String)requestObject.get("id");
			date = (String)requestObject.get("date");
			
			status = database.executeUpdate("UPDATE stay_apply SET value=", (int)requestObject.get("value"), " WHERE id='", id, "' AND date='", date, "'");
			break;
		case Commands.MODIFY_GOINGOUT:
			id = (String)requestObject.get("id");
			date = (String)requestObject.get("date");
			
			if(requestObject.containsKey("reason")) {
				status = database.executeUpdate("UPDATE stay_apply SET reason='", (String)requestObject.get("reason"), "' WHERE id='", id, "' AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_MERIT:
			id = (String)requestObject.get("id");
			date = (String)requestObject.get("date");
			
			if(requestObject.containsKey("value")) {
				status = database.executeUpdate("UPDATE stay_apply SET value=", (int)requestObject.get("value"), " WHERE id='", id, "' AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_AFTERSCHOOL:
			int targetNo = (int)requestObject.get("target_no");
			
			if(requestObject.containsKey("no")) {
				status = database.executeUpdate("UPDATE afterschool_apply SET no=", (int)requestObject.get("no"), " WHERE no=", targetNo);
			}
		}
		
		responseObject.put("status", status);
		return responseObject;
	}
}
