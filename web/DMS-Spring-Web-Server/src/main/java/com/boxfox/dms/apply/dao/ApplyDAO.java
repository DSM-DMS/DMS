package com.boxfox.dms.apply.dao;

import java.util.List;

import com.boxfox.dms.apply.dto.*;

public interface ApplyDAO {

	public void extensionApply(String id, int room, int seat);

	public void afterschoolApply(String id, int no);

	public void rewardscoreApply(String id, String target, String content);

	public void stayApply(String id, int value, String data);

	public void goingoutApply(String id, String date, String reason);

	public List<AfterSchoolApplyDTO> lookupAfterSchoolApply(String id);

	public ExtensionApplyDTO lookupExtensionApply(String id);

	public List<ExtensionApplyDTO> lookupExtensionApplyAtRoom(int room);
	
	public List<OutApplyDTO> lookupOutApply(String id);
	
	public List<RewardApplyDTO> lookupRewardApply(String id);
	
	public List<StayApplyDTO> lookupStayApply(String id);

}
