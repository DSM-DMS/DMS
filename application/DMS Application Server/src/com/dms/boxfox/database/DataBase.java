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
		accept();
	}
	
	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	private boolean accept() {
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
	/*
	 * 1. For example when without queryBuilder
	 * String query = "select * from user id='" + id + "'";
	 * 
	 * 2. for example when use queryBuilder
	 * String query = DataBase.queryBuilder("select * from user where id='",id,"'");
	 * */
	public static String queryBuilder(Object... args) {
		StringBuilder s = new StringBuilder();
		// scratch variable
		for (Object str : args) {
			s.append((String) str);
		}
		return s.toString();
	}

	public boolean execute(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.execute(query);
	}

	public int executeUpdate(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.executeUpdate(query);
	}

	public ResultSet executeQuery(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.executeQuery(query);
	}

	public ResultSet getResultSet() throws SQLException {
		return statement.getResultSet();
	}
}
