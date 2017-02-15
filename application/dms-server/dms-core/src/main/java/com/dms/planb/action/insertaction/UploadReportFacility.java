package com.dms.planb.action.insertaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command = Commands.UPLOAD_REPORT_FACILITY)
public class UploadReportFacility implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		/**
		 * Report facilities in dormitory
		 * 
		 * Table Name : facility_report
		 * 
		 * no INT(11) PK NN AI
		 * title VARCHAR(45) NN
		 * content VARCHAR(1000) NN
		 * room INT(11) NN
		 * write_date DATETIME NN
		 * writer VARCHAR(10) NN
		 * result VARCHAR(100) Default NULL
		 * result_date DATETIME Default NULL
		 * 
		 * DATETIME format : YYYY-MM-DD hh:mm:ss
		 */
		String title = requestObject.getString("title");
		String content = requestObject.getString("content");
		int no = requestObject.getInt("room");
		String writer = requestObject.getString("writer");
		
		int status = database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", title, "', '", content, "', ", no, ", NOW(), '", writer, "')");
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
