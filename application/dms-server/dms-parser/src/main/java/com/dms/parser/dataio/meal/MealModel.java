package com.dms.parser.dataio.meal;

import java.sql.SQLException;

import com.dms.parser.dataio.Query;
import com.dms.parser.datamodel.meals.DayMeal;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.QueryUtils;
import com.dms.utilities.database.SafeResultSet;

public class MealModel{

	public static DayMeal getMealAtDate(int year, int month, int day) {
		String query = QueryUtils.querySetter(Query.MEAL.selectFormat, QueryUtils.queryCreateDate(year, month, day));
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(query);
			if(rs.next()){
				DayMeal dayMeal = (DayMeal)new DayMeal().fromResultSet(rs);
				if(dayMeal.isVaild()){
					return dayMeal;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new MealParser(year,month,day).parse();
	}

}
