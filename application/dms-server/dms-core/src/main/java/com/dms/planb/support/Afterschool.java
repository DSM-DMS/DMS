package com.dms.planb.support;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

public class Afterschool {
	static DataBase database;
	static SafeResultSet resultSet;
	static SafeResultSet tempResultSet;

	static boolean[] appliedDay = new boolean[4];
	static boolean[] targetDay = new boolean[4];
	/*
	 * 0 : Monday
	 * 1 : Tuesday
	 * 2 : Wednesday
	 * 3 : Saturday
	 */

	public static boolean canApply(String id, int no) throws SQLException {
		database = DataBase.getInstance();

		resultSet = database.executeQuery("SELECT * FROM afterschool_list WHERE no=", no);
		/*
		 * Target
		 */

		targetDay[0] = resultSet.getBoolean("on_monday");
		targetDay[1] = resultSet.getBoolean("on_tuesday");
		targetDay[2] = resultSet.getBoolean("on_wednesday");
		targetDay[3] = resultSet.getBoolean("on_saturday");
		/*
		 * Target subject's date of course
		 */

		resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE id='", id, "'");

		if (resultSet.next()) {
			/*
			 * Applier's apply status
			 */
			
			tempResultSet = database.executeQuery("SELECT * FROM afterschool_list WHERE no=", resultSet.getInt("no"));
			/*
			 * Applier's after school number
			 */
			
			do {
				if (tempResultSet.getBoolean("on_monday")) {
					appliedDay[0] = true;
				}

				if (tempResultSet.getBoolean("on_tuesday")) {
					appliedDay[1] = true;
				}

				if (tempResultSet.getBoolean("on_wednesday")) {
					appliedDay[2] = true;
				}

				if (tempResultSet.getBoolean("on_saturday")) {
					appliedDay[3] = true;
				}
			} while (resultSet.next());
		} else {
			/*
			 * There's no any row(didn't any apply)
			 */

			return true;
			/*
			 * Can apply
			 */
		}

		for (int i = 0; i <= 3; i++) {
			if (targetDay[i] && !appliedDay[i]) {
				continue;
			} else {
				/*
				 * If overlapped
				 */
				
				return false;
				/*
				 * Can't apply
				 */
			}
		}

		return true;
	}
}
