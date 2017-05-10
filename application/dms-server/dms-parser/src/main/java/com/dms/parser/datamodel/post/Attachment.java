package com.dms.parser.datamodel.post;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.database.SafeResultSet;
import org.json.simple.JSONObject;

import com.dms.parser.dataio.Parser;
import com.dms.parser.dataio.Query;

public class Attachment extends DataSaveAble {
	private String name, link;
	private int number;
	
	protected Attachment(){}

	public Attachment(int number, String name, String link) {
		this.name = name;
		this.number = number;
		this.link = Parser.LINK+link;
	}

	@Override
	public String toQuery() {
		return QueryUtils.querySetter(Query.ATTACHMENT.insertFormat, number, name, link)+";";
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("Name", name);
		obj.put("Link", link);
		return obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public DataSaveAble fromResultSet(SafeResultSet rs) throws SQLException {
		this.number = rs.getInt("no");
		this.name = rs.getString("name");
		this.link = rs.getString("link");
		return this;
	}

}
