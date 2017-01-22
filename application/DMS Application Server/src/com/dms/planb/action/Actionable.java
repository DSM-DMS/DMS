package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public interface Actionable {
	public abstract JSONObject action(int command, JSONObject requestObject) throws JSONException, SQLException;
}
