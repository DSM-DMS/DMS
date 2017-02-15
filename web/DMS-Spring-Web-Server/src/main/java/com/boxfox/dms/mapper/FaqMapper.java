package com.boxfox.dms.mapper;

import com.boxfox.dms.board.dto.FacilityReportContext;
import com.boxfox.dms.board.dto.PrimaryPostContext;

public interface FaqMapper extends BoardMapper<PrimaryPostContext>{
	
	public void writePost(PrimaryPostContext post);
	public void editPost(PrimaryPostContext post);

}
