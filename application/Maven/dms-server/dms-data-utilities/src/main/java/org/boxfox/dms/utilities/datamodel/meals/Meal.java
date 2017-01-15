package org.boxfox.dms.utilities.datamodel.meals;

import org.json.simple.JSONArray;

public class Meal{
	private JSONArray menu, allergies;

	public Meal(JSONArray menu, JSONArray allergies) {
		this.menu = menu;
		this.allergies = allergies;
	}
	
	public JSONArray getMenu() {
		return menu;
	}

	public JSONArray getAllergies() {
		return allergies;
	}

}
