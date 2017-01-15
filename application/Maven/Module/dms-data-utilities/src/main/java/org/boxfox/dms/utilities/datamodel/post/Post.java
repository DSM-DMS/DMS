package org.boxfox.dms.utilities.datamodel.post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.QueryUtills;
import org.json.simple.JSONObject;

public class Post extends DataSaveAble{
	private String writer, dateTime, title, realtitle, content, img, url;
	private int num;
	private String fileLinks;
	private String fileNames;
	
	public Post(String title, String dateTime, String writer, String content){
		this.title = title;
		this.dateTime = dateTime;
		this.writer = writer;
		this.content = content;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject toJSONObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException{
		title = rs.getString("title");
		content = rs.getString("content");
		writer = rs.getString("writer");
		Timestamp ts = rs.getTimestamp("date");
		dateTime = QueryUtills.queryBuilder(ts.getYear(),".",ts.getMonth(),".",ts.getDay()," ",ts.getHours(),":",ts.getMinutes());
		return this;
	}
	public void setTitle(String text) {
		title = text.replaceAll("'", "`");
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setWriter(String str) {
		this.writer = str;
	}

	public void setUrl(String str) {
		if(str.startsWith("/")){
			str = str.substring(1,str.length());
		}
		this.url = link + str;
	}

	public String getUrl() {
		return url;
	}

	public String getFileNames() {
		return fileNames;
	}

	public String getFileLinks() {
		return fileLinks;
	}

}
