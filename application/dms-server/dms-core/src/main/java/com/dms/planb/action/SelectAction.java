package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.dataio.meal.MealModel;
import org.boxfox.dms.utilities.dataio.plan.PlanModel;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dms.planb.support.Commands;

public class SelectAction implements Actionable {
	@SuppressWarnings("unchecked")
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		SafeResultSet resultSet;
		JSONObject responseObject = new JSONObject();
		EasyJsonObject readOnlyJsonObject;
		JSONObject tempObject = new JSONObject();
		JSONArray array = new JSONArray();
		
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		String password = null;
		int number;
		
		// For post
		int no;
		int qnaNo;
		int category;
		
		// For meal&plan
		int year;
		int month;
		int date;
		
		// For post list
		int count; // JSON Object's sequence count
		
		switch(command) {
		case Commands.LOAD_MYPAGE:
			number = requestObject.getInt("number");
			
			resultSet = database.executeQuery("SELECT * FROM student_data WHERE number=", number);
			resultSet.next();
			
			responseObject.put("sex", resultSet.getInt("sex"));
			responseObject.put("status", resultSet.getInt("status"));
			responseObject.put("name", resultSet.getString("name"));
			responseObject.put("phone", resultSet.getString("phone"));
			responseObject.put("p_name", resultSet.getString("p_name"));
			responseObject.put("merit", resultSet.getInt("merit"));
			responseObject.put("demerit", resultSet.getInt("demerit"));
			
			break;
//		case Commands.LOAD_TEACHER_MYPAGE:
//			id = (String) requestObject.get("id");
//			
//			resultSet = database.executeQuery("SELECT * FROM teacher_account WHERE id='", id, "'");
//			
//			break;
		case Commands.LOAD_ACCOUNT:
			// when login
			id = (String) requestObject.get("id");
			password = (String) requestObject.get("password");
			
			resultSet = database.executeQuery("SELECT password FROM account WHERE id='", id, "'");
			if(!resultSet.next() || !resultSet.getString("password").equals(password)) {
				responseObject.put("permit", false);
			}
			else if(resultSet.getString("password").equals(password)) {
				responseObject.put("permit", true);
			}
			
			break;
		case Commands.LOAD_TEACHER_ACCOUNT:
			// when login
			id = (String) requestObject.get("id");
			password = (String) requestObject.get("password");
			
			resultSet = database.executeQuery("SELECT password FROM FROM teacher_account WHERE id='", id, "'");
			if(!resultSet.next() || resultSet.getString("password").equals(password)) {
				responseObject.put("permit", false);
			}
			else if(resultSet.getString("password").equals(password)) {
				responseObject.put("permit", true);
			}
			
			break;
		case Commands.LOAD_NOTICE_LIST:
		case Commands.LOAD_NEWSLETTER_LIST:
		case Commands.LOAD_COMPETITION_LIST:
			count = 1;
			
			category = requestObject.getInt("category");
			
			resultSet = database.executeQuery("SELECT * FROM app_content WHERE category=", category);
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("number", resultSet.getInt("number"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("wrtier", resultSet.getString("writer"));
				tempObject.put("date", resultSet.getString("date"));
				
				array.add(tempObject);
			}
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_QNA_LIST:
			count = 1;
			
			resultSet = database.executeQuery("SELECT * FROM qna");
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("question_date", resultSet.getString("question_date"));
				tempObject.put("writer", resultSet.getString("wrtier"));
				tempObject.put("privacy", resultSet.getInt("privacy"));
				
				array.add(tempObject);
			}
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_FAQ_LIST:
			count = 1;
			
			resultSet = database.executeQuery("SELECT * FROM faq");
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				
				array.add(tempObject);
			}
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_REPORT_FACILITY_LIST:
			count = 1;
			
			resultSet = database.executeQuery("SELECT * FROM facility_report");
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("room", resultSet.getString("room"));
				tempObject.put("write_date", resultSet.getString("write_date"));
				tempObject.put("writer", resultSet.getInt("writer"));
				if(!resultSet.getString("result").isEmpty()) {
					tempObject.put("has_result", true);
				}
				
