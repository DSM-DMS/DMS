package com.boxfox.dms.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boxfox.dms.board.dto.DatePostContext;

public interface NoticeMapper {

	public List<DatePostContext> getPostsAtPage(@Param("page")int page);
	public List<DatePostContext> getPost(@Param("number")int number);
}
