package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.MODIFY_QNA_QUESTION)
public class ModifyQnaQuestion implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		int status = 1;
		if(requestObject.containsKey("title")) {
			status = database.executeUpdate("UPDATE qna SET title='", requestObject.getString("title"), "' WHERE no=", no);
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		if(requestObject.containsKey("question_content")) {
			status = database.executeUpdate("UPDATE qna SET question_content='", requestObject.getString("question_content"), "' WHERE no=", no);
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		if(requestObject.containsKey("writer")) {
			status = database.executeUpdate("UPDATE qna SET writer='", requestObject.getString("writer"), "' WHERE no=", no);
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
