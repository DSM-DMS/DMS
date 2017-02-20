package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.actions.support.Sender;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_AFTERSCHOOL_ITEM)
public class UploadAfterschoolItem implements Actionable {
	@Override
	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * After school list
		 * 
		 * Table Name : afterschool_list
		 * 
		 * no INT(11) PK NN
		 * title VARCHAR(45) NN
		 * target INT(1) NN
		 * place VARCHAR(10) NN
		 * on_monday TINYINT(1) NN
		 * on_tuesday TINYINT(1) NN
		 * on_wednesday TINYINT(1) NN
		 * on_saturday TINYINT(1) NN
		 * instructor VARCHAR(10) NN
		 * personnel INT(11) NN
		 */
		int no = requestObject.getInt("no");
		String title = requestObject.getString("title");
		
		int target = requestObject.getInt("target");
		String place = requestObject.getString("place");
		boolean onMonday = requestObject.getBoolean("on_monday");
		boolean onTuesday = requestObject.getBoolean("on_tuesday");
		boolean onWednesday = requestObject.getBoolean("on_wednesday");
		boolean onSaturday = requestObject.getBoolean("on_saturday");
		String instructor = requestObject.getString("instructor");
		int personnel = requestObject.getInt("personnel");
		
		int status = database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, on_monday, on_tuesday, on_wednesday, on_saturday, instructor, personnel) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", onMonday, ", ", onTuesday, ", ", onWednesday, ", ", onSaturday, ", '", instructor, "', ", personnel, ")");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
