package com.dms.planb.support;

import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;

public class ActionPerformer {

	private static JSONObject responseObject;
	private static DataBase database = DataBase.getInstance();

	public static JSONObject doInsert(int command, JSONObject requestObject) {
		responseObject = new JSONObject();
		
		if(command == Commands.REGISTER_ACCOUNT) {
			
		} else if(command == Commands.UPLOAD_NOTICE) {
			
		} else if(command == Commands.UPLOAD_RULE) {
			
		} else if(command == Commands.UPLOAD_QNA) {
			
		} else if(command == Commands.UPLOAD_CONTACT) {
			
		} else if(command == Commands.UPLOAD_COMPETITION) {
			
		} else if(command == Commands.UPLOAD_REPORT_FACILITY) {
			
		} else if(command == Commands.UPLOAD_MEAL) {
			
		} else if(command == Commands.UPLOAD_PLAN) {
			
		} else if(command == Commands.APPLY_EXTENTION) {
			
		} else if(command == Commands.APPLY_STAY) {
			
		} else if(command == Commands.APPLY_GOINGOUT) {
			
		} else if(command == Commands.APPLY_MERIT) {
			
		} else if(command == Commands.APPLY_AFTERSCHOOL) {
			
		}

		return responseObject;
	}

	public static JSONObject doUpdate(int command, JSONObject requestObject) {
		responseObject = new JSONObject();
		
		if(command == Commands.MODIFY_ACCOUNT) {
			
		} else if(command == Commands.MODIFY_NOTICE) {
			
		} else if(command == Commands.MODIFY_RULE) {
			
		} else if(command == Commands.MODIFY_QNA) {
			
		} else if(command == Commands.MODIFY_CONTACT) {
			
		} else if(command == Commands.MODIFY_COMPETITION) {
			
		} else if(command == Commands.MODIFY_REPORT_FACILITY) {
			
		} else if(command == Commands.MODIFY_EXTENTION) {
			
		} else if(command == Commands.MODIFY_STAY) {
			
		} else if(command == Commands.MODIFY_GOINGOUT) {
			
		} else if(command == Commands.MODIFY_MERIT) {
			
		} else if(command == Commands.MODIFY_AFTERSCHOOL) {
			
		}

		return responseObject;
	}

	public static JSONObject doDelete(int command, JSONObject requestObject) {
		responseObject = new JSONObject();
		
		if(command == Commands.DELETE_ACCOUNT) {
			
		} else if(command == Commands.DELETE_NOTICE) {
			
		} else if(command == Commands.DELETE_RULE) {
			
		} else if(command == Commands.DELETE_QNA) {
			
		} else if(command == Commands.DELETE_CONTACT) {
			
		} else if(command == Commands.DELETE_COMPETITION) {
			
		} else if(command == Commands.DELETE_REPORT_FACILITY) {
			
		} else if(command == Commands.DEAPPLY_EXTENTION) {
			
		} else if(command == Commands.DEAPPLY_MERIT) {
			
		}

		return responseObject;
	}

	public static JSONObject doSelect(int command, JSONObject requestObject) {
		responseObject = new JSONObject();
		
		if(command == Commands.LOAD_MYPAGE) {
			
		} else if(command == Commands.LOAD_ACCOUNT) {
			
		} else if(command == Commands.LOAD_POST_LIST) {
			
		} else if(command == Commands.LOAD_NOTICE_LIST) {
			
		} else if(command == Commands.LOAD_QNA_LIST) {
			
		} else if(command == Commands.LOAD_CONTACT_LIST) {
			
		} else if(command == Commands.LOAD_COMPETITION_LIST) {
			
		} else if(command == Commands.LOAD_REPORT_FACILITY_LIST) {
			
		} else if(command == Commands.LOAD_NOTICE) {
			
		} else if(command == Commands.LOAD_RULE) {
			
		} else if(command == Commands.LOAD_QNA) {
			
		} else if(command == Commands.LOAD_CONTACT) {
			
		} else if(command == Commands.LOAD_COMPETITION) {
			
		} else if(command == Commands.LOAD_REPORT_FACILITY) {
			
		} else if(command == Commands.LOAD_EXTENTION_STATUS) {
			
		} else if(command == Commands.LOAD_STAY_STATUS) {
			
		} else if(command == Commands.LOAD_GOINGOUT_STATUS) {
			
		} else if(command == Commands.LOAD_MERIT_APPLY_STATUS) {
			
		} else if(command == Commands.LOAD_AFTERSCHOOL_STATUS) {
			
		} else if(command == Commands.LOAD_MEAL) {
			
		} else if(command == Commands.LOAD_PLAN) {
			
		} else if(command == Commands.LOAD_SCORE) {
			
		}

		return responseObject;
	}

	// Class for test
//	public static JSONObject accept() throws JSONException {
//		responseObject = new JSONObject();
//		responseObject.put("Command", 3);
//
//		return responseObject;
//	}
}
