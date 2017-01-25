package com.dms.planb.support;

import java.sql.SQLException;

import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.json.simple.JSONObject;

import com.dms.planb.action.Actionable;
import com.dms.planb.action.DeleteAction;
import com.dms.planb.action.InsertAction;
import com.dms.planb.action.SelectAction;
import com.dms.planb.action.UpdateAction;

/*
 * Date Time Functions	: http://www.java2s.com/Tutorial/MySQL/0280__Date-Time-Functions/STRTODATEstrformat.htm
 * MySQL Commands		: https://opentutorials.org/course/195/1537 | http://city7310.blog.me/220902269838
 */

public class ActionPerformer {
	private static Actionable action;

	public static EasyJsonObject perform(int command, EasyJsonObject requestObject) throws SQLException {
		// 1. Get prefix of command
		int prefixOfCommand = command / 100;
		
		// 2. Get instance of Action appropriate to the command.
		switch(prefixOfCommand) {
		case Commands.INSERT:
			action = new InsertAction();
			break;
		case Commands.UPDATE:
			action = new UpdateAction();
			break;
		case Commands.DELETE:
			action = new DeleteAction();
			break;
		case Commands.SELECT:
			action = new SelectAction();
			break;
		}
		
		// 3. Action, branch off one of four Action classes
		return action.action(command, requestObject);
	}
}