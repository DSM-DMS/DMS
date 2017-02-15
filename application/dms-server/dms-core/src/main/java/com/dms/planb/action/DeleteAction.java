package com.dms.planb.action;

import org.boxfox.dms.utilities.actions.Actionable;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

/**
 * @author JoMingyu
 */
public class DeleteAction implements Actionable {
	/** (non-Javadoc)
	 * @see org.boxfox.dms.utilities.actions.Actionable#action(int, org.boxfox.dms.utilities.json.EasyJsonObject)
	 */
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		EasyJsonObject responseObject = new EasyJsonObject();
		
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		
		// For post
		int no;
		String date;
		
		// For status
		int status = 1;
		
		switch(command) {
		case Commands.DELETE_ACCOUNT:
			
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
		case Commands.DELETE_REPORT_FACILITY:
			
			break;
		case Commands.DEAPPLY_EXTENTION:
			
			break;
		case Commands.DEAPPLY_GOINGOUT:
			
			break;
		case Commands.DEAPPLY_MERIT:
			
			break;
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
