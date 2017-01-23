package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;
import com.dms.planb.support.Commands;

public class InsertAction implements Actionable {
	@Override
	public JSONObject action(int command, JSONObject requestObject) throws JSONException, SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
		// For register account
		String id;
		String password;
		String sessionKey;
		int permission;
		
		// For upload a post
		int no;
		int number;
		int category;
		
		String title;
		String content;
		String writer;
		String date;
		
		// For apply
		int applierId;
		
		switch(command) {
		case Commands.REGISTER_STUDENT_ACC:
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			sessionKey = requestObject.getString("session_key");
			permission = requestObject.getInt("permission");
			
			database.executeUpdate("INSERT INTO student(id, password, session_key, permission) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ")");
			
			int studentNumber = requestObject.getInt("number");
			int studentSex = requestObject.getInt("sex");
			int studentStatus = requestObject.getInt("status");
			String studentName = requestObject.getString("name");
			String studentPhone = requestObject.getString("phone");
			String parentName = requestObject.getString("p_name");
			String parentPhone = requestObject.getString("p_phone");
			
			database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
			break;
		case Commands.REGISTER_TEACHER_ACC:
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			sessionKey = requestObject.getString("session_key");
			permission = requestObject.getInt("permission");
			String teacherName = requestObject.getString("name");
			
			database.executeUpdate("INSERT INTO teacher_account(id, password, session_key, permission, name) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ", '", teacherName, "')");
			break;
		case Commands.UPLOAD_NOTICE:
		case Commands.UPLOAD_ANNOUNCEMENT:
		case Commands.UPLOAD_COMPETITION:
			number = requestObject.getInt("number");
			category = requestObject.getInt("category");
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			writer = requestObject.getString("writer");
			date = requestObject.getString("date");
			
			database.executeUpdate("INSERT INTO app_content(number, category, title, content, writer, date) VALUES(", number, ", ", category, ", '", title, "', '", content, "', '", writer, "', STR_TO_DATE('", date, "', %Y-%m-%d-%r))");
			break;
		case Commands.UPLOAD_RULE:
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO rule(title, content) VALUES('", title, "', '", content, "')");
			break;
		case Commands.UPLOAD_QUESTION:
			title = requestObject.getString("title");
			content = requestObject.getString("question_content");
			date = requestObject.getString("question_date");
			writer = requestObject.getString("questioner");
			
			int privacy = requestObject.getInt("privacy");
			
			database.executeUpdate("INSERT INTO qna(title, question_content, question_date, questioner, privacy) VALUES('", title, "', '", content, "', '", date, "', '", writer, "', ", privacy, ")");
			break;
		case Commands.UPLOAD_ANSWER:
			// Upload answer based question no
			no = requestObject.getInt("no");
			content = requestObject.getString("answer_content");
			date = requestObject.getString("answer_date");
			
			database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date='", date, "' WHERE no=", no);
			break;
		case Commands.UPLOAD_QNA_COMMENT:
			// Upload comment based QNA no
			no = requestObject.getInt("no");
			writer = requestObject.getString("writer");
			content = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(", no, ", '", writer, "', now(), '", content, "')");
			break;
		case Commands.UPLOAD_FAQ:
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO faq(title, content) VALUES('", title, "', '", content, "')");
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			no = requestObject.getInt("room");
			writer = requestObject.getString("writer");
			
			database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", title, "', '", content, "', ", no, ", NOW(), '", writer, "')");
			break;
		case Commands.UPLOAD_REPORT_RESULT:
			no = requestObject.getInt("no");
			content = requestObject.getString("result");
			
			database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
			break;
		case Commands.UPLOAD_MEAL:
			// does Meal needs command?
			
			break;
		case Commands.UPLOAD_PLAN:
			// does Plan needs command?
			
			break;
		case Commands.UPLOAD_AFTERSCHOOL:
			no = requestObject.getInt("no");
			title = requestObject.getString("title");
			
			int target = requestObject.getInt("target");
			String place = requestObject.getString("place");
			int day = requestObject.getInt("day");
			String instructor = requestObject.getString("instructor");
			
			database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, day, instructor) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", day, ", '", instructor, "')");
			break;
		case Commands.APPLY_EXTENTION:
			applierId = requestObject.getInt("id");
			int classId = requestObject.getInt("class");
			int seatId = requestObject.getInt("seat");
			
			database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES(", applierId, ", ", classId, ", ", seatId, ")");
			break;
		case Commands.APPLY_STAY:
			/*
			 * Date Format : in ActionPerformer
			 */
			applierId = requestObject.getInt("id");
			int extensionValue = requestObject.getInt("value");
			date = requestObject.getString("date");
			
			database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES(", applierId, ", ", extensionValue, ", '", date, "')");
			break;
		case Commands.APPLY_GOINGOUT:
			applierId = requestObject.getInt("id");
			String deptDate = requestObject.getString("dept_date");
			String reason = requestObject.getString("reason");
			
			database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES(", applierId, ", STR_TO_DATE('", deptDate, "', %Y-%m-%d), '", reason, "')");
			break;
		case Commands.APPLY_MERIT:
			applierId = requestObject.getInt("id");
			content = requestObject.getString("content");
			
			if(requestObject.has("target")) {
				// Case that recommendation
				String recommendTarget = requestObject.getString("target");
				database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES(", applierId, ", '", recommendTarget, "', '", content, "')");
			} else {
				database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES(", applierId, ", '", content, "')");
			}
			break;
		case Commands.APPLY_AFTERSCHOOL:
			applierId = requestObject.getInt("id");
			no = requestObject.getInt("no");
			
			database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES(", applierId, ", ", no, ")");
			break;
		}
		
		return responseObject;
	}
}
