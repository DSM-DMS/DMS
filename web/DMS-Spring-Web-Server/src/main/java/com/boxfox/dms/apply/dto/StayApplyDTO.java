package com.boxfox.dms.apply.dto;

public class StayApplyDTO {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private int value;
	private String data;

	public StayApplyDTO(String id, int value, String data) {
		this.id = id;
		this.value = value;
		this.data = data;
	}

	public StayApplyDTO() {
	}

}
