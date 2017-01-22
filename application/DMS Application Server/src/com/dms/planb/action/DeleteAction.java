package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;
import com.dms.planb.support.Commands;

public class DeleteAction implements Actionable {
	@Override
	public JSONObject action(int command, JSONObject requestObject) throws JSONException, SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
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

}
