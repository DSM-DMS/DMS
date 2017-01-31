package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

public class UpdateAction implements Actionable {
	/*
	 * The data to be modified are received in JSON form at once.
	 * If there is key in request object, do action automatically.
	 * Reference : case Commands.MODIFY_STUDENT_DATA
	 */
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		EasyJsonObject responseObject = new EasyJsonObject();
		
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
			
			if(requestObject.containsKey("sex")) {
				status = database.executeUpdate("UPDATE student_data SET sex=", requestObject.getInt("sex"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("status")) {
				status = database.executeUpdate("UPDATE student_data SET status=", requestObject.getInt("status"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("name")) {
				status = database.executeUpdate("UPDATE student_data SET name='", requestObject.getString("name"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("phone")) {
				status = database.executeUpdate("UPDATE student_data SET phone='", requestObject.getString("phone"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("p_name")) {
				status = database.executeUpdate("UPDATE student_data SET p_name='", requestObject.getString("p_name"), "' WHERE id='", id, "'");
			}
			if(requestObject.containsKey("p_phone")) {
				status = database.executeUpdate("UPDATE student_data SET p_phone='", requestObject.getString("p_phone"), "' WHERE id='", id, "'");
			}
			
			break;
		case Commands.MODIFY_RULE:
			no = requestObject.getInt("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE rule SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE rule SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_QUESTION:
			no = requestObject.getInt("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE qna SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("question_content")) {
				status = database.executeUpdate("UPDATE qna SET question_content='", requestObject.getString("question_content"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("writer")) {
				status = database.executeUpdate("UPDATE qna SET writer='", requestObject.getString("writer"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_ANSWER:
			no = requestObject.getInt("no");
			
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE qna SET answer_content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_QNA_COMMENT:
			no = requestObject.getInt("no");
			
			status = database.executeUpdate("UPDATE qna_comment SET content='", requestObject.getString("content"), "' WHERE no=", no);
			
			break;
		case Commands.MODIFY_FAQ:
			no = requestObject.getInt("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE faq SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE faq SET content='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			if(requestObject.containsKey("title")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("title"), "' WHERE no=", no);
			}
			if(requestObject.containsKey("content")) {
				status = database.executeUpdate("UPDATE facility_report SET title='", requestObject.getString("content"), "' WHERE no=", no);
			}
			
			break;
		case Commands.MODIFY_EXTENTION:
			id = requestObject.getString("id");
			
			if(requestObject.containsKey("class")) {
				status = database.executeUpdate("UPDATE extension_apply SET class=", requestObject.getInt("class"), " WHERE id='", id, "'");
			}
			if(requestObject.containsKey("seat")) {
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
		case Commands.MODIFY_STAY_DEFAULT:
			id = requestObject.getString("id");
			
			status = database.executeUpdate("UPDATE stay_apply_default SET value=", requestObject.getInt("value"), " WHERE id='", id, "'");
			
			break;
		case Commands.MODIFY_GOINGOUT:
			id = requestObject.getString("id");
			date = requestObject.getString("date");
			
			if(requestObject.containsKey("reason")) {
				status = database.executeUpdate("UPDATE goingout_apply SET reason='", requestObject.getString("reason"), "' WHERE id='", id, "' AND date='", date, "'");
			}
			
			break;
		case Commands.MODIFY_MERIT:
			id = requestObject.getString("id");
			date = requestObject.getString("date");
			
			if(requestObject.containsKey("value")) {
				status = database.executeUpdate("UPDATE merit_apply SET value=", requestObject.getInt("value"), " WHERE id='", id, "' AND date='", date, "'");
			}
			
			break;
		case Commands.MODIFY_AFTERSCHOOL:
			int targetNo = requestObject.getInt("target_no");
			
			if(requestObject.containsKey("no")) {
				status = database.executeUpdate("UPDATE afterschool_apply SET no=", requestObject.getInt("no"), " WHERE no=", targetNo);
			}
			
			break;
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
