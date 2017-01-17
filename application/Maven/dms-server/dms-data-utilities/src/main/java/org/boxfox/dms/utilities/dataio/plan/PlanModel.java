package org.boxfox.dms.utilities.dataio.plan;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.datamodel.plan.MonthPlan;

public class PlanModel {

	public static MonthPlan getPlan(int year, int month) {
		try {
			ResultSet rs = DataBase.getInstance().executeQuery(Query.PLAN.selectFormat, year, month);
			if (rs.next()) {
				MonthPlan plan = (MonthPlan) new MonthPlan().fromResultSet(rs);
				if (plan.isVaild())
					return plan;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new PlanParser(year,month).parse();
	}

}
