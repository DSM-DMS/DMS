package com.boxfox.dms.apply.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public JSONArray getMapdatas() {
		ApplyMapper mapper = sqlSession.getMapper(ApplyMapper.class);
		List<ExtensionMapDTO> list = mapper.getMapdatas();
		JSONArray arr = new JSONArray();
		for(ExtensionMapDTO map : list)
			arr.add(map.toJSONObject());
		System.out.println(arr.size());
		process(mapper, arr);
		return arr;
	}
	
	private void process(ApplyMapper mapper, JSONArray roomArr) {
		for (int r = 0; r < roomArr.size(); r++) {
			HashMap<Integer, String> map = mapper.getSeatDatas((int)((JSONObject)roomArr.get(r)).get("Room"));
			JSONArray arr = (JSONArray)((JSONObject)roomArr.get(r)).get("Data");
			int count = 1;
			for (int i = 0; i < arr.size(); i++) {
				JSONArray row = (JSONArray)arr.get(i);
				for (int k = 0; k < row.size(); k++) {
					if (((long)row.get(k)) == 1) {
						row.remove(k);
						if (map.get(count) != null) {
							row.add(k, map.get(count));
						} else {
							row.add(k, count);
						}
						count++;
					}
				}
			}
		}
	}
	

}
