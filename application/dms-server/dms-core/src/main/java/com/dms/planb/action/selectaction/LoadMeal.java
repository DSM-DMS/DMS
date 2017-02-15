package com.dms.planb.action.selectaction;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import org.boxfox.dms.utilities.dataio.meal.MealModel;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

@ActionRegistration(command=Commands.LOAD_MEAL)
public class LoadMeal implements Actionable {
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		int year = requestObject.getInt("year");
		int month = requestObject.getInt("month");
		int day = requestObject.getInt("day");
		
		responseObject.put("result", MealModel.getMealAtDate(year, month, day).toJSONObject());
		
		return responseObject;
	}
}
