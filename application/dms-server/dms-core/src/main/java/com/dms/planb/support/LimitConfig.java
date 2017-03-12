package com.dms.planb.support;

import java.util.Calendar;

public class LimitConfig {
	//this class change to .config file later
	public static String EXTENSION_APPLY_TIME = "17:40";
	
	public static boolean canApplyStay(String week) {
		Calendar calendar = Calendar.getInstance();
		int weekInMonth = Integer.parseInt(week.split("-")[2]);
		if(weekInMonth == calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
			// Current week
			int today = calendar.get(Calendar.DAY_OF_WEEK);
			int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
			int currentMinute = calendar.get(Calendar.MINUTE);
			if((Calendar.MONDAY <= today) && (today <= Calendar.THURSDAY)) {
				// Between Monday and Thursday
				if(today == Calendar.THURSDAY) {
					if(currentHour < 20 || (currentHour == 20 && currentMinute <= 30)) {
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
}
