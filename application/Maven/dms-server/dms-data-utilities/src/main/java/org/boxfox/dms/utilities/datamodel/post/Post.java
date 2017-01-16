package org.boxfox.dms.utilities.datamodel.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtills;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Post extends DataSaveAble{
	private String writer, dateTime, title, content;
	private int number;
	private AttachmentList<Attachment> fileList;
	
	public Post(int number, String title, String dateTime, String writer, String content, AttachmentList<Attachment> fileList){
		this.number = number;
		this.title = title;
		this.dateTime = dateTime;
		this.writer = writer;
		this.content = content;
		this.fileList = fileList;
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
		builder.append(QueryUtills.querySetter(Query.POST.insertFormat, title, writer, dateTime, content)+";");
		for(int i = 0 ;i<fileList.size(); i++){
			builder.append(fileList.get(i).toQuery()+";");
		}
		return builder.toString();
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("Title", title);
		obj.put("Writer", writer);
		obj.put("Content", content);
		obj.put("Attachments", fileList.toJSONObject());
		return obj;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException{
		number = rs.getInt("no");
		title = rs.getString("title");
		content = rs.getString("content");
		writer = rs.getString("writer");
		dateTime = rs.getString("date");
		DataBase.getInstance().executeQuery(QueryUtills.querySetter(Query.ATTACHMENT.selectFormat, "*", number));
		return this;
	}
	
	public void setTitle(String text) {
		title = text.replaceAll("'", "`");
	}

	public void setWriter(String str) {
		this.writer = str;
	}

}
