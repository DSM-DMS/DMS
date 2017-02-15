package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.dataio.post.PostModel;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.LOAD_NOTICE_LIST)
public class LoadNoticeList implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int category = requestObject.getInt("category");
		int no = requestObject.getInt("page");
		
		responseObject.put("result", PostModel.getPostsAtPage(category, no));
		
		return responseObject;
	}
}
