package com.boxfox.dms.mapper;

import org.apache.ibatis.annotations.Param;

import com.boxfox.dms.apply.dto.AfterSchoolApplyDTO;
import com.boxfox.dms.apply.dto.ExtensionApplyDTO;
import com.boxfox.dms.apply.dto.OutApplyDTO;
import com.boxfox.dms.apply.dto.RewardApplyDTO;
import com.boxfox.dms.apply.dto.StayApplyDTO;

public interface ApplyMapper {

	public void extensionApply(ExtensionApplyDTO apply);
	public void afterschoolApply(AfterSchoolApplyDTO apply);
	public void rewardscoreApply(RewardApplyDTO apply);
	public void stayApply(StayApplyDTO apply);
	public void goingoutApply(OutApplyDTO apply);
	public void lookupOutApply(@Param("id")String id);
	public void lookupExtensionApply(@Param("id")String id);
	public void lookupExtensionApplyAtRoom(@Param("room")Integer id);
	public void lookupRewardApply(@Param("id")String id);
	public void lookupStayApply(@Param("id")String id);

}
