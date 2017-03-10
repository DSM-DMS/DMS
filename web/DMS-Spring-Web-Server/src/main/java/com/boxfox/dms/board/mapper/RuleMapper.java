package com.boxfox.dms.board.mapper;

import com.boxfox.dms.board.dto.PrimaryPostContext;

public interface RuleMapper extends BoardMapper<PrimaryPostContext> {
	public void writePost(PrimaryPostContext post);
}
