package com.boxfox.dms.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.board.mapper.NoticeMapper;

@Repository
public class NoticeDAOImpl implements NoticeDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<DatePostContext> getPostsAtPage(int page) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		List<DatePostContext> list = noticeMapper.getPostsAtPage(page);
		return list;
	}

	@Override
	public DatePostContext getPost(int number) {
		NoticeMapper noticeMapper = sqlSession.getMapper(NoticeMapper.class);
		DatePostContext post = null;
		List<DatePostContext> posts = noticeMapper.getPost(number*10);
		if(posts.size()>0)
			post = posts.get(0);
		return post;
	}

}
