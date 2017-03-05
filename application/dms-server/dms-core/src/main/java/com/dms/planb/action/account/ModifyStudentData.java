//package com.dms.planb.action.account;
//
//import java.sql.SQLException;
//
//import org.boxfox.dms.utilities.actions.ActionRegistration;
//import org.boxfox.dms.utilities.actions.Actionable;
//import org.boxfox.dms.utilities.actions.support.Sender;
//import org.boxfox.dms.utilities.json.EasyJsonObject;
//
//import com.dms.planb.support.Commands;
//
//@ActionRegistration(command = Commands.MODIFY_STUDENT_DATA)
//public class ModifyStudentData implements Handler<RoutingContext> {
//	@Override
//	public EasyJsonObject action(Sender sender, int command, EasyJsonObject requestObject) throws SQLException {
//		String id = requestObject.getString("id");
//		
//		int status = 1;
//		if(requestObject.containsKey("sex")) {
//			status = database.executeUpdate("UPDATE student_data SET sex=", requestObject.getInt("sex"), " WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		if(requestObject.containsKey("status")) {
//			status = database.executeUpdate("UPDATE student_data SET status=", requestObject.getInt("status"), " WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		if(requestObject.containsKey("name")) {
//			status = database.executeUpdate("UPDATE student_data SET name='", requestObject.getString("name"), "' WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		if(requestObject.containsKey("phone")) {
//			status = database.executeUpdate("UPDATE student_data SET phone='", requestObject.getString("phone"), "' WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		if(requestObject.containsKey("p_name")) {
//			status = database.executeUpdate("UPDATE student_data SET p_name='", requestObject.getString("p_name"), "' WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		if(requestObject.containsKey("p_phone")) {
//			status = database.executeUpdate("UPDATE student_data SET p_phone='", requestObject.getString("p_phone"), "' WHERE id='", id, "'");
//			if(status == 0) {
//				// If failed
//				responseObject.put("status", status);
//				return responseObject;
//			}
//		}
//		
//		responseObject.put("status", status);
//		
//		return responseObject;
//	}
//}
