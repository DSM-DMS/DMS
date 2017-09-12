package com.dms.parser.datamodel.meals;

import java.sql.SQLException;

import com.dms.utilities.database.DataSaveable;
import com.dms.utilities.database.QueryUtils;
import com.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dms.parser.dataio.Query;

public class DayMeal extends DataSaveable {
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
    public DataSaveable fromResultSet(SafeResultSet rs) throws SQLException {
        vaild = false;
        date = rs.getString("date");
        meals = new Meal[3];
        meals[0] = new Meal((JSONArray) tryJsonParse(rs, "breakfast"),
                (JSONArray) tryJsonParse(rs, "breakfast_allergy"));
        meals[1] = new Meal((JSONArray) tryJsonParse(rs, "lunch"), (JSONArray) tryJsonParse(rs, "lunch_allergy"));
        meals[2] = new Meal((JSONArray) tryJsonParse(rs, "dinner"), (JSONArray) tryJsonParse(rs, "dinner_allergy"));
        for (int i = 0; i < meals.length; i++)
            if (meals[i].getMenu().size() == 0 || meals[i].getMenu().toString().contains("급식이 없습니다.")) {
                vaild = false;
                break;
            }
        return this;
    }
}
