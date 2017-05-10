package com.dms.parser.datamodel.plan;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Plan {
	private int day;
	private JSONArray dayPlan;

	public Plan(int day, JSONArray dayPlan) {
		this.day = day;
		this.dayPlan = dayPlan;
	}

	public Plan(JSONObject jsonObject) {
		day = Integer.valueOf(jsonObject.get("Day").toString());
		dayPlan = (JSONArray)jsonObject.get("Plan");
	}

	public int getDay() {
		return day;
	}

	public JSONArray getDayPlan() {
		return dayPlan;
	}

	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("Day", day);
		obj.put("Plan", dayPlan);
		return obj;
	}

}
