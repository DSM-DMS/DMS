package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;
import com.dms.planb.support.ProfileImage;

@ActionRegistration(command=Commands.MODIFY_PROFILE_IMAGE)
public class ModifyProfileImage implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		String data = requestObject.getString("profile_image");
		
		ProfileImage.setProfileImage(id, data);
		
		return responseObject;
	}
}
