package com.boxfox.dms.mapper;

import java.util.List;

<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;

=======
>>>>>>> b6c155ce82904b658dc26ff5549ee43e053039e3
import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.QnaPostContext;

public interface QnaMapper extends BoardMapper<QnaPostContext> {
	public void writeAnswer(QnaPostContext post);
	public void writeComment(Comment comment);
	public void editAnswer(QnaPostContext post);
	public void editComment(Comment comment);
<<<<<<< HEAD
	public List<Comment> getComments(@Param("no") int no);
=======
	public List<Comment> getComments(int no);
>>>>>>> b6c155ce82904b658dc26ff5549ee43e053039e3
}
