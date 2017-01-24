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
		String id = null;
		
		// For post
		int no;
		
		String date = null;
		
		// For status
		int status = 0;
		
		switch(command) {
		case Commands.MODIFY_PASSWORD:
			id = requestObject.getString("id");
			String password = requestObject.getString("password");
			
			status = database.executeUpdate("UPDATE account SET password='", password, "' WHERE id='", id, "'");
			break;
		case Commands.MODIFY_STUDENT_DATA:
			id = requestObject.getString("id");
			
			if(requestObject.has("sex")) {
				status = database.executeUpdate("UPDATE student_data SET sex=", requestObject.getInt("sex"), " WHERE id='", id, "'");
			}
			if(requestObject.has("status")) {
				status = database.executeUpdate("UPDATE student_data SET status=", requestObject.getInt("status"), " WHERE id='", id, "'");
			}
			if(requestObject.has("name")) {
				status = database.executeUpdate("UPDATE student_data SET name='", requestObject.getString("name"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("phone")) {
				status = database.executeUpdate("UPDATE student_data SET phone='", requestObject.getString("phone"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("p_name")) {
				status = database.executeUpdate("UPDATE student_data SET p_name='", requestObject.getString("p_name"), "' WHERE id='", id, "'");
			}
			if(requestObject.has("p_phone")) {
				status = database.executeUpdate("UPDATE student_data SET p_phone='", requestObject.getString("p_phone"), "'WHERE id='", id, "'");
			}
			break;
		case Commands.MODIFY_NOTICE:
		case Commands.MODIFY_NEWSLETTER:
		case Commands.MODIFY_COMPETITION:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				status = database.executeUpdate("UPDATE notice SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				status = database.executeUpdate("UPDATE notice SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_RULE:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				status = database.executeUpdate("UPDATE rule SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				status = database.executeUpdate("UPDATE rule SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_QUESTION:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				status = database.executeUpdate("UPDATE qna SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("question_content")) {
				status = database.executeUpdate("UPDATE qna SET question_content='", requestObject.getString("question_content"), "' WHERE no=", no);
			}
			if(requestObject.has("writer")) {
				status = database.executeUpdate("UPDATE qna SET writer='", requestObject.getString("writer"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_ANSWER:
			no = requestObject.getInt("no");
			
			if(requestObject.has("content")) {
				status = database.executeUpdate("UPDATE qna SET answer_content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_QNA_COMMENT:
			no = requestObject.getInt("no");
			
			status = database.executeUpdate("UPDATE qna_comment SET content='", requestObject.getString("content"), "' WHERE no=", no);
			break;
		case Commands.MODIFY_FAQ:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				status = database.executeUpdate("UPDATE faq SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				status = database.executeUpdate("UPDATE faq SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_EXTENTION:
			id = requestObject.getString("id");
			
			if(requestObject.has("class")) {
				status = database.executeUpdate("UPDATE extension_apply SET class=", requestObject.getInt("class"), " WHERE id='", id, "'");
			}
			if(requestObject.has("seat")) {
				status = database.executeUpdate("UPDATE extension_apply SET seat=", requestObject.getInt("seat"), " WHERE id='", id, "'");
			}
			break;
		case Commands.MODIFY_STAY:
			/*
			 * Date Format : YYYY-MM-DD
			 */
			id = requestObject.getString("id");
			date = requestObject.getString("date");
			
			status = database.executeUpdate("UPDATE stay_apply SET value=", requestObject.getInt("value"), " WHERE id='", id, "' AND date='", date, "'");
			break;
		case Commands.MODIFY_GOINGOUT:
			id = requestObject.getString("id");
			date = requestObject.getString("date");
			
			if(requestObject.has("reason")) {
				status = database.executeUpdate("UPDATE stay_apply SET reason='", requestObject.getString("reason"), "' WHERE id='", id, "' AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_MERIT:
			id = requestObject.getString("id");
			date = requestObject.getString("date");
			
			if(requestObject.has("value")) {
				status = database.executeUpdate("UPDATE stay_apply SET value=", requestObject.getInt("value"), " WHERE id='", id, "' AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_AFTERSCHOOL:
			int targetNo = requestObject.getInt("target_no");
			
			if(requestObject.has("no")) {
				status = database.executeUpdate("UPDATE afterschool_apply SET no=", requestObject.getInt("no"), " WHERE no=", targetNo);
			}
		}
		
		responseObject.put("status", status);
		return responseObject;
	}
}
