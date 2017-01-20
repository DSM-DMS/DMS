package org.boxfox.dms.utilities.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import static java.sql.Types.*;

public class SafeResultSet {
	private static final String OFFSET_OVERFLOW = "Is not have anymore row";
	private static final String INVALID_COLUMN = "Not valid column : ";
	private static final String INVALID_TYPE = "Is not valid type";
	private static final String OUT_OF_INDEX = "Out of range Index : ";

	private int offset;
	private ArrayList<String> columns;
	private ArrayList<Row> rows;
	private HashMap<String, Integer> columnTypes;

	public SafeResultSet(ResultSet rs) throws SQLException {
		this.offset = -1;
		this.columnTypes = new HashMap<String, Integer>();
		this.columns = new ArrayList<String>();
		this.rows = new ArrayList<Row>();
		for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
			columns.add(rs.getMetaData().getColumnLabel(i));
			columnTypes.put(rs.getMetaData().getColumnLabel(i), rs.getMetaData().getColumnType(i));
		}
		while (rs.next()) {
			rows.add(new Row(rs));
		}
	}

	public SafeResultSet nextAndReturn() throws SQLException {
		offset++;
		if (offset >= rows.size()) {
			throw new SQLException(OFFSET_OVERFLOW);
		}
		return this;
	}

	public boolean next() {
		boolean result = false;
		offset++;
		if (offset < rows.size()) {
			result = true;
		}
		return result;
	}

	public int getColumnSize() {
		return columns.size();
	}

	public int getInt(String label) throws SQLException {
		return getInt(getIndexByLabel(label));
	}

	public int getInt(int index) throws SQLException {
		try{
			return Integer.valueOf(getObject(index).toString());
		}catch(NumberFormatException e){
			throw new SQLException(INVALID_TYPE);
		}
	}

	public String getString(String label) throws SQLException {
		return getString(getIndexByLabel(label));
	}

	public String getString(int index) throws SQLException {
		checkVaildIndex(index);
		return getObject(index).toString();
	}

	public int getIndexByLabel(String label) throws SQLException {
		if (columns.indexOf(label) < 0){
			for(String col : columns)
				System.out.println(col);
			throw new SQLException(INVALID_COLUMN + label);
		}
		return columns.indexOf(label) + 1;
	}

	private void checkVaildIndex(int index) throws SQLException {
		if (index-1 >= getColumnSize()) {
			throw new SQLException(OUT_OF_INDEX + index);
		}
	}

	public Object getObject(int index) {
		return rows.get(offset).get(index);
	}

	private class Row {
		private Object[] datas;

		public Row(ResultSet rs) throws SQLException {
			datas = new Object[getColumnSize()];
			for (int i = 1; i <= getColumnSize(); i++) {
				datas[i - 1] = rs.getObject(i);
			}
		}

		public Object get(int index) {
			return datas[index - 1];
		}
	}

}
