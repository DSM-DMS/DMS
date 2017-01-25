package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.json.simple.JSONObject;

import com.dms.planb.support.Commands;

public class DeleteAction implements Actionable {
	@Override
	public JSONObject action(int command, JSONObject requestObject) throws SQLException {
		JSONObject responseObject = new JSONObject();
		DataBase database = DataBase.getInstance();
		
		// For account
		String id = null;
		
		// For post
		int no;
		int number;
		int category;
		
		// For status
		int status = 0;
		
		switch(command) {
		case Commands.DELETE_ACCOUNT:
			id = (String)requestObject.get("id");
			
			status = database.executeUpdate("DELETE FROM account WHERE id=", id);
			break;
		case Commands.DELETE_NOTICE:
		case Commands.DELETE_NEWSLETTER:
		case Commands.DELETE_COMPETITION:
			number = (int)requestObject.get("number");
			category = (int)requestObject.get("category");
			
			status = database.executeUpdate("DELETE FROM app_content WHERE number=", number, " AND category=", category);
			break;
		case Commands.DELETE_RULE:
			no = (int)requestObject.get("no");
			
			status = database.executeUpdate("DELETE FROM rule WHERE no=", no);
			break;
		case Commands.DELETE_QUESTION:
			no = (int)requestObject.get("no");
			
			status = database.executeUpdate("DELETE FROM qna WHERE no=", no);
			break;
		case Commands.DELETE_ANSWER:
			no = (int)(int)requestObject.get("no");
			
			status = database.executeUpdate("UPDATE qna SET answer_content=NULL, answer_date=NULL WHERE no=", no);
			break;
		case Commands.DELETE_QNA_COMMENT:
			no = (int)(int)requestObject.get("no");
			
			status = database.executeUpdate("DELETE qna_comment WHERE no=", no);
			break;
		case Commands.DELETE_FAQ:
			no = (int)requestObject.get("no");
			
			status = database.executeUpdate("DELETE faq WHERE no=", no);
			break;
		case Commands.DELETE_REPORT_FACILITY:
			no = (int)requestObject.get("no");
			
			status = database.executeUpdate("DELETE facility_report WHERE no=", no);
			break;
		case Commands.DEAPPLY_EXTENTION:
			id = (String)requestObject.get("id");
			
			status = database.executeUpdate("DELETE extension_apply WHERE id='", id, "'");
			break;
		case Commands.DEAPPLY_GOINGOUT:
			id = (String)requestObject.get("id");
			
			status = database.executeUpdate("DELETE goingout_apply WHERE id='", id, "'");
			break;
		case Commands.DEAPPLY_MERIT:
			id = (String)requestObject.get("id");
			
			status = database.executeUpdate("DELETE merit_apply WHERE id='", id, "'");
			break;
		}
		
		responseObject.put("status", status);
		return responseObject;
	}
}
