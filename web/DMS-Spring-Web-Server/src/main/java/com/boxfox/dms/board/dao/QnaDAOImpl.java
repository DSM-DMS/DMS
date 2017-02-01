package com.boxfox.dms.board.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.QnaPostContext;
import com.boxfox.dms.mapper.QnaMapper;

@Repository
public class QnaDAOImpl implements QnaDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<QnaPostContext> getPostsAtPage(int page) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		List<QnaPostContext> list = qnaMapper.getPostsAtPage(page);
		return list;
	}

	@Override
	public QnaPostContext getPost(int number) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		List<QnaPostContext> list = qnaMapper.getPost(number);
		QnaPostContext post = null;
		if(list.size()==0)
			post = list.get(0);
		return post;
	}

	@Override
	public void writePost(String title, String content, String writer, boolean privacy) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		QnaPostContext post = new QnaPostContext();
		post.setTitle(title);
		post.setContent(content);
		post.setPrivacy(privacy);
		post.setWriter(writer);
		qnaMapper.writePost(post);
	}

	@Override
	public void writeAnswer(int no, String content) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		QnaPostContext post = new QnaPostContext();
		Calendar c = Calendar.getInstance();
		String date = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
		post.setNo(no);
		post.setResult(content);
		post.setDate(date);
		qnaMapper.writeAnswer(post);
	}

	@Override
	public void writeComment(int no, String content, String writer) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		Comment comment = new Comment();
		Calendar c = Calendar.getInstance();
		String date = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
		comment.setNo(no);
		comment.setContent(content);
		comment.setWriter(writer);
		comment.setDate(date);
		qnaMapper.writeComment(comment);
	}

	@Override
	public void editPost(int no, String title, String content, boolean privacy) {
		QnaMapper qnaMapper = sqlSession.getMapper(QnaMapper.class);
		QnaPostContext post = new QnaPostContext();
		post.setNo(no);
		post.setContent(content);
		post.setTitle(title);
		post.setPrivacy(privacy);
		qnaMapper.editPost(post);
	}

	@Override
	public void editAnswer(int no, String content) {
		
	}

	@Override
	public void editComment(int no, int number, String content) {
		// TODO Auto-generated method stub
		
	}
	
	

}
