package com.boxfox.dms.users.dao;

import javax.servlet.http.Cookie;

public interface UserDAO {
	public String login(String id, String password, String recapchaResponse);
	public String rename(String id, String newId, String password, String recapchaResponse);
	public String modifyPassword(String id, String password, String newPassword, String recapchaResponse);
	public boolean checkAdminSession(Cookie [] cookies);
	public boolean checkUserSession(Cookie [] cookies);
}
