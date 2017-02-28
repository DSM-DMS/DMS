package com.boxfox.dms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.QnaPostContext;

public interface QnaMapper extends BoardMapper<QnaPostContext> {
	public void writeAnswer(QnaPostContext post);
	public void writeComment(Comment comment);
	public void editComment(Comment comment);
	public List<Comment> getComments(@Param("no") int no);
}
