package com.boxfox.dms.apply.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class ExtensionMapDTO {
	private int room;
	private String name, map;

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSONArray getMap() {
		return (JSONArray)JSONValue.parse(map);
	}

	public void setMap(String map) {
		this.map = map;
	}

}
