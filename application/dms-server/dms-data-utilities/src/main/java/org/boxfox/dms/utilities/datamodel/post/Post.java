package org.boxfox.dms.utilities.datamodel.post;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONObject;

public class Post extends DataSaveAble {
	public static final int CATEGORY_NOTICE = 0;
	public static final int CATEGORY_NEWSLETTER = 1;
	public static final int CATEGORY_COMPETITION = 2;
	private String writer, dateTime, title, content;
	private int number, homePageNumber, category;
	private AttachmentList  fileList;

	public Post(int number,int category, int homePageNumber, String title, String dateTime, String writer, String content,
			AttachmentList fileList) {
		this.number = number;
		this.category = category;
		this.homePageNumber = homePageNumber;
		this.title = title;
		this.dateTime = dateTime;
		this.writer = writer;
		this.content = content;
		this.fileList = fileList;
	}

	public Post() {
	}

	public String getTitle() {
		return title;
	}

	public String getDateTime() {
		return dateTime;
	}

	public String getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toQuery() {
		StringBuilder builder = new StringBuilder();
		builder.append(QueryUtils.querySetter(Query.POST.insertFormat, number, category, homePageNumber, title, writer,
				dateTime, content) + ";");
		builder.append(fileList.toQuery());
		return builder.toString();
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("Number", number);
		obj.put("Category", category);
		obj.put("HomeNumber", homePageNumber);
		obj.put("Title", title);
		obj.put("Writer", writer);
		obj.put("Date", dateTime);
		obj.put("Content", content);
		obj.put("Attachments", fileList.toJSONObject());
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(SafeResultSet rs) throws SQLException {
		number = rs.getInt("no");
		category = rs.getInt("category");
		homePageNumber = rs.getInt("number");
		title = rs.getString("title");
		content = rs.getString("content");
		writer = rs.getString("writer");
		dateTime = rs.getString("date");
		fileList = (AttachmentList) new AttachmentList().fromResultSet(DataBase
				.getInstance().executeQuery(QueryUtils.querySetter(Query.ATTACHMENT.selectFormat, number)));
		vaild = true;
		return this;
	}

	public void setTitle(String text) {
		title = text.replaceAll("'", "`");
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public AttachmentList getFileList() {
		return fileList;
	}

	public void setFileList(AttachmentList fileList) {
		this.fileList = fileList;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setWriter(String str) {
		this.writer = str;
	}

}
