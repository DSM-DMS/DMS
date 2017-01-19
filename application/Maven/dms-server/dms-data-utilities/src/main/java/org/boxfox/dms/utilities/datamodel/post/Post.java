package org.boxfox.dms.utilities.datamodel.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Post extends DataSaveAble{
	public static int CATEGORY_BROAD = 0;
	public static int CATEGORY_FAMILER = 1;
	public static int CATEGORY_CHALLENGE = 2;
	private String writer, dateTime, title, content;
	private int number, homePageNumber;
	private AttachmentList<Attachment> fileList;
	
	public Post(int number,int homePageNumber, String title, String dateTime, String writer, String content, AttachmentList<Attachment> fileList){
		this.number = number;
		this.homePageNumber = homePageNumber;
		this.title = title;
		this.dateTime = dateTime;
		this.writer = writer;
		this.content = content;
		this.fileList = fileList;
	}

	public Post() {
		// TODO Auto-generated constructor stub
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
		builder.append(QueryUtils.querySetter(Query.POST.insertFormat,number, homePageNumber, title, writer, dateTime, content)+";");
		builder.append(fileList.toQuery());
		return builder.toString();
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("Number", number);
		obj.put("Title", title);
		obj.put("Writer", writer);
		obj.put("Content", content);
		obj.put("Attachments", fileList.toJSONObject());
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException{
		number = rs.getInt("no");
		homePageNumber = rs.getInt("number");
		title = rs.getString("title");
		content = rs.getString("content");
		writer = rs.getString("writer");
		dateTime = rs.getString("date");
		fileList = (AttachmentList<Attachment>) new AttachmentList<Attachment>(Attachment.class).fromResultSet(DataBase.getInstance().executeQuery(QueryUtils.querySetter(Query.ATTACHMENT.selectFormat, "*", number)));
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

	public AttachmentList<Attachment> getFileList() {
		return fileList;
	}

	public void setFileList(AttachmentList<Attachment> fileList) {
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
