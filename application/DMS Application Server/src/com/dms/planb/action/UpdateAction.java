package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;
import com.dms.planb.support.Commands;

public class UpdateAction implements Action {
	private int command;
	private JSONObject requestObject;
	
	public UpdateAction(int command, JSONObject requestObject) {
		this.command = command;
		this.requestObject = requestObject;
	}
	
	@Override
	public JSONObject action() throws JSONException, SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
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

}
