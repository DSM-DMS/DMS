package org.boxfox.dms.utilities.datamodel.post;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataSaveAble;
import org.boxfox.dms.utilities.database.Query;
import org.boxfox.dms.utilities.database.QueryUtills;
import org.json.simple.JSONObject;

public class Attachment extends DataSaveAble {
	private String name, link;
	private int number;

	public Attachment(int number, String name, String link) {
		this.name = name;
		this.number = number;
		this.link = link;
	}

	@Override
	public String toQuery() {
		return QueryUtills.querySetter(Query.ATTACHMENT.insertFormat, number, name, link);
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
	public DataSaveAble fromResultSet(ResultSet rs) throws SQLException {
		this.number = rs.getInt("no");
		this.name = rs.getString("name");
		this.link = rs.getString("link");
		return this;
	}

}
