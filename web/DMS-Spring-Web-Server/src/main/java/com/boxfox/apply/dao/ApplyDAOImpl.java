package com.boxfox.apply.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.boxfox.apply.dto.ExtensionStudyDTO;
import com.boxfox.dms.mapper.ApplyMapper;


public class ApplyDAOImpl implements ApplyDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void applyExtension(String id, int room, int seat) {
		ApplyMapper applyMapper = sqlSession.getMapper(ApplyMapper.class);
		ExtensionStudyDTO apply = new ExtensionStudyDTO();
		apply.setId(id);
		apply.setRoom(room);
		apply.setSeat(seat);
		applyMapper.applyExtension(apply);
	}

}
