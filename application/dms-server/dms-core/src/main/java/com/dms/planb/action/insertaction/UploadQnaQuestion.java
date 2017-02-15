package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

@ActionRegistration(command=112)
public class UploadQnaQuestion implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Question in Q&A
		 * 
		 * Table Name : qna
		 * 
		 * no INT(11) PK NN AI
		 * title VARCHAR(45) NN
		 * question_content VARCHAR(5000) NN
		 * question_date DATETIME NN
		 * writer VARCHAR(20) NN
		 * answer_content VARCHAR(5000) Default NULL
		 * answer_date DATETIME Defalt NULL
		 * privacy TINYINT(1) NN
		 * 
		 * DATETIME format : YYYY-MM-DD hh:mm:ss
		 */
		String title = requestObject.getString("title");
		String content = requestObject.getString("question_content");
		String writer = requestObject.getString("writer");
		
		int privacy = requestObject.getInt("privacy");
		
		int status = database.executeUpdate("INSERT INTO qna(title, question_content, question_date, writer, privacy) VALUES('", title, "', '", content, "', now(), '", writer, "', ", privacy, ")");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
