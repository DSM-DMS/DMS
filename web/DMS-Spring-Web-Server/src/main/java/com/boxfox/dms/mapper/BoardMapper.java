package com.boxfox.dms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BoardMapper<T> {

	public List<T> getPost(@Param("number")int number);
	public void writePost(T obj);
	public void editPost(T obj);
	public int deletePost(int no);
}
