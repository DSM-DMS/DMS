package com.boxfox.dms.apply.dto;

public class ExtensionApplyDTO {
	private String id;
	private int room;
	private int seat;

	public ExtensionApplyDTO() {
	}

	public ExtensionApplyDTO(String id, int room, int seat) {
		this.id = id;
		this.room = room;
		this.seat = seat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

}