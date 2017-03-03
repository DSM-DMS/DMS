package com.boxfox.dms.board.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.board.mapper.FaqMapper;
import com.boxfox.dms.board.mapper.NoticeMapper;

@Repository
public class NoticeDAOImpl implements NoticeDAO{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public DatePostContext getPost(int number) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		DatePostContext post = null;
		List<DatePostContext> posts = noticeMapper.getPost(number*10);
		if(posts.size()>0)
			post = posts.get(0);
		return post;
	}

	@Override
	public void writePost(String title, String content, String writer) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		Calendar c = Calendar.getInstance();
		String date = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
		DatePostContext post = new DatePostContext();
		post.setDate(date);
		post.setWriter(writer);
		post.setTitle(title);
		post.setWriter(writer);
		noticeMapper.writePost(post);
	}

	@Override
	public void editPost(int no, String title, String content) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		DatePostContext post = new DatePostContext();
		post.setNo(no);
		post.setTitle(title);
		post.setContent(content);
		noticeMapper.editPost(post);
	}
	
	@Override
	public int deletePost(int number) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		return noticeMapper.deletePost(number);
	}

}
