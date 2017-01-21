package com.dms.planb.action;

import org.json.JSONObject;

public abstract class Action {
	private final int COMMAND;
	private final JSONObject requestObject;
	
	public Action(JSONObject obj, int cmd){
		this.COMMAND = cmd;
		this.requestObject = obj;
	}
	
	public abstract JSONObject actionPerform();
	
	

}
