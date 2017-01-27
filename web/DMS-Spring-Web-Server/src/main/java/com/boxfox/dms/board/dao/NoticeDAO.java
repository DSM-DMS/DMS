package com.boxfox.dms.board.dao;

import java.util.List;

import com.boxfox.dms.board.dto.DatePostContext;

public interface NoticeDAO {
	
	public List<DatePostContext> getPostsAtPage(int page);
	public DatePostContext getPost(int number);
	
}
