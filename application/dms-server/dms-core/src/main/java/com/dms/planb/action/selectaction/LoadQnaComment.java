package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_QNA_COMMENT)
public class LoadQnaComment implements Actionable {
	EasyJsonObject tempObject;
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int qnaNo = requestObject.getInt("no");
		
		SafeResultSet resultSet = database.executeQuery("SELECT * FROM qna_comment WHERE no=", qnaNo);
		
		if(resultSet.next()) {
			do {
			tempObject = new EasyJsonObject();
			
			tempObject.put("writer", resultSet.getString("writer"));
			tempObject.put("comment_day", resultSet.getString("comment_day"));
			tempObject.put("content", resultSet.getString("content"));
			
			array.add(tempObject);
			} while(resultSet.next());
		} else {
			responseObject.put("status", 404);
		}
		
		responseObject.put("result", array);
		
		return responseObject;
	}
}
