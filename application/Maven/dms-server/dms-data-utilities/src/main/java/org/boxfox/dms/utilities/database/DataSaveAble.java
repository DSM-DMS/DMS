package org.boxfox.dms.utilities.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public abstract class DataSaveAble {
	
	protected boolean vaild;
	{
		vaild = true;
	}

	public abstract String toQuery();

	public abstract JSONObject toJSONObject();

	public abstract DataSaveAble fromResultSet(ResultSet rs) throws SQLException;

	public boolean isVaild() {
		return vaild;
	}

	protected Object tryJsonParse(ResultSet rs, String key) {
		Object resultVal = null;
		try {
			resultVal = JSONValue.parse(rs.getString(key));
		} catch (SQLException e) {
			vaild = false;
		}
		return resultVal;
	}

}
