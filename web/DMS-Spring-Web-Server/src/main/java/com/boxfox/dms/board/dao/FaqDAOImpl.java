package com.boxfox.dms.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.board.dto.PrimaryPostContext;
import com.boxfox.dms.mapper.FacilityMapper;
import com.boxfox.dms.mapper.FaqMapper;

@Repository
public class FaqDAOImpl implements FaqDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PrimaryPostContext getPost(int number) {
		FaqMapper faqMapper = sqlSession.getMapper(FaqMapper.class);
		List<PrimaryPostContext> list = faqMapper.getPost(number);
		PrimaryPostContext post = null;
		if (list.size() > 0) {
			post = list.get(0);
		}
		return post;
	}

	@Override
	public void writePost(String title, String content) {
		FaqMapper faqMapper = sqlSession.getMapper(FaqMapper.class);
		PrimaryPostContext post = new PrimaryPostContext();
		post.setTitle(title);
		post.setContent(content);
		faqMapper.writePost(post);
	}

	@Override
	public void editPost(int no, String title, String content) {
		FaqMapper faqMapper = sqlSession.getMapper(FaqMapper.class);
		PrimaryPostContext post = new PrimaryPostContext();
		post.setNo(no);
		post.setTitle(title);
		post.setContent(content);
		faqMapper.editPost(post);
	}
	
	@Override
	public int deletePost(int number) {
		FaqMapper faqMapper = sqlSession.getMapper(FaqMapper.class);
		return faqMapper.deletePost(number);
	}
}
