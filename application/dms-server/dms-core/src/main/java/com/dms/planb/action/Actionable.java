package com.dms.planb.action;

import java.sql.SQLException;

import org.json.simple.JSONObject;

public interface Actionable {
	public abstract JSONObject action(int command, JSONObject requestObject) throws SQLException;
}
