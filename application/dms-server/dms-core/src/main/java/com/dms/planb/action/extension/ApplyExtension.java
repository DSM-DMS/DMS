package com.dms.planb.action.extension;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.LimitConfig;
import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.APPLY_EXTENSION)
public class ApplyExtension implements Handler<RoutingContext> {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Table Name : extension_apply
		 * 
		 * class INT(1) NN PK,
		 * seat INT(2) NN PK,
		 * name VARCHAR(20) NN
		 */
		Calendar currentTime = Calendar.getInstance();
		int hour = currentTime.get(Calendar.HOUR_OF_DAY);
		int minute = currentTime.get(Calendar.MINUTE);
		int setHour = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[0]);
		int setMinute = Integer.valueOf(LimitConfig.EXTENSION_APPLY_TIME.split(":")[1]);
		if(hour<setHour||(hour==setHour&&minute<setMinute)){
			responseObject.put("status", 404);
			
			return responseObject;
		}
		
		int classId = requestObject.getInt("class");
		int seatId = requestObject.getInt("seat");
		String name = requestObject.getString("name");
		String id = requestObject.getString("id");
		
		database.executeUpdate("DELETE FROM extension_apply WHERE id='", id, "'");
		int status = database.executeUpdate("INSERT INTO extension_apply(class, seat, name, id) VALUES(", classId, ", ", seatId, ", '", name, "', '", id, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
