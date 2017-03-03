package com.boxfox.dms.apply.dto;

public class AfterSchoolApplyDTO {
	private String id;
	private int no;

	public AfterSchoolApplyDTO() {
	}

	public AfterSchoolApplyDTO(String id, int no) {
		this.id = id;
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
