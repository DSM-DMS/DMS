package com.boxfox.dms.board.dto;

public class ResponsePostContext extends DatePostContext{
	private String result;
	private String resultDate;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultDate() {
		return resultDate;
	}

	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
}
