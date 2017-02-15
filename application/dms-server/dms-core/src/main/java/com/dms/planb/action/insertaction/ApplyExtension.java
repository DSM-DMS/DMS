package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_EXTENSION)
public class ApplyExtension implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Table Name : extension_apply
		 * 
		 * id VARCHAR(20) PK NN
		 * class INT(1) NN
		 * seat INT(2) NN
		 */
		String id = requestObject.getString("id");
		int classId = requestObject.getInt("class");
		int seatId = requestObject.getInt("seat");
		
		int status = database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES('", id, "', ", classId, ", ", seatId, ")");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}