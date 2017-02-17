package com.dms.planb.action.applyaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_GOINGOUT)
public class ApplyGoingout implements Actionable {
	
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Apply goingout - about departure date and reason
		 * 
		 * Table Name : goingout_apply
		 * 
		 * id VARCHAR(20) PK NN
		 * date TINYINT(1) NN
		 * reason VARCHAR(100) NN
		 * 
		 * DATE format : YYYY-MM-DD
		 */
		String id = requestObject.getString("id");
		boolean date = requestObject.getBoolean("date");
		String reason = requestObject.getString("reason");
		
		database.executeUpdate("DELETE FROM goingout_apply WHERE id='", id, "' AND date=", date);
		int status = database.executeUpdate("INSERT INTO goingout_apply(id, date, reason) VALUES('", id, "', ", date, ", '", reason, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
