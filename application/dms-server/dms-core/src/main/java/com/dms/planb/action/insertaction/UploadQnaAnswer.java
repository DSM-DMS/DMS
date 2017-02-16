package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_QNA_ANSWER)
public class UploadQnaAnswer implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Answer in Q&A
		 * 
		 * Reference UPLOAD_QUESTION
		 * Upload answer based question no
		 */
		int no = requestObject.getInt("no");
		String content = requestObject.getString("answer_content");
		
		int status = database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date= now()", " WHERE no=", no);
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
