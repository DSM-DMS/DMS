package com.boxfox.dms.meal.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class Meal {
	private JSONArray menu, allergies;

	public Meal(JSONArray menu, JSONArray allergies) {
		this.menu = menu;
		this.allergies = allergies;
		if (menu == null) {
			JSONArray arr = new JSONArray();
			arr.add("급식이 없습니다.");
			this.menu = arr;
			this.allergies = arr;
		}
	}

	public JSONArray getMenu() {
		return menu;
	}

	public JSONArray getAllergies() {
		return allergies;
	}
	
	public void setMenu(String arr) {
		this.menu = (JSONArray)JSONValue.parse(arr);
	}
	
	public void setAllergies(String arr) {
		this.allergies = (JSONArray)JSONValue.parse(arr);
	}

}
