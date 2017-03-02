package com.boxfox.dms.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boxfox.dms.apply.dto.*;

public interface ApplyMapper {

	//public void lookupExtensionApply(@Param("id") String id);

	public void extensionApply(ExtensionApplyDTO apply);

	public void afterschoolApply(AfterSchoolApplyDTO apply);

	public void rewardscoreApply(RewardApplyDTO apply);

	public void stayApply(StayApplyDTO apply);

	public void goingoutApply(OutApplyDTO apply);

	public List<AfterSchoolApplyDTO> lookupAfterSchoolApply(@Param("id") String id);

	public ExtensionApplyDTO lookupExtensionApply(@Param("room") Integer id);

	public List<ExtensionApplyDTO> lookupExtensionApplyAtRoom(int room);

	public List<OutApplyDTO> lookupOutApply(@Param("id") String id);

	public List<RewardApplyDTO> lookupRewardApply(@Param("id") String id);

	public List<StayApplyDTO> lookupStayApply(@Param("id") String id);

	public List<ExtensionMapDTO> getMapdatas();
	
	public HashMap<Integer, String> getSeatDatas(@Param("room") int room);

}
