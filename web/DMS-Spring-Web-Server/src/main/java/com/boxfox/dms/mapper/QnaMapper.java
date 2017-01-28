package com.boxfox.dms.mapper;

import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.QnaPostContext;

public interface QnaMapper extends BoardMapper<QnaPostContext> {
	public void writeAnswer(QnaPostContext post);
	public void writeComment(Comment comment);
}
