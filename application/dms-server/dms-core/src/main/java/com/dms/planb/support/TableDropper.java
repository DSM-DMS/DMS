package com.dms.planb.support;

import java.sql.SQLException;
import java.util.Calendar;

import org.boxfox.dms.utilities.database.DataBase;

public class TableDropper extends Thread {
	public void run() {
		while(true) {
			Calendar currentTime = Calendar.getInstance();
			int dayOfWeek = currentTime.get(Calendar.DAY_OF_WEEK);
			int hour = currentTime.get(Calendar.HOUR_OF_DAY);
			if (dayOfWeek == Calendar.MONDAY) {
				try {
					DataBase.getInstance().executeUpdate("delete from goingout_apply");
					/*
					 * Every Monday, refresh goingout_apply table
				 	*/
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (hour >= 0 && hour <= 8) {
				try {
					DataBase.getInstance().executeUpdate("delete from extension_apply");
					/*
					 * Every day, refresh extension_apply table
				 	*/
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			Thread.sleep(3600000)
		}
	}
}
