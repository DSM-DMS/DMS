package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_QNA)
public class LoadQna implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int no = requestObject.getInt("no");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM qna WHERE no=", no);
		
		if(resultSet.next()) {
			responseObject.put("title", resultSet.getString("title"));
			responseObject.put("question_content", resultSet.getString("question_content"));
			responseObject.put("question_date", resultSet.getString("question_date"));
			responseObject.put("writer", resultSet.getString("writer"));
			responseObject.put("privacy", resultSet.getBoolean("privacy"));
			if(resultSet.getString("answer_content") != null) {
				responseObject.put("has_answer", true);
				responseObject.put("answer_content", resultSet.getString("answer_content"));
				responseObject.put("answer_date", resultSet.getString("answer_date"));
			} else {
				responseObject.put("has_answer", false);
			}
		} else {
			responseObject.put("status", 404);
		}
		
		return responseObject;
	}
}
