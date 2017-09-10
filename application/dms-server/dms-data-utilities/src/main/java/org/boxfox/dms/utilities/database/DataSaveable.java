package org.boxfox.dms.utilities.database;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public abstract class DataSaveable {
	
	protected boolean vaild;
	{
		vaild = true;
	}

	public abstract String toQuery();

	public abstract JSONObject toJSONObject();

	public abstract DataSaveable fromResultSet(SafeResultSet rs) throws SQLException;

	public boolean isVaild() {
		return vaild;
	}

	protected Object tryJsonParse(SafeResultSet rs, String key)throws SQLException {
		Object resultVal = null;
			resultVal = JSONValue.parse(rs.getString(key));
		return resultVal;
	}
	
	@Override
	public String toString(){
		return toJSONObject().toJSONString();
	}

}
