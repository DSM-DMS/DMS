package com.dms.planb.action.updateaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import org.boxfox.dms.algorithm.SHA256;
import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.MODIFY_PROFILE_IMAGE)
public class ModifyProfileImage implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		String hashedId = SHA256.encrypt(id);
		
		String data = requestObject.getString("data");
				
		File file = new File("Profile Images/".concat(hashedId));
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseObject;
	}
}
