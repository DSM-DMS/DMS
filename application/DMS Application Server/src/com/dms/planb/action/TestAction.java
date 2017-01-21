package com.dms.planb.action;

import org.json.JSONObject;

//예시로 구현한 Action
public class TestAction extends Action{
	private Request request;
	
	public TestAction(Request request, JSONObject obj, int cmd) {
		super(obj, cmd);
		this.request = request;
	}
	
	@Override
	public JSONObject actionPerform() {
		
		System.out.println("작업 수행");
		
		return null;
	}

}
