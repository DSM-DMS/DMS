package org.boxfox.dms.util;

import java.sql.SQLException;

import io.vertx.ext.web.RoutingContext;

public interface AccountManageable {
	public boolean login(String id, String password) throws SQLException;
	public String createSession();
	public boolean isLogined(RoutingContext context);
	public String getIdFromSession(RoutingContext context);
	public String getSessionKey(String id) throws SQLException;
	public boolean registerSession(RoutingContext context, boolean keepLogin, String id);
}
