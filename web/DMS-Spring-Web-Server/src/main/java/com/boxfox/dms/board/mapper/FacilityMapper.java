package com.boxfox.dms.board.mapper;


import com.boxfox.dms.board.dto.FacilityReportContext;

public interface FacilityMapper extends BoardMapper<FacilityReportContext>{

	public void setResult(FacilityReportContext post);
	
}
