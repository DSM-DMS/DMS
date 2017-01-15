package org.boxfox.dms.utilities.datamodel.plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtills;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MonthPlan extends DataSaveAble {
	private ArrayList<Plan> dayPlans;
	private int year, month;

	public MonthPlan(int year, int month) {
		dayPlans = new ArrayList<Plan>();
		this.year = year;
		this.month = month;
	}

	public void addPlan(Plan plan) {
		dayPlans.add(plan);
	}

	@Override
	public String toQuery() {
		return QueryUtills.querySetter(Query.PLAN.insertFormat, year, month, toJSONObject().toJSONString());
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for (Plan plan : dayPlans) {
			arr.add(plan.toJsonObject());
		}
		obj.put("Year", year);
		obj.put("Month", month);
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException {
		year = rs.getInt("year");
		month = rs.getInt("month");
		dayPlans = convertArrayList((JSONArray)JSONValue.parse(rs.getString("data")));
		return this;
	}
	
	private ArrayList<Plan> convertArrayList(JSONArray data){
		ArrayList<Plan> plans = new ArrayList<Plan>();
		for(int i = 0 ; i < data.size() ; i++){
			plans.add(new Plan((JSONObject)data.get(i)));
		}
		return plans;
	}

}
