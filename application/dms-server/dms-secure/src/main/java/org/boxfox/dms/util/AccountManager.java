package org.boxfox.dms.util;

import java.sql.SQLException;

import io.vertx.ext.web.RoutingContext;

public interface AccountManager {
	boolean login(String id, String password) throws SQLException;
	String createSession();
	boolean isLogined(RoutingContext context);
	String getIdFromSession(RoutingContext context);
	String getSessionKey(String id) throws SQLException;
	boolean registerSession(RoutingContext context, boolean keepLogin, String id);
	boolean checkIdExists(String id);
}
