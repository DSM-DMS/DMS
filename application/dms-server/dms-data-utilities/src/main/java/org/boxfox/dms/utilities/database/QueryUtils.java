package org.boxfox.dms.utilities.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class QueryUtils {
	public static String queryBuilder(Object... args) {
		/*
		 * This method is accessible anywhere.
		 * 
		 * 1. For example when without queryBuilder String query =
		 * "select * from user id='" + id + "'";
		 * 
		 * 2. for example when use queryBuilder String query =
		 * DataBase.queryBuilder("select * from user where id='",id,"'");
		 */

		StringBuilder builder = new StringBuilder();
		// scratch variable
		for (Object arg : args) {
			if(arg == null) {
				builder.append("null");
			} else if (arg instanceof DataSaveAble) {
				builder.append(((DataSaveAble) arg).toQuery());
			} else {
				builder.append(arg.toString());
			}
		}
		return builder.toString();
	}

	public static String queryCreateDate(int year, int month, int day) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.of(year, month, day);
		return dateFormat.format(date);
	}
	
	public static String queryCreateDate(int year, int month, int day, int hour, int minute, int second) {
		return queryCreateDate(year,month,day) +String.format(" %02d-%02d-%02d",hour,minute,second);
	}

	public static String querySetter(String format, Object... args) {
		StringBuilder builder = new StringBuilder();
		for (Object arg : args) {
			String argStr;
			if (arg instanceof String || arg instanceof JSONArray || arg instanceof JSONObject) {
				argStr = "'" + arg.toString() + "'";
				if (checkDate(arg.toString()))
					argStr = "str_to_date(" + argStr + ", '%Y-%m-%d %H:%i:%s')";
			} else {
				argStr = arg.toString();
			}
			if (format.contains("?"))
				format = format.replaceFirst("[?]", argStr);
			else {
				builder.append(" ");
				builder.append(argStr);
			}
		}
		return format + builder.toString();
	}

	public static boolean checkDate(String str) {
		boolean dateValidity = true;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA); // 20041102101244
		df.setLenient(false);
		try {
			Date dt = df.parse(str);
		} catch (ParseException | IllegalArgumentException pe) {
			dateValidity = false;
		}
		return dateValidity;
	}

	/*
	 * //should I make this? public static BoxFoxResultSet
	 * fixResultSet(ResultSet rs){ try { int max =
	 * rs.getMetaData().getColumnCount(); String [] columns = new String[max];
	 * for(int i = 0 ; i < max ; i ++){ } } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 */
}
