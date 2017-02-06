package com.boxfox.dms.mapper;


import com.boxfox.dms.board.dto.FacilityReportContext;

public interface FacilityMapper extends BoardMapper<FacilityReportContext>{

	public void setResult(FacilityReportContext post);
	public void editResult(FacilityReportContext post);

	
}
