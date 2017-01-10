package com.boxfox.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.boxfox.logging.Log;

public class DataBase {
	private static final String DB_TARGET = "localhost/dsm_dms";
	private static final String DB_ID = "";
	private static final String DB_PASSWORD = "";
	private static DataBase instance;
	private Statement st;
	private Connection con;

	private DataBase() {
		accept();
	}

	private boolean accept() {
		try {
			if (con == null || con.isClosed()) {
				con = DriverManager.getConnection("jdbc:mysql://" + DB_TARGET, DB_ID, DB_PASSWORD);
				st = con.createStatement();
				Log.l("DataBase connect success!");
			}
			return true;
		} catch (SQLException sqex) {
			Log.e(sqex.toString());
		}
		return false;
	}

	public String queryBuilder(Object... args) {
		StringBuilder builder = new StringBuilder();
		for (Object str : args) {
			builder.append((String) str);
		}
		return builder.toString();
	}

	public static DataBase getInstance() {
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	public boolean execute(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return st.execute(query);
	}

	public int executeUpdate(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return st.executeUpdate(query);
	}

	public ResultSet executeQuery(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return st.executeQuery(query);
	}
	
	public ResultSet getResultSet() throws SQLException{
		return st.getResultSet();
	}
}
