package com.dms.support;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.SafeResultSet;

public class Afterschool {
	static DataBase database;
	static SafeResultSet resultSet;
	static SafeResultSet tempResultSet;

	static boolean[] appliedDate = new boolean[4];
	static boolean[] targetDate = new boolean[4];
	/*
	 * 0 : Monday
	 * 1 : Tuesday
	 * 2 : Wednesday
	 * 3 : Saturday
	 */

	public static boolean canApply(String uid, int no) throws SQLException {
		database = DataBase.getInstance();
				
		resultSet = database.executeQuery("SELECT * FROM afterschool_list WHERE no=", no);
		/*
		 * Target
		 */
		
		resultSet.next();
		
		targetDate[0] = resultSet.getBoolean("on_monday");
		targetDate[1] = resultSet.getBoolean("on_tuesday");
		targetDate[2] = resultSet.getBoolean("on_wednesday");
		targetDate[3] = resultSet.getBoolean("on_saturday");
		/*
		 * Target subject's date of course
		 */

		resultSet = database.executeQuery("SELECT * FROM afterschool_apply WHERE uid='", uid, "'");

		if (resultSet.next()) {
			/*
			 * Applier's apply status
			 */
			
			tempResultSet = database.executeQuery("SELECT * FROM afterschool_list WHERE no=", resultSet.getInt("no"));
			/*
			 * Applier's applied date
			 */
			
			do {
				if (tempResultSet.getBoolean("on_monday")) {
					appliedDate[0] = true;
				}

				if (tempResultSet.getBoolean("on_tuesday")) {
					appliedDate[1] = true;
				}

				if (tempResultSet.getBoolean("on_wednesday")) {
					appliedDate[2] = true;
				}

				if (tempResultSet.getBoolean("on_saturday")) {
					appliedDate[3] = true;
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
			if (targetDate[i] && !appliedDate[i]) {
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
