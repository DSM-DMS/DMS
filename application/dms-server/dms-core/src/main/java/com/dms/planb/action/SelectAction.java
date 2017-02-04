package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.dataio.meal.MealModel;
import org.boxfox.dms.utilities.dataio.plan.PlanModel;
import org.boxfox.dms.utilities.dataio.post.PostModel;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

public class SelectAction implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonObject tempObject = new EasyJsonObject();
		EasyJsonArray array = new EasyJsonArray();
		
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
		
		switch(command) {
		case Commands.LOAD_MYPAGE:
			number = requestObject.getInt("number");
			
			resultSet = database.executeQuery("SELECT * FROM student_data WHERE number=", number);
			
			if(resultSet.next()) {
				responseObject.put("sex", resultSet.getInt("sex"));
				responseObject.put("status", resultSet.getInt("status"));
				responseObject.put("name", resultSet.getString("name"));
				responseObject.put("phone", resultSet.getString("phone"));
				responseObject.put("p_name", resultSet.getString("p_name"));
				responseObject.put("merit", resultSet.getInt("merit"));
				responseObject.put("demerit", resultSet.getInt("demerit"));
			} else {
				responseObject.put("status", 2);
				/*
				 *  Can't find from DB, set status 2 and return 404 status code.
				 *  Below from here, same about this context will same meaning.
				 */
			}
			
			break;
//		case Commands.LOAD_TEACHER_MYPAGE:
//			id = (String) requestObject.get("id");
//			
//			resultSet = database.executeQuery("SELECT * FROM teacher_account WHERE id='", id, "'");
//			
//			break;
		case Commands.LOAD_ACCOUNT:
			// when login
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			
			resultSet = database.executeQuery("SELECT password FROM account WHERE id='", id, "'");
			
			if(!resultSet.next() || !resultSet.getString("password").equals(password)) {
				/*
				 * !resultSet.next() : Can't find id
				 * !resultSet.getString("password").equals(password) : Incorrect password
				 * 
				 * Can't find id or incorrect password, set permit to false
				 */
				responseObject.put("permit", false);
			}
			else if(resultSet.getString("password").equals(password)) {
				// Correct password
				responseObject.put("permit", true);
			}
			
			break;
		case Commands.LOAD_TEACHER_ACCOUNT:
			// when login
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			
			resultSet = database.executeQuery("SELECT password FROM FROM teacher_account WHERE id='", id, "'");
			
			if(!resultSet.next() || resultSet.getString("password").equals(password)) {
				/*
				 * !resultSet.next() : Can't find id
				 * !resultSet.getString("password").equals(password) : Incorrect password
				 * 
				 * Can't find id or incorrect password, set permit to false
				 */
				responseObject.put("permit", false);
			}
			else if(resultSet.getString("password").equals(password)) {
				// Correct password
				responseObject.put("permit", true);
			}
			
			break;
		case Commands.LOAD_NOTICE_LIST:
		case Commands.LOAD_NEWSLETTER_LIST:
		case Commands.LOAD_COMPETITION_LIST:
			category = requestObject.getInt("category");
			no = requestObject.getInt("page");
			
			responseObject.put("result", PostModel.getPostsAtPage(category, no));
			
			break;
		case Commands.LOAD_QNA_LIST:
			resultSet = database.executeQuery("SELECT * FROM qna");
			
			if(resultSet.next()) {
				// There're any posts in qna
				do {
					tempObject.clear();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("question_date", resultSet.getString("question_date"));
					tempObject.put("writer", resultSet.getString("wrtier"));
					tempObject.put("privacy", resultSet.getInt("privacy"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			// Arrays in object
			
			break;
		case Commands.LOAD_REPORT_FACILITY_LIST:
			resultSet = database.executeQuery("SELECT * FROM facility_report");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("room", resultSet.getInt("room"));
					tempObject.put("write_date", resultSet.getString("write_date"));
					tempObject.put("writer", resultSet.getInt("writer"));
					if(!resultSet.getString("result").isEmpty()) {
						tempObject.put("has_result", true);
					}
					
					array.add(tempObject);
				} while(resultSet.next());
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_AFTERSCHOOL_LIST:
			resultSet = database.executeQuery("SELECT * FROM afterschool_list");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("target", resultSet.getInt("target"));
					tempObject.put("place", resultSet.getString("place"));
					tempObject.put("on_monday", resultSet.getBoolean("on_monday"));
					tempObject.put("on_tuesday", resultSet.getBoolean("on_tuesday"));
					tempObject.put("on_wednesday", resultSet.getBoolean("on_wednesday"));
					tempObject.put("on_saturday", resultSet.getBoolean("on_saturday"));
					tempObject.put("instuctor", resultSet.getString("instructor"));

					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_NOTICE:
		case Commands.LOAD_NEWSLETTER:
		case Commands.LOAD_COMPETITION:
			number = requestObject.getInt("number");
			category = requestObject.getInt("category");
			/*
			 * Categories
			 * Notice : 0
			 * Newsletter : 1
			 * Competition : 2
			 */
			
			responseObject.put("result", PostModel.getPost(category, number));
			
			break;
		case Commands.LOAD_RULE:
			// Both list and content
			resultSet = database.executeQuery("SELECT * FROM rule");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("content", resultSet.getString("content"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_QNA:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
			
			if(resultSet.next()) {
				responseObject.put("title", resultSet.getString("title"));
				responseObject.put("question_content", resultSet.getString("question_content"));
				responseObject.put("question_date", resultSet.getString("question_date"));
				responseObject.put("writer", resultSet.getString("writer"));
				responseObject.put("privacy", resultSet.getInt("privacy"));
				if(!resultSet.getString("answer_content").isEmpty()) {
					responseObject.put("has_answer", true);
					responseObject.put("answer_content", resultSet.getString("answer_content"));
					responseObject.put("answer_date", resultSet.getString("answer_date"));
				}
				else {
					responseObject.put("hasAnswer", false);
				}
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_QNA_COMMENT:
			qnaNo = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM qna_comment WHERE no=", qnaNo);
			
			if(resultSet.next()) {
				do {
				tempObject.clear();
				
				tempObject.put("writer", resultSet.getString("writer"));
				tempObject.put("comment_date", resultSet.getString("comment_date"));
				tempObject.put("content", resultSet.getString("content"));
				
				array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_FAQ:
			// Both list and content
			resultSet = database.executeQuery("SELECT * FROM faq");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
					
					tempObject.put("no", resultSet.getInt("no"));
					tempObject.put("title", resultSet.getString("title"));
					tempObject.put("content", resultSet.getString("content"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM facility_report WHERE no=", no);
			
			if(resultSet.next()) {
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
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_EXTENTION_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM extension_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("class", resultSet.getInt("class"));
				responseObject.put("seat", resultSet.getInt("seat"));
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_STAY_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
					
					tempObject.put("value", resultSet.getInt("value"));
					tempObject.put("date", resultSet.getString("date"));
					
					array.add(tempObject);
				} while(resultSet.next());
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_STAY_DEFAULT:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply_default WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("value", resultSet.getString("value"));
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_GOINGOUT_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("dept_date", resultSet.getString("dept_date"));
				responseObject.put("reason", resultSet.getString("reason"));
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_MERIT_APPLY_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM merit_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("content", resultSet.getString("content"));
				if(!resultSet.getString("target").isEmpty()) {
					responseObject.put("has_target", true);
					responseObject.put("target", resultSet.getString("target"));
				} else {
					responseObject.put("has_target", false);
				}
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		case Commands.LOAD_AFTERSCHOOL_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				do {
					tempObject.clear();
				
					tempObject.put("no", resultSet.getInt("no"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 2);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_MEAL:
			year = requestObject.getInt("year");
			month = requestObject.getInt("month");
			date = requestObject.getInt("date");
			
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
			
			if(resultSet.next()) {
				responseObject.put("merit", resultSet.getInt("merit"));
				responseObject.put("demerit", resultSet.getInt("demerit"));
			} else {
				responseObject.put("status", 2);
			}
			
			break;
		}
		
		return responseObject;
	}
}
