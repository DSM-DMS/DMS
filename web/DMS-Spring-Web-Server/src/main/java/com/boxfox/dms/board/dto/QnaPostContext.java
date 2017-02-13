package com.boxfox.dms.board.dto;

public class QnaPostContext extends ResponsePostContext{
	private boolean privacy;

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}
}
