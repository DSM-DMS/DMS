package com.dms.planb.action;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

public interface Action {
	public abstract JSONObject action() throws JSONException, SQLException;
}
