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
		int modifierId;
		int no;
		
		String date;
		
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
		case Commands.MODIFY_ANNOUNCEMENT:
		case Commands.MODIFY_COMPETITION:
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
			no = requestObject.getInt("no");
			
			if(requestObject.has("content")) {
				database.executeQuery("UPDATE qna SET answer_content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_QNA_COMMENT:
			no = requestObject.getInt("no");
			
			database.executeQuery("UPDATE qna_comment SET content='", requestObject.getString("content"), "' WHERE no=", no);
			break;
		case Commands.MODIFY_FAQ:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				database.executeQuery("UPDATE faq SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				database.executeQuery("UPDATE faq SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			if(requestObject.has("title")) {
				database.executeQuery("UPDATE facility_report SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.has("content")) {
				database.executeQuery("UPDATE facility_report SET title='", requestObject.getString("content"), "' WHERE no=", no);
			}
			break;
		case Commands.MODIFY_EXTENTION:
			modifierId = requestObject.getInt("id");
			
			if(requestObject.has("class")) {
				database.executeQuery("UPDATE extension_apply SET class=", requestObject.getInt("class"), " WHERE id=", modifierId);
			}
			if(requestObject.has("seat")) {
				database.executeQuery("UPDATE extension_apply SET seat=", requestObject.getInt("seat"), " WHERE id=", modifierId);
			}
			break;
		case Commands.MODIFY_STAY:
			/*
			 * Date Format : YYYY-MM-DD
			 */
			modifierId = requestObject.getInt("id");
			date = requestObject.getString("date");
			
			database.executeQuery("UPDATE stay_apply SET value=", requestObject.getInt("value"), " WHERE id=", modifierId, " AND date='", date, "'");
			break;
		case Commands.MODIFY_GOINGOUT:
			modifierId = requestObject.getInt("id");
			date = requestObject.getString("date");
			
			if(requestObject.has("reason")) {
				database.executeQuery("UPDATE stay_apply SET reason='", requestObject.getString("reason"), "' WHERE id=", modifierId, " AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_MERIT:
			modifierId = requestObject.getInt("id");
			date = requestObject.getString("date");
			
			if(requestObject.has("value")) {
				database.executeQuery("UPDATE stay_apply SET value=", requestObject.getInt("value"), " WHERE id=", modifierId, " AND date='", date, "'");
			}
			break;
		case Commands.MODIFY_AFTERSCHOOL:
			modifierId = requestObject.getInt("id");
			int targetNo = requestObject.getInt("target_no");
			
			if(requestObject.has("no")) {
				database.executeQuery("UPDATE afterschool_apply SET no=", requestObject.getInt("no"), " WHERE no=", targetNo);
			}
		}
		
		return responseObject;
	}
}
