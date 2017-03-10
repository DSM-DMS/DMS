package com.boxfox.dms.board.dao;

import java.util.List;

import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.QnaPostContext;

public interface QnaDAO extends BoardDAO<QnaPostContext>{
	public void writePost(String title, String content, String writer, boolean privacy);
	public void writeAnswer(int no, String content);
	public void writeComment(int no, String content, String writer);
	public void editPost(int no, String title, String content, boolean privacy);
	public void editComment(int no, int number, String content);
	public List<Comment> getComments(int no);
}
