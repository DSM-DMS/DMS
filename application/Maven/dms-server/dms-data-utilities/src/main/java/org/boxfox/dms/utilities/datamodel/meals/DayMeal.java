package org.boxfox.dms.utilities.datamodel.meals;

import java.sql.ResultSet;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DayMeal extends DataSaveAble {
	private String date;
	private Meal[] meals;

	public DayMeal(String date, Meal... meals) {
		this.date = date;
		this.meals = meals;
	}

	public DayMeal() {
	}

	@Override
	public String toQuery() {
		return QueryUtils.querySetter(Query.MEAL.insertFormat, date, meals[0].getMenu(), meals[1].getMenu(),
				meals[2].getMenu(), meals[0].getAllergies(), meals[1].getAllergies(), meals[2].getAllergies()) + ";";
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray mealArr = new JSONArray();
		obj.put("Date", date);
		for (int i = 0; i < meals.length; i++) {
			JSONObject mealObj = new JSONObject();
			mealObj.put("Menu", meals[i].getMenu());
			mealObj.put("Allergy", meals[i].getAllergies());
			mealArr.add(mealObj);
		}
		obj.put("Meals", mealArr);
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) {
		vaild = false;
		meals = new Meal[3];
		meals[0] = new Meal((JSONArray) tryJsonParse(rs, "breakfast"),
				(JSONArray) tryJsonParse(rs, "breakfast_allergy"));
		meals[1] = new Meal((JSONArray) tryJsonParse(rs, "lunch"), (JSONArray) tryJsonParse(rs, "lunch_allergy"));
		meals[2] = new Meal((JSONArray) tryJsonParse(rs, "dinner"), (JSONArray) tryJsonParse(rs, "dinner_allergy"));
		for (int i = 0; i < meals.length; i++)
			if (meals[i].getMenu().size() > 0) {
				vaild = true;
			}
		return this;
	}
}
