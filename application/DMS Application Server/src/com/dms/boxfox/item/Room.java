package com.dms.boxfox.item;

public class Room {
	private String title, db;
	private int type;

	public Room(String title, String db, int type) {
		this.title = title;
		this.db = db;
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDB(String db) {
		this.db = db;
	}

	public String getTitle() {
		return title;
	}

	public String getDB() {
		return db;
	}

	public int getType() {
		return type;
	}

}