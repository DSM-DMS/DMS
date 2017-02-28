package com.dms.planb.action.post.qna;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_QNA_QUESTION)
public class UploadQnaQuestion implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
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
		 * answer_date DATETIME Default NULL
		 * privacy TINYINT(1) NN
		 * 
		 * DATETIME format : YYYY-MM-DD hh:mm:ss
		 */
		
		String title = requestObject.getString("title");
		String content = requestObject.getString("question_content");
		String writer = requestObject.getString("writer");
		
		boolean privacy = requestObject.getBoolean("privacy");
		
		int status = 1;
		
		if(requestObject.containsKey("no")) {
			/*
			 * Judge modify
			 */
			int no = requestObject.getInt("no");
			
			database.executeUpdate("UPDATE qna SET title='", title, "' WHERE no=", no);
			database.executeUpdate("UPDATE qna SET question_content='", content, "' WHERE no=", no);
			database.executeUpdate("UPDATE qna SET writer='", writer, "' WHERE no=", no);
		} else {
			/*
			 * Judge upload
			 */
			status = database.executeUpdate("INSERT INTO qna(title, question_content, question_date, writer, privacy) VALUES('", title, "', '", content, "', now(), '", writer, "', ", privacy, ")");
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
