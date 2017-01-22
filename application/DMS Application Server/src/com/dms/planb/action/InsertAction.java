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
		
		switch(command) {
		case Commands.REGISTER_ACCOUNT:
			String accountId = requestObject.getString("id");
			String accountPassword = requestObject.getString("password");
			String sessionKey = requestObject.getString("session_key");
			int permission = requestObject.getInt("permission");
			
			database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", accountId, "', '", accountPassword, "', '", sessionKey, "', ", permission, ")");
			
			int studentNumber = requestObject.getInt("number");
			int studentSex = requestObject.getInt("sex");
			int studentStatus = requestObject.getInt("status");
			String studentName = requestObject.getString("name");
			String studentPhone = requestObject.getString("phone");
			String parentName = requestObject.getString("p_name");
			String parentPhone = requestObject.getString("p_phone");
			
			database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
			
			break;
		case Commands.UPLOAD_NOTICE:
			// I think auto-increment the no column
			String noticeTitle = requestObject.getString("title");
			String noticeContent = requestObject.getString("content");
			String noticeWriter = requestObject.getString("writer");
			
			database.executeUpdate("INSERT INTO notice(title, content, writer) VALUES('", noticeTitle, "', '", noticeContent, "', '", noticeWriter, "')");
			break;
		case Commands.UPLOAD_RULE:
			// I think auto-increment the no column
			String ruleTitle = requestObject.getString("title");
			String ruleContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO rule(title, content) VALUES('", ruleTitle, "', '", ruleContent, "')");
			break;
		case Commands.UPLOAD_QUESTION:
			// I think auto-increment the no column
			String qnaTitle = requestObject.getString("title");
			String questionContent = requestObject.getString("question_content");
			String questionDate = requestObject.getString("question_date");
			String questioner = requestObject.getString("questioner");
			int privacy = requestObject.getInt("privacy");
			
			database.executeUpdate("INSERT INTO qna(title, question_content, question_date, questioner, privacy) VALUES('", qnaTitle, "', '", questionContent, "', '", questionDate, "', '", questioner, "', ", privacy, ")");			
			break;
		case Commands.UPLOAD_ANSWER:
			// Upload answer based question no
			int targetQuestionNo = requestObject.getInt("no");
			String answerContent = requestObject.getString("answer_content");
			String answerDate = requestObject.getString("answer_date");
			
			database.executeUpdate("UPDATE qna SET answer_content='", answerContent, "', answer_date='", answerDate, "' WHERE no=", targetQuestionNo);
			break;
		case Commands.UPLOAD_QNA_COMMENT:
			// Upload comment based QNA no
			int targetQnaNo = requestObject.getInt("no");
			// no is primary key, one QNA post can have 2 or more comments.
			String qnaCommentWriter = requestObject.getString("writer");
			String qnaCommentContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(",targetQnaNo, ", '", qnaCommentWriter, "', now(), '", qnaCommentContent, "')");
			break;
		case Commands.UPLOAD_FAQ:
			// I think auto-increment the no column
			String faqTitle = requestObject.getString("title");
			String faqContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO faq(title, content) VALUES('", faqTitle, "', '", faqContent, "')");
			break;
		case Commands.UPLOAD_COMPETITION:
			// I think auto-increment the no column
			// Should INSERT INTO app_content but I can't find any ways.
			
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			// I think auto-increment the no column
			String reportTitle = requestObject.getString("title");
			String reportContent = requestObject.getString("content");
			int roomNo = requestObject.getInt("room");
			String reportWriter = requestObject.getString("writer");
			
			database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", reportTitle, "', '", reportContent, "', ", roomNo, ", NOW(), '", reportWriter, "')");
			break;
		case Commands.UPLOAD_REPORT_RESULT:
			int targetReportNo = requestObject.getInt("no");
			String reportResult = requestObject.getString("result");
			
			database.executeUpdate("UPDATE facility_report SET result='", reportResult, "', result_date=NOW() WHERE no=", targetReportNo);
			break;
		case Commands.UPLOAD_MEAL:
			// does Meal needs command?
			
			break;
		case Commands.UPLOAD_PLAN:
			// does Plan needs command?
			
			break;
		case Commands.UPLOAD_AFTERSCHOOL:
			int afterschoolNo = requestObject.getInt("no");
			String afterschoolTitle = requestObject.getString("title");
			int afterschoolTarget = requestObject.getInt("target");
			String afterschoolPlace = requestObject.getString("place");
			int afterschoolDay = requestObject.getInt("day");
			String afterschoolInstructor = requestObject.getString("instructor");
			
			database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, day, instructor) VALUES(", afterschoolNo, ", '", afterschoolTitle, "', ", afterschoolTarget, ", '", afterschoolPlace, "', ", afterschoolDay, ", '", afterschoolInstructor, "')");
			break;
		case Commands.APPLY_EXTENTION:
			int applierId = requestObject.getInt("id");
			int classId = requestObject.getInt("class");
			int seatId = requestObject.getInt("seat");
			
			database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES(", applierId, ", ", classId, ", ", seatId, ")");
			break;
		case Commands.APPLY_STAY:
			int applierId1 = requestObject.getInt("id");
			// Need to change variable name
			int extensionValue = requestObject.getInt("value");
			String date = requestObject.getString("date");
			
			database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES(", applierId1, ", ", extensionValue, ", '", date, "')");
			break;
		case Commands.APPLY_GOINGOUT:
			/*
			 * Date Format : YYYY-MM-DD
			 */
			int applierId2 = requestObject.getInt("id");
			// Need to change variable name
			String deptDate = requestObject.getString("dept_date");
			String reason = requestObject.getString("reason");
			
			database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES(", applierId2, ", STR_TO_DATE(", deptDate, ", %Y-%m-%d), '", reason, "')");
			break;
		case Commands.APPLY_MERIT:
			// I think auto-increment the no column
			int applierId3 = requestObject.getInt("id");
			// Need to change variable name
			String applyContent = requestObject.getString("content");
			
			if(requestObject.has("target")) {
				// Case that recommendation
				String target = requestObject.getString("target");
				database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES(", applierId3, ", '", target, "', '", applyContent, "')");
			} else {
				database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES(", applierId3, ", '", applyContent, "')");
			}
			break;
		case Commands.APPLY_AFTERSCHOOL:
			int applierId4 = requestObject.getInt("id");
			// Need to change variable name
			int targetNo = requestObject.getInt("no");
			
			database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES(", applierId4, ", ", targetNo, ")");
			break;
		}
		
		return responseObject;
	}
}
