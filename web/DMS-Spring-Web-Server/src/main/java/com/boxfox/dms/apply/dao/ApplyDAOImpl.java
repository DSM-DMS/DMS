package com.boxfox.dms.apply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.apply.dto.AfterSchoolApplyDTO;
import com.boxfox.dms.apply.dto.ExtensionApplyDTO;
import com.boxfox.dms.apply.dto.ExtensionMapDTO;
import com.boxfox.dms.apply.dto.OutApplyDTO;
import com.boxfox.dms.apply.dto.RewardApplyDTO;
import com.boxfox.dms.apply.dto.StayApplyDTO;
import com.boxfox.dms.mapper.ApplyMapper;

@Repository
public class ApplyDAOImpl implements ApplyDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void extensionApply(String id, int room, int seat) {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		ExtensionApplyDTO apply = new ExtensionApplyDTO(id, room, seat);
		mapper.extensionApply(apply);
	}
	
	@Override
	public void afterschoolApply(String id, int no) {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		AfterSchoolApplyDTO apply = new AfterSchoolApplyDTO(id, no);
		mapper.afterschoolApply(apply);
		
	}

	@Override
	public void rewardscoreApply(String id, String target, String content) {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		RewardApplyDTO apply = new RewardApplyDTO(id, target, content);
		mapper.rewardscoreApply(apply);
	}

	@Override
	public void stayApply(String id, int value, String data) {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		StayApplyDTO apply = new StayApplyDTO(id, value, data);
		mapper.stayApply(apply);
	}

	@Override
	public void goingoutApply(String id, String date, String reason) {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		OutApplyDTO apply = new OutApplyDTO(id, date, reason);
		mapper.goingoutApply(apply);
		
	}

	@Override
	public List<AfterSchoolApplyDTO> lookupAfterSchoolApply(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExtensionApplyDTO lookupExtensionApply(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtensionApplyDTO> lookupExtensionApplyAtRoom(int room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OutApplyDTO> lookupOutApply(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RewardApplyDTO> lookupRewardApply(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StayApplyDTO> lookupStayApply(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExtensionMapDTO> getMapdatas() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
