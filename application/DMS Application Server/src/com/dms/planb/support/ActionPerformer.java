package com.dms.planb.support;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;

/*
 * Check between this code and database's tables.
 * And match data type.
 * 
 * Date Time Functions	: http://www.java2s.com/Tutorial/MySQL/0280__Date-Time-Functions/STRTODATEstrformat.htm
 * MySQL Commands		: https://opentutorials.org/course/195/1537 | http://city7310.blog.me/220902269838
 */

public class ActionPerformer {

	private static JSONObject responseObject;
	private static DataBase database = DataBase.getInstance();

	public synchronized static JSONObject doInsert(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.REGISTER_ACCOUNT:
			String accountId = requestObject.getString("id");
			String accountPassword = requestObject.getString("password");
			String sessionKey = requestObject.getString("session_key");
			int permission = requestObject.getInt("permission");
			
			database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", accountId, "', '", accountPassword, "', '", sessionKey, "', ", permission, ")");
			break;
		case Commands.UPLOAD_NOTICE:
			// no 컬럼 Auto Increment 해야될것 같음
			String noticeTitle = requestObject.getString("title");
			String noticeContent = requestObject.getString("content");
			String noticeWriter = requestObject.getString("writer");
			
			database.executeUpdate("INSERT INTO notice(title, content, writer) VALUES('", noticeTitle, "', '", noticeContent, "', '", noticeWriter, "')");
			break;
		case Commands.UPLOAD_RULE:
			// no 컬럼 Auto Increment 해야될것 같음
			String ruleTitle = requestObject.getString("title");
			String ruleContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO rule(title, content) VALUES('", ruleTitle, "', '", ruleContent, "')");
			break;
		case Commands.UPLOAD_QUESTION:
			// no 컬럼 Auto Increment 해야될것 같음
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
			int targetQnaNo = requestObject.getInt("no");
			String qnaCommentWriter = requestObject.getString("writer");
			String qnaCommentContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(",targetQnaNo, ", '", qnaCommentWriter, "', now(), '", qnaCommentContent, "')");
			break;
		case Commands.UPLOAD_FAQ:
			// no 컬럼 Auto Increment 해야될것 같음
			String faqTitle = requestObject.getString("title");
			String faqContent = requestObject.getString("content");
			
			database.executeUpdate("INSERT INTO faq(title, content) VALUES('", faqTitle, "', '", faqContent, "')");
			break;
		case Commands.UPLOAD_COMPETITION:
			// no 컬럼 Auto Increment 해야될것 같음
			// app_content 테이블을 참조해야 될것 같은데 디테일한 부분을 모르겠음
			
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			// no 컬럼 Auto Increment 해야될것 같음
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
			// 필요한 커맨드일까?
			
			break;
		case Commands.UPLOAD_PLAN:
			// 필요한 커맨드일까?
			
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
			String applierId = requestObject.getString("id");
			int classId = requestObject.getInt("class");
			int seatId = requestObject.getInt("seat");
			
			database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES('", applierId, "', ", classId, ", ", seatId, ")");
			break;
		case Commands.APPLY_STAY:
			String applierId1 = requestObject.getString("id");
			// 변수명 교체 요망
			int extensionValue = requestObject.getInt("value");
			
			database.executeUpdate("INSERT INTO stay_apply(id, value) VALUES('", applierId1, "', ", extensionValue, ")");
			break;
		case Commands.APPLY_GOINGOUT:
			/*
			 * Date Format : YYYY-MM-DD
			 */
			String applierId2 = requestObject.getString("id");
			// 변수명 교체 요망
			String deptDate = requestObject.getString("dept_date");
			String reason = requestObject.getString("reason");
			
			database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES('", applierId2, "', STR_TO_DATE(", deptDate, ", %Y-%m-%d), '", reason, "')");
			break;
		case Commands.APPLY_MERIT:
			// no 컬럼 Auto Increment 해야될것 같음
			String applierId3 = requestObject.getString("id");
			// 변수명 교체 요망
			String applyContent = requestObject.getString("content");
			
			if(requestObject.has("target")) {
				// Case that recommendation
				String target = requestObject.getString("target");
				database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES('", applierId3, "', '", target, "', '", applyContent, "')");
			} else {
				database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES('", applierId3, "', '", applyContent, "')");
			}
			break;
		case Commands.APPLY_AFTERSCHOOL:
			String applierId4 = requestObject.getString("id");
			int targetNo = requestObject.getInt("no");
			
			database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", applierId4, "', ", targetNo, ")");
			break;
		}

		return responseObject;
	}

	public synchronized static JSONObject doUpdate(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		
		switch(command) {
		case Commands.MODIFY_ACCOUNT:
			break;
		case Commands.MODIFY_NOTICE:
			break;
		case Commands.MODIFY_RULE:
			break;
		case Commands.MODIFY_QUESTION:
			break;
		case Commands.MODIFY_ANSWER:
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

	public synchronized static JSONObject doDelete(int command, JSONObject requestObject) throws JSONException, SQLException {
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
		case Commands.DELETE_QUESTION:
			break;
		case Commands.DELETE_ANSWER:
			break;
		case Commands.DELETE_QNA_COMMENT:
			break;
		case Commands.DELETE_FAQ:
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

	public synchronized static JSONObject doSelect(int command, JSONObject requestObject) throws JSONException, SQLException {
		responseObject = new JSONObject();
		ResultSet resultSet;
		
		switch(command) {
		case Commands.LOAD_MYPAGE:
			break;
		case Commands.LOAD_ACCOUNT:
			// when login
			String id = requestObject.getString("id");
			String password = requestObject.getString("password");
			
			resultSet = database.executeQuery("SELECT * FROM account WHERE id=", id);
			if(resultSet.getString("password").equals(password)) {
				responseObject.put("status", true);
			}
			
			break;
		case Commands.LOAD_POST_LIST:
			break;
		case Commands.LOAD_NOTICE_LIST:
			break;
		case Commands.LOAD_QNA_LIST:
			break;
		case Commands.LOAD_FAQ_LIST:
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
		case Commands.LOAD_QNA_COMMENT:
			break;
		case Commands.LOAD_FAQ:
			break;
		case Commands.LOAD_COMPETITION:
			break;
		case Commands.LOAD_REPORT_FACILITY:
			break;
		case Commands.LOAD_EXTENTION_STATUS:
			int applierId = requestObject.getInt("id");
			
			resultSet = database.executeQuery("SELECT * FROM account WHERE id=", applierId);
			
			responseObject.put("class", resultSet.getInt("class"));
			responseObject.put("seat", resultSet.getInt("seat"));
			
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