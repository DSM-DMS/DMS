package com.boxfox.dms.users.dao;


public interface UserDAO {
	public String login(String id, String password);
	public String rename(String id, String newId, String password);
	public String modifyPassword(String id, String password, String newPassword);
}
