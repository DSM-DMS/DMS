package com.boxfox.dms.board.dao;

import com.boxfox.dms.board.dto.QnaPostContext;

public interface QnaDAO extends BoardDAO<QnaPostContext>{
	public void writePost(String title, String content, String writer, boolean privacy);
	public void writeAnswer(int no, String content);
	public void writeComment(int no, String content, String writer);
}
