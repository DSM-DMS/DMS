package com.dms.planb.action.deleteaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=312)
public class DeleteQnaQuestion implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		int status = database.executeUpdate("DELETE FROM qna WHERE no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
