package com.dms.utilities.support;

import java.util.Calendar;

public class ApplyDataUtil {
    //this class change to .config file later
	private static String EXTENSION_APPLY_START = "17:30";
	private static String EXTENSION_APPLY_LIMIT = "20:30";

    public static boolean canApplyStay(String week) {
        Calendar calendar = Calendar.getInstance();
        int month = Integer.parseInt(week.split("-")[1]);
        int weekInMonth = Integer.parseInt(week.split("-")[2]);
        
        if (month == calendar.get(Calendar.MONTH) + 1 &&
        	weekInMonth == calendar.get(Calendar.WEEK_OF_MONTH)) {
            return canApplyStay();
        } else {
            return true;
        }
    }

    public static boolean canApplyStay() {
        boolean check = false;
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        if ((Calendar.MONDAY <= today) && (today <= Calendar.THURSDAY)) {
            // Between Monday and Thursday
            if (today == Calendar.THURSDAY) {
                if (currentHour < 20 || (currentHour == 20 && currentMinute <= 30)) {
                    check = true;
                } else {
                    check = false;
                }
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    public static boolean canApplyExtension() {
        boolean check = false;
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        int setHourStart = Integer.valueOf(EXTENSION_APPLY_START.split(":")[0]);
        int setMinuteStart = Integer.valueOf(EXTENSION_APPLY_START.split(":")[1]);
        int setHourLimit = Integer.valueOf(EXTENSION_APPLY_LIMIT.split(":")[0]);
        int setMinuteLimit = Integer.valueOf(EXTENSION_APPLY_LIMIT.split(":")[1]);
        
        if((hour > setHourStart || (hour == setHourStart && minute > setMinuteStart)) && (hour < setHourLimit || (hour == setHourLimit && minute < setMinuteLimit))) {
        	check = true;
        }
        
        return check;
    }

    public static boolean warningStayApply() {
        Calendar currentTime = Calendar.getInstance();
        if (currentTime.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            return true;
        return false;
    }

    public static String stayDataToString(int num) {
        String str = "";
        switch (num) {
            case 1:
                str = "금요귀가";
                break;
            case 2:
                str = "토요귀가";
                break;
            case 3:
                str = "토요귀사";
                break;
            case 4:
                str = "잔류";
        }
        return str;
    }

    public static boolean canApplyGoingout() {
        return true;
    }
}
