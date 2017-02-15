package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.MODIFY_QNA_COMMENT)
public class ModifyQnaComment implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		int status = database.executeUpdate("UPDATE qna_comment SET content='", requestObject.getString("content"), "' WHERE no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
