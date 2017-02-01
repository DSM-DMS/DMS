package com.boxfox.dms.mapper;

import com.boxfox.dms.board.dto.PrimaryPostContext;

public interface RuleMapper extends BoardMapper<PrimaryPostContext> {
	public void writePost(PrimaryPostContext post);
}
