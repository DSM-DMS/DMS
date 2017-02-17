package com.dms.planb.action.applyaction;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Config;

public class ApplyExtension implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Table Name : extension_apply
		 * 
		 * id VARCHAR(20) PK NN
		 * class INT(1) NN
		 * seat INT(2) NN
		 */
		Calendar currentTime = Calendar.getInstance();
		int hour = currentTime.get(Calendar.HOUR_OF_DAY);
		int minute = currentTime.get(Calendar.MINUTE);
		int setHour = Integer.valueOf(Config.EXTENSION_APPLY_TIME.split(":")[0]);
		int setMinute = Integer.valueOf(Config.EXTENSION_APPLY_TIME.split(":")[1]);
		if(hour<setHour||(hour==setHour&&minute<setMinute)){
			
			
			can't apply
			
			
			
		}
		
		
		
		String id = requestObject.getString("id");
		int classId = requestObject.getInt("class");
		int seatId = requestObject.getInt("seat");
		
		database.executeUpdate("DELETE FROM extension_apply WHERE id='", id, "'");
		int status = database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES('", id, "', ", classId, ", ", seatId, ")");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
