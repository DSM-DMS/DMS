package com.boxfox.dms.board.dao;

import java.util.List;

import com.boxfox.dms.board.dto.DatePostContext;

public interface BoardDAO<T> {
	
	public T getPost(int number);
	public int deletePost(int number);
	
}
