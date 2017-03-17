package org.boxfox.dms.utilities.actions.support;

import java.util.Calendar;

public class ApplyDataUtil {
    //this class change to .config file later
    public static String EXTENSION_APPLY_TIME = "17:40";

    public static boolean canApplyStay(String week) {
        Calendar calendar = Calendar.getInstance();
        int weekInMonth = Integer.parseInt(week.split("-")[2]);
        if (weekInMonth == calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH)) {
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

        int setHour = Integer.valueOf(ApplyDataUtil.EXTENSION_APPLY_TIME.split(":")[0]);
        int setMinute = Integer.valueOf(ApplyDataUtil.EXTENSION_APPLY_TIME.split(":")[1]);
        if (!(hour < setHour || (hour == setHour && minute < setMinute))) {
            check = true;
        }
        return check;
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
