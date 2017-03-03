package com.dms.planb.action.post.parsed;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.parser.dataio.post.PostModel;
import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_COMPETITION)
public class LoadCompetition implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		int number = requestObject.getInt("number");
		int category = requestObject.getInt("category");
		/*
		 * Categories
		 * Notice : 0
		 * Newsletter : 1
		 * Competition : 2
		 */
		
		responseObject.put("result", PostModel.getPost(category, number));
		
		return responseObject;
	}
}
