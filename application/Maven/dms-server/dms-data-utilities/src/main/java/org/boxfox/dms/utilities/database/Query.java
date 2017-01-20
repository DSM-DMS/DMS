package org.boxfox.dms.utilities.database;

public enum Query {
	MEAL("replace into meal (date, breakfast, lunch, dinner, breakfast_allergy, lunch_allergy, dinner_allergy) values(STR_TO_DATE(?, '%Y-%m-%d'), ?, ?, ?, ?, ?, ? )", "select * from meal where date=?"),
	PLAN("replace into plan (year, month, data) values(?, ?, ?)", "select * from plan where year=? and month=?"),
	POST("replace into app_content (no, category, number, title,writer,date,content) values(?, ?, ?, ?, ?, ?, ?)","select * from app_content"),
	ATTACHMENT("insert into attachments (no, name, link) values(?, ?, ?)", "select * from attachments where no=?");

	public String insertFormat, selectFormat, table;

	Query(String insertFormat, String selectFormat) {
		this.table = insertFormat.split(" ")[2];
		this.insertFormat = insertFormat;
		this.selectFormat = selectFormat;
	}

}
