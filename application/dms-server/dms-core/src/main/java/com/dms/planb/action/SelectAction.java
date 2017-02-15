package com.dms.planb.action;

import org.boxfox.dms.utilities.actions.Actionable;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.dataio.meal.MealModel;
import org.boxfox.dms.utilities.dataio.plan.PlanModel;
import org.boxfox.dms.utilities.dataio.post.PostModel;
import org.boxfox.dms.utilities.json.EasyJsonArray;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

/**
 * @author JoMingyu
 */
public class SelectAction implements Actionable {
	/** (non-Javadoc)
	 * @see com.dms.planb.action.Actionable#action(int, org.boxfox.dms.utilities.json.EasyJsonObject)
	 */
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		SafeResultSet resultSet;
		EasyJsonObject responseObject = new EasyJsonObject();
		EasyJsonArray array = new EasyJsonArray();
		EasyJsonObject tempObject;
		
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		String password = null;
		int number;
		
		// For post
		int no;
		int qnaNo;
		int category;
		
		// For apply
		String week = null;
		
		// For meal&plan
		int year;
		int month;
		int day;
		
		switch(command) {
		case Commands.LOAD_MYPAGE:
			
			
			break;
//		case Commands.LOAD_TEACHER_MYPAGE:
//			id = (String) requestObject.get("id");
//			
//			resultSet = database.executeQuery("SELECT * FROM teacher_account WHERE id='", id, "'");
//			
//			break;
		case Commands.LOAD_ACCOUNT:
			// When login
			
			
			break;
		case Commands.LOAD_TEACHER_ACCOUNT:
			// When login
			
			
			break;
		case Commands.LOAD_NOTICE_LIST:
		case Commands.LOAD_NEWSLETTER_LIST:
		case Commands.LOAD_COMPETITION_LIST:
			
			break;
		case Commands.LOAD_QNA_LIST:
			
			break;
		case Commands.LOAD_REPORT_FACILITY_LIST:
			
			break;
		case Commands.LOAD_AFTERSCHOOL_LIST:
			
			break;
		case Commands.LOAD_NOTICE:
		case Commands.LOAD_NEWSLETTER:
		case Commands.LOAD_COMPETITION:
			
			break;
		case Commands.LOAD_RULE:
			
			break;
		case Commands.LOAD_QNA:
			
			break;
		case Commands.LOAD_QNA_COMMENT:
			
			break;
		case Commands.LOAD_FAQ:
			
			break;
		case Commands.LOAD_REPORT_FACILITY:
			no = requestObject.getInt("no");
			
			resultSet = database.executeQuery("SELECT * FROM facility_report WHERE no=", no);
			
			if(resultSet.next()) {
				responseObject.put("title", resultSet.getString("title"));
				responseObject.put("content", resultSet.getString("content"));
				responseObject.put("room", resultSet.getInt("room"));
				responseObject.put("write_day", resultSet.getString("write_day"));
				responseObject.put("writer", resultSet.getString("writer"));
				if(resultSet.getString("result") != null){
					responseObject.put("has_result", true);
					responseObject.put("result", resultSet.getString("result"));
					responseObject.put("result_day", resultSet.getString("result_day"));
				} else {
					responseObject.put("has_result", false);
				}
			} else {
				responseObject.put("status", 404);
			}
			
			break;
		case Commands.LOAD_EXTENTION_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM extension_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("class", resultSet.getInt("class"));
				responseObject.put("seat", resultSet.getInt("seat"));
			} else {
				responseObject.put("status", 404);
			}
			
			break;
		case Commands.LOAD_STAY_STATUS:
			id = requestObject.getString("id");
			week = requestObject.getString("week");
			/*
			 * week format : YYYY-MM-DD
			 */
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply WHERE id='", id, "' AND date='", week, "'");
			
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
					
					tempObject.put("value", resultSet.getInt("value"));
					tempObject.put("day", resultSet.getString("day"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 404);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_STAY_DEFAULT:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM stay_apply_default WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("value", resultSet.getString("value"));
			} else {
				responseObject.put("status", 404);
			}
			
			break;
		case Commands.LOAD_GOINGOUT_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM goingout_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				responseObject.put("dept_day", resultSet.getString("dept_day"));
				responseObject.put("reason", resultSet.getString("reason"));
			} else {
				responseObject.put("status", 404);
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
				responseObject.put("status", 404);
			}
			
			break;
		case Commands.LOAD_AFTERSCHOOL_STATUS:
			id = requestObject.getString("id");
			
			resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE id='", id, "'");
			
			if(resultSet.next()) {
				do {
					tempObject = new EasyJsonObject();
				
					tempObject.put("no", resultSet.getInt("no"));
					
					array.add(tempObject);
				} while(resultSet.next());
			} else {
				responseObject.put("status", 404);
			}
			
			responseObject.put("result", array);
			
			break;
		case Commands.LOAD_MEAL:
			year = requestObject.getInt("year");
			month = requestObject.getInt("month");
			day = requestObject.getInt("day");
			
			responseObject.put("result", MealModel.getMealAtDate(year, month, day).toJSONObject());
			
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
				responseObject.put("status", 404);
			}
			
			break;
		}
		
		return responseObject;
	}
}
