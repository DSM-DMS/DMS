package com.boxfox.dms.board.dto;

public class WriterPostContext extends PrimaryPostContext {
	//title, writer, no, content
	private String writer;

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
}
