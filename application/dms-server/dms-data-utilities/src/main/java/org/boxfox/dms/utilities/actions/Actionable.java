package org.boxfox.dms.utilities.actions;

import java.sql.SQLException;

import org.boxfox.dms.utilities.json.EasyJsonObject;

public interface Actionable {
	public abstract EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException;
}
