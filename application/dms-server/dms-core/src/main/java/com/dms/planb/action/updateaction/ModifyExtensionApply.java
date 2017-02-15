package com.dms.planb.action.updateaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.MODIFY_EXTENSION_APPLY)
public class ModifyExtensionApply implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		String id = requestObject.getString("id");
		
		int status = 1;
		if(requestObject.containsKey("class")) {
			status = database.executeUpdate("UPDATE extension_apply SET class=", requestObject.getInt("class"), " WHERE id='", id, "'");
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		if(requestObject.containsKey("seat")) {
			status = database.executeUpdate("UPDATE extension_apply SET seat=", requestObject.getInt("seat"), " WHERE id='", id, "'");
			if(status == 0) {
				// If failed
				responseObject.put("status", status);
				return responseObject;
			}
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
