package com.dms.utilities.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dms.utilities.log.Log;
import com.dms.utilities.support.SecureConfig;

public class DB {
	private static Connection connection;
	
	private static final String DB_TARGET = SecureConfig.get("DB_HOST")+":"+SecureConfig.get("DB_PORT")+"/"+SecureConfig.get("DB_NAME");
	private static final String DB_ATTRIBUTE = "?allowMultiQueries=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	private static final String DB_ID = SecureConfig.get("DB_ID");
	private static final String DB_PW = SecureConfig.get("DB_PW");
	
	static {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + DB_TARGET + DB_ATTRIBUTE, DB_ID, DB_PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized static PreparedStatement buildQuery(String sql, Object... args) {
		Log.l(sql);
		
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			int placeholderCount = 1;
			for(Object o: args) {
				statement.setObject(placeholderCount++, o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return statement;
	}
	
	public synchronized static ResultSet executeQuery(String sql, Object... args) {
		try {
			return buildQuery(sql, args).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public synchronized static int executeUpdate(String sql, Object... args) {
		try {
			return buildQuery(sql, args).executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
