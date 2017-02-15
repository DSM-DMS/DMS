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
			
			
			break;
		case Commands.LOAD_EXTENSION_STATUS:
			
			
			break;
		case Commands.LOAD_STAY_STATUS:
			
			
			break;
		case Commands.LOAD_STAY_DEFAULT:
			
			
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
}
