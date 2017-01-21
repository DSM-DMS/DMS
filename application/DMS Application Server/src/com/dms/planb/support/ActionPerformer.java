package com.dms.planb.support;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.planb.action.Action;
import com.dms.planb.action.DeleteAction;
import com.dms.planb.action.InsertAction;
import com.dms.planb.action.SelectAction;
import com.dms.planb.action.UpdateAction;

/*
 * Check between this code and database's tables, and match data type.
 * Having foreign key : afterschool_apply, attachment, qna_comment table
 * 
 * Be sure to review the code.
 * 
 * Date Time Functions	: http://www.java2s.com/Tutorial/MySQL/0280__Date-Time-Functions/STRTODATEstrformat.htm
 * MySQL Commands		: https://opentutorials.org/course/195/1537 | http://city7310.blog.me/220902269838
 */

public class ActionPerformer {
	private static Action action;

	public static JSONObject perform(int command, JSONObject requestObject) throws JSONException, SQLException {
		// 1. Get prefix of command
		int prefixOfCommand = command / 100;
		
		// 2. Get instance of Action appropriate to the command.
		switch(prefixOfCommand) {
		case Commands.INSERT:
			action = new InsertAction(command, requestObject);
			break;
		case Commands.UPDATE:
			action = new UpdateAction(command, requestObject);
			break;
		case Commands.DELETE:
			action = new DeleteAction(command, requestObject);
			break;
		case Commands.SELECT:
			action = new SelectAction(command, requestObject);
			break;
		}
		
		// 3. Action, branch off one of four Action classes
		return action.action();
	}

	// Class for test
//	public static JSONObject accept() throws JSONException {
//		responseObject = new JSONObject();
//		responseObject.put("Command", 3);
//
//		return responseObject;
//	}
}