package com.boxfox.dms.board.dao;

import java.util.List;

import com.boxfox.dms.board.dto.DatePostContext;

public interface BoardDAO<T> {
	
	public List<T> getPostsAtPage(int page);
	public T getPost(int number);
	
}
