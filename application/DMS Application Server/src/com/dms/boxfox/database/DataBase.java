package com.dms.boxfox.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dms.boxfox.logging.Log;

public class DataBase {
	private static final String DB_TARGET = "localhost:3306/dsm_dms";
	private static final String DB_ID = "";
	private static final String DB_PASSWORD = "";
	private static DataBase instance;
	private Statement statement;
	private Connection connection;

	private DataBase() {
		connect();
	}
	
	public static DataBase getInstance() {
		/*
		 * Problems occur when more than one object accesses the database at the same time.
		 * So use singleton pattern.
		 */
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	private boolean connect() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:mysql://" + DB_TARGET, DB_ID, DB_PASSWORD);
				statement = connection.createStatement();
				Log.l("Database connected successfully!");
			}
			return true;
		} catch (SQLException e) {
			Log.e(e.toString());
		}
		return false;
	}
	
	public static String queryBuilder(Object... args) {
		/*
		 * This method is accessible anywhere.
		 *
		 * When create this query : select * from user id='id'
		 * 
		 * 1. For example when without queryBuilder
		 * String query = "select * from user id='" + id + "'";
		 * 
		 * 2. for example when use queryBuilder
		 * String query = DataBase.queryBuilder("select * from user where id='", id, "'");
		 */
		
		StringBuilder s = new StringBuilder();
		// scratch variable
		for (Object str : args) {
			s.append((String) str);
		}
		return s.toString();
	}

	public synchronized boolean execute(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.execute(query);
	}

	public synchronized int executeUpdate(Object... args) throws SQLException {
		/*
		 * Returns number of rows applied
		 * Uses INSERT, UPDATE, DELETE query
		 */
		String query = queryBuilder(args);
		return statement.executeUpdate(query);
	}

	public synchronized ResultSet executeQuery(Object... args) throws SQLException {
		/*
		 * Method to get ResultSet from result of query
		 * Uses SELECT query
		 */
		String query = queryBuilder(args);
		return statement.executeQuery(query);
	}

	public synchronized ResultSet getResultSet() throws SQLException {
		return statement.getResultSet();
	}
}
