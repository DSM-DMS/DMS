package org.boxfox.dms.utilities.dataio.meal;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.boxfox.dms.utilities.datamodel.meals.DayMeal;

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
