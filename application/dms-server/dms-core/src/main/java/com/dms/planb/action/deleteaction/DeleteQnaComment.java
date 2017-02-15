package com.dms.planb.action.deleteaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=314)
public class DeleteQnaComment implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		String date = requestObject.getString("date");
		
		int status = database.executeUpdate("DELETE qna_comment WHERE no=", no, " AND date='", date, "'");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
