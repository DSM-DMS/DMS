package org.boxfox.dms.utilities.database;

public class QueryUtills {
	
	public static String queryBuilder(Object... args) {
		/*
		 * This method is accessible anywhere.
		 * 
		 * 1. For example when without queryBuilder
		 * String query = "select * from user id='" + id + "'";
		 * 
		 * 2. for example when use queryBuilder
		 * String query = DataBase.queryBuilder("select * from user where id='",id,"'");
		 */
		
		StringBuilder s = new StringBuilder();
		// scratch variable
		for (Object str : args) {
			s.append(str.toString());
		}
		return s.toString();
	}
	
	public static String queryCreateDate(int year, int month, int day){
		StringBuilder  builder = new StringBuilder();
		builder.append(year);
		builder.append("-");
		builder.append(month);
		builder.append("-");
		builder.append(year);
		return builder.toString();
	}
	
	public static String querySetter(String format, Object ... args){
		for(Object arg : args){
			String argStr;
			if(arg instanceof String){
				argStr="'"+arg.toString()+"'";
			}else{
				argStr = arg.toString();
			}
			format = format.replaceFirst("[?]", argStr);
		}
		return format;
	}
	
	public static String columnTargetCreater(String ... targets){
		StringBuilder builder = new StringBuilder();
		for(int i = 0 ; i<targets.length-1 ; i++){
			builder.append(targets[i]);
			builder.append(",");
		}
		builder.append(targets[targets.length-1]);
		return builder.toString();
	}
	
}
