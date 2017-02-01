package com.boxfox.dms.board.dao;

import com.boxfox.dms.board.dto.FacilityReportContext;

public interface FacilityDAO extends BoardDAO<FacilityReportContext> {
	
	public void writePost(String title, String content, int room, String writer);
	public void setResult(int num, String result);
	public void editPost(int no, String title, String content, int room);
	public void editResult(int no, String result);

}
