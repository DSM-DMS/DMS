package com.boxfox.dms.board.dao;

import com.boxfox.dms.board.dto.DatePostContext;

public interface NoticeDAO extends BoardDAO<DatePostContext> {
	public void writePost(String title, String content, String writer);
	public void editPost(int no, String title, String content);
}
