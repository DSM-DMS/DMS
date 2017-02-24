package com.boxfox.dms.users.dao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserDAO {
	public String login(HttpServletRequest request, HttpServletResponse response, String id, String password, boolean autoLogin, String recapchaResponse);
	public String loginAdmin(HttpServletRequest request, HttpServletResponse response, String id, String password, boolean autoLogin, String recapchaResponse);
	public String rename(String id, String newId, String password, String recapchaResponse);
	public String modifyPassword(String id, String password, String newPassword, String recapchaResponse);
	public boolean checkAdminSession(HttpServletRequest request);
	public boolean checkUserSession(HttpServletRequest request);
	public String getIdBySession(HttpServletRequest request);
}
