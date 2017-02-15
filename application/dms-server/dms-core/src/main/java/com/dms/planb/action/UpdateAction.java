package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

/**
 * @author JoMingyu
 */
public class UpdateAction implements Actionable {
	/**
	 * The data to be modified are received in JSON form at once.
	 * If there is key in request object, do action automatically.
	 * @see case Commands.MODIFY_STUDENT_DATA
	 */
	
	/** (non-Javadoc)
	 * @see com.dms.planb.action.Actionable#action(int, org.boxfox.dms.utilities.json.EasyJsonObject)
	 */
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		EasyJsonObject responseObject = new EasyJsonObject();
		
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		
		// For post
		int no;
		
		// For apply
		String week = null;
		String date = null;
		
		// For status
		int status = 1;
		
		switch(command) {
		case Commands.MODIFY_PASSWORD:
			
			break;
		case Commands.MODIFY_STUDENT_DATA:
			
			
			break;
		case Commands.MODIFY_RULE:
			
			
			break;
		case Commands.MODIFY_QNA_QUESTION:
			}
			
			break;
		case Commands.MODIFY_QNA_ANSWER:
			
			break;
		case Commands.MODIFY_QNA_COMMENT:
			
			break;
		case Commands.MODIFY_FAQ:
			
			
			break;
		case Commands.MODIFY_REPORT_FACILITY:
			
			
			break;
		case Commands.MODIFY_EXTENTION:
			
			
			break;
		case Commands.MODIFY_STAY:
			
			break;
		case Commands.MODIFY_STAY_DEFAULT:
			
			break;
		case Commands.MODIFY_GOINGOUT:
			
			
			break;
		case Commands.MODIFY_MERIT:
			
			break;
		case Commands.MODIFY_AFTERSCHOOL_APPLY:
			
			break;
		}
		
		if(status == 1) {
			responseObject.put("status", 200);
		} else {
			responseObject.put("status", 500);
		}
		
		return responseObject;
	}
}
