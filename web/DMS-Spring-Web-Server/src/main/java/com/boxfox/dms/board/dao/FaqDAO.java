package com.boxfox.dms.board.dao;

import com.boxfox.dms.board.dto.PrimaryPostContext;

public interface FaqDAO extends BoardDAO<PrimaryPostContext>{
	
	public void writePost(String title, String content);
	public void editPost(int no, String title, String content);

}
