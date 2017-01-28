package com.boxfox.dms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BoardMapper<T> {

	public List<T> getPostsAtPage(@Param("page")int page);
	public List<T> getPost(@Param("number")int number);
}
