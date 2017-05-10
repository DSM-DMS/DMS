package com.dms.parser.dataio.plan;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;

import com.dms.parser.dataio.Query;
import com.dms.parser.datamodel.plan.MonthPlan;

public class PlanModel {

	public static DataSaveAble getPlan(int year, int month) {
		try {
			SafeResultSet rs = DataBase.getInstance().executeQuery(QueryUtils.querySetter(Query.PLAN.selectFormat, year, month));
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