				array.add(tempObject);
			}
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_AFTERSCHOOL_LIST:
			count = 1;
			
			resultSet = database.executeQuery("SELECT * FROM afterschool_list");
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("no", resultSet.getInt("no"));
				tempObject.put("title", resultSet.getString("title"));
				tempObject.put("target", resultSet.getInt("target"));
				tempObject.put("place", resultSet.getString("place"));
				tempObject.put("day", resultSet.getInt("day"));
				tempObject.put("instuctor", resultSet.getString("instructor"));

				array.add(tempObject);
			}
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_NOTICE:
		case Commands.LOAD_NEWSLETTER:
		case Commands.LOAD_COMPETITION:
			number = requestObject.getInt("number");
			category = requestObject.getInt("category");
			
			resultSet = database.executeQuery("SELECT * FROM app_content WHERE number=", number, " AND category=", category);
			resultSet.next();
			
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("content", resultSet.getString("content"));
			responseObject.put("writer", resultSet.getString("writer"));
			responseObject.put("date", resultSet.getString("date"));
			
			break;
		case Commands.LOAD_RULE:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM rule WHERE no=", no);
			resultSet.next();
			
			responseObject.put("title", resultSet.getString("resultSet"));
			responseObject.put("content", resultSet.getString("content"));
			
			break;
		case Commands.LOAD_QNA:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
			resultSet.next();
			
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("question_content", resultSet.getString("question_content"));
			responseObject.put("question_date", resultSet.getString("question_date"));
			responseObject.put("questioner", resultSet.getString("questioner"));
			responseObject.put("privacy", resultSet.getInt("privacy"));
			if(!resultSet.getString("answer_content").isEmpty()) {
				responseObject.put("has_answer", true);
				responseObject.put("answer_content", resultSet.getString("answer_content"));
				responseObject.put("answer_date", resultSet.getString("answer_date"));
			}
			else {
				responseObject.put("hasAnswer", false);
			}
			
			break;
		case Commands.LOAD_QNA_COMMENT:
			count = 1;
			
			qnaNo = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM qna_comment WHERE no=", qnaNo);
			
			while(resultSet.next()) {
				tempObject.clear();
				
				tempObject.put("writer", resultSet.getString("writer"));
				tempObject.put("comment_date", resultSet.getString("comment_date"));
				tempObject.put("content", resultSet.getString("content"));
				
				array.add(tempObject);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_FAQ:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM faq WHERE no=", no);
			resultSet.next();
			
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("content", resultSet.getString("content"));
			
			break;
		case Commands.LOAD_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM facility_report WHERE no=", no);
			resultSet.next();
			
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("content", resultSet.getString("content"));
			responseObject.put("room", resultSet.getInt("room"));
			responseObject.put("write_date", resultSet.getString("write_date"));
			responseObject.put("writer", resultSet.getString("writer"));
			if(!resultSet.getString("result").isEmpty()){
				responseObject.put("has_result", true);
				responseObject.put("result", resultSet.getString("result"));
				responseObject.put("result_date", resultSet.getString("result_date"));
			} else {
				responseObject.put("has_result", false);
			}
			
			break;
		case Commands.LOAD_EXTENTION_STATUS:
			id = (String)requestObject.get("id");
			
			resultSet = database.executeQuery("SELECT * FROM extension_apply WHERE id='", id, "'");
			resultSet.next();
			
			responseObject.put("class", resultSet.getInt("class"));
			responseObject.put("seat", resultSet.getInt("seat"));
			
			break;
		case Commands.LOAD_STAY_STATUS:
			// Apply on a monthly basis
			id = (String)requestObject.get("id");
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "'");
			resultSet.next();
			
			responseObject.put("value", resultSet.getInt("value"));
			responseObject.put("date", resultSet.getString("date"));
			
			break;
		case Commands.LOAD_GOINGOUT_STATUS:
			id = (String)requestObject.get("id");
			
			resultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE id='", id, "'");
			resultSet.next();
			
			responseObject.put("dept_date", resultSet.getString("dept_date"));
			responseObject.put("reason", resultSet.getString("reason"));
			
			break;
		case Commands.LOAD_MERIT_APPLY_STATUS:
			id = (String)requestObject.get("id");
			
			resultSet = database.executeQuery("SELECT * FROM merit_apply WHERE id='", id, "'");
			resultSet.next();
			
			responseObject.put("content", resultSet.getString("content"));
			if(!resultSet.getString("target").isEmpty()) {
				responseObject.put("has_target", true);
				responseObject.put("target", resultSet.getString("target"));
			} else {
				responseObject.put("hasTarget", false);
			}
			
			break;
		case Commands.LOAD_AFTERSCHOOL_STATUS:
			count = 1;
			
			id = (String)requestObject.get("id");
			
			resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE id='", id, "'");
			
			while(resultSet.next()) {
				responseObject.put("sequence".concat(Integer.toString(count++)), resultSet.getInt("no"));
			}
			
			break;
		case Commands.LOAD_MEAL:
			year = (int) requestObject.get("year");
			month = (int) requestObject.get("month");
			date = (int) requestObject.get("date");
			
			responseObject.put("result", MealModel.getMealAtDate(year, month, date).toJSONObject());
			
			break;
		case Commands.LOAD_PLAN:
			year = requestObject.getInt("year");
			month = requestObject.getInt("month");
			
			responseObject.put("result", PlanModel.getPlan(year, month));
			
			break;
		case Commands.LOAD_SCORE:
			number = requestObject.getInt("number");
			
			resultSet = database.executeQuery("SELECT * FROM student_data WHERE number=", number);
			resultSet.next();
			
			responseObject.put("merit", resultSet.getInt("merit"));
			responseObject.put("demerit", resultSet.getInt("demerit"));
			
			break;
		}
		
		readOnlyJsonObject = new EasyJsonObject(responseObject);
		return readOnlyJsonObject;
	}
}
