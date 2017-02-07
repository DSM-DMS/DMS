package com.boxfox.dms.apply.dto;

public class OutApplyDTO {
	private String id;
	private String date;
	private String reason;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public OutApplyDTO() {
	}

	public OutApplyDTO(String id, String date, String reason) {
	}

}
