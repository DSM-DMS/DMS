package org.boxfox.dms.utilities.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.boxfox.dms.utilities.Log;
import static org.boxfox.dms.utilities.database.QueryUtils.queryBuilder;

public class DataBase {
	private static final String DB_TARGET = "localhost:3306/dsm_dms";
	private static final String DB_ATTRIBUTE = "?autoReconnect=true&allowMultiQueries=true";
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
		 * Problems occur when more than one object accesses the database at the
		 * same time. So use singleton pattern.
		 */
		if (instance == null) {
			instance = new DataBase();
		}
		return instance;
	}
	
	public Statement getStatement(){
		return statement;
	}

	private boolean connect() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection("jdbc:mysql://" + DB_TARGET + DB_ATTRIBUTE, DB_ID,
						DB_PASSWORD);
				statement = connection.createStatement();
				Log.l("Database connected successfully!");
			}
			return true;
		} catch (SQLException e) {
			Log.e(e.toString());
		}
		return false;
	}

	public synchronized boolean execute(String query) throws SQLException {
		return statement.execute(query);
	}

	public synchronized int executeUpdate(String query) throws SQLException {
		return statement.executeUpdate(query);
	}

	public synchronized ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	public synchronized boolean execute(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.execute(query);
	}

	public synchronized int executeUpdate(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.executeUpdate(query);
	}

	public synchronized ResultSet executeQuery(Object... args) throws SQLException {
		String query = queryBuilder(args);
		return statement.executeQuery(query);
	}

	public synchronized ResultSet getResultSet() throws SQLException {
		return statement.getResultSet();
	}

	public int executeUpdate(DataSaveAble data) throws SQLException {
		return executeUpdate(data.toQuery());
	}

}
