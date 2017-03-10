package com.boxfox.dms.users.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.mapper.UserMapper;
import com.boxfox.dms.response.JsonBuilder;
import com.boxfox.dms.response.ResponseCode;
import com.boxfox.dms.users.dto.UserDTO;
import com.boxfox.dms.users.dto.UserModifyPasswordDTO;
import com.boxfox.dms.users.dto.UserRenameDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	private static final String ALREDY_EXSIT_ID = "�̹� �����ϴ� ���̵��Դϴ�.";
	private static final String FAIL_LOGIN = "���̵� �� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
	private static final String SUCCESS_LOGIN = "�α��ο� �����߽��ϴ�.";
	private static final String SUCCESS_RENAME = "���̵� ���濡 �����߽��ϴ�.";
	private static final String SUCCESS_MODIFY_PASSWORD = "��й�ȣ ���濡 �����߽��ϴ�.";
	private static final String FAIL_RENAME = "���̵� ���濡 �����߽��ϴ�.";
	private static final String FAIL_MODIFY_PASSWORD = "��й�ȣ ���濡 �����߽��ϴ�.";
	private static final String FAIL_RECAPCHA = "��ĸ���� Ȯ���� �ּ���";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public String login(HttpServletRequest request, HttpServletResponse response, String id, String password,
			boolean autoLogin, String recapchResponse) {
		int code = 200;
		String msg = SUCCESS_LOGIN;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = 400;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserDTO user = new UserDTO(id, password);
			String result = userMapper.login(user);
			if (result != null) {
				String sessionKey = UUID.randomUUID().toString();
				userMapper.createUserSession(sessionKey, id);
				request.getSession().setAttribute("UserSessionKey", sessionKey);
				if (autoLogin) {
					Cookie cookie = new Cookie("UserSessionKey", sessionKey);
					cookie.setMaxAge(356 * 24 * 60 * 60);
					response.addCookie(cookie);
				}
			} else {
				code = 400;
				msg = FAIL_LOGIN;
			}
		}

		JsonBuilder builder = JsonBuilder.build(code, msg);
		return builder.toString();
	}
	
	@Override
	public String register(HttpServletRequest request, HttpServletResponse response, String id, String password,
			boolean autoLogin, String recapchResponse) {
		int code = 200;
		String msg = SUCCESS_LOGIN;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = 400;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserDTO user = new UserDTO(id, password);
			String result = userMapper.login(user);
			if (result != null) {
				String sessionKey = UUID.randomUUID().toString();
				userMapper.createUserSession(sessionKey, id);
				request.getSession().setAttribute("UserSessionKey", sessionKey);
				if (autoLogin) {
					Cookie cookie = new Cookie("UserSessionKey", sessionKey);
					cookie.setMaxAge(356 * 24 * 60 * 60);
					response.addCookie(cookie);
				}
			} else {
				code = 400;
				msg = FAIL_LOGIN;
			}
		}

		JsonBuilder builder = JsonBuilder.build(code, msg);
		return builder.toString();
	}

	@Override
	public String loginAdmin(HttpServletRequest request, HttpServletResponse response, String id, String password,
			boolean autoLogin, String recapchResponse) {
		int code = 200;
		String msg = SUCCESS_LOGIN;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = 400;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserDTO user = new UserDTO(id, password);
			String result = userMapper.loginAdmin(user);
			if (result != null) {
				String sessionKey = UUID.randomUUID().toString();
				userMapper.createAdminSession(sessionKey, id);
				request.getSession().setAttribute("AdminSessionKey", sessionKey);
				if (autoLogin) {
					Cookie cookie = new Cookie("AdminSessionKey", sessionKey);
					cookie.setMaxAge(356 * 24 * 60 * 60);
					response.addCookie(cookie);
				}
			} else {
				code = 400;
				msg = FAIL_LOGIN;
			}
		}

		JsonBuilder builder = JsonBuilder.build(code, msg);
		return builder.toString();
	}

	@Override
	public String rename(String id, String newId, String password, String recapchResponse) {
		int code = ResponseCode.SUCCESS;
		String msg = SUCCESS_LOGIN;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = ResponseCode.FAIL;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserRenameDTO user = new UserRenameDTO(id, newId, password);
			if (userMapper.login(user) != null) {
				msg = FAIL_LOGIN;
			} else if (userMapper.checkIdExist(newId) > 0) {
				msg = ALREDY_EXSIT_ID;
			} else {
				if (userMapper.rename(user) > 0) {
					code = ResponseCode.SUCCESS;
					msg = SUCCESS_RENAME;
				} else {
					msg = FAIL_RENAME;
				}
			}
		}
		return JsonBuilder.build(code, msg).toString();
	}

	@Override
	public String modifyPassword(String id, String password, String newPassword, String recapchResponse) {
		int code = ResponseCode.SUCCESS;
		String msg = SUCCESS_LOGIN;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = ResponseCode.FAIL;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserModifyPasswordDTO user = new UserModifyPasswordDTO(id, password, newPassword);
			if (userMapper.login(user) != null) {
				msg = FAIL_LOGIN;
			} else {
				if (userMapper.modifyPassword(user) > 0) {
					code = ResponseCode.SUCCESS;
					msg = SUCCESS_MODIFY_PASSWORD;
				} else {
					msg = FAIL_MODIFY_PASSWORD;
				}
			}
		}
		return JsonBuilder.build(code, msg).toString();
	}

	@Override
	public boolean checkAdminSession(HttpServletRequest request) {
		boolean check = false;
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("AdminSessionKey")) {
				if (mapper.checkAdminSession(cookie.getValue()) != null) {
					check = true;
				}
			}
		}
		if (check == false) {
			Object sessionKey = request.getSession().getAttribute("AdminSessionKey");
			if (sessionKey != null && mapper.checkAdminSession((String) sessionKey) != null) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public boolean checkUserSession(HttpServletRequest request) {
		boolean check = false;
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("UserSessionKey")) {
				if (mapper.checkUserSession(cookie.getValue()) != null) {
					check = true;
				}
			}
		}
		if (check == false) {
			Object sessionKey = request.getSession().getAttribute("UserSessionKey");
			if (sessionKey != null && mapper.checkUserSession((String) sessionKey) != null) {
				check = true;
			}
		}
		return check;
	}

	@Override
	public String getIdBySession(HttpServletRequest request) {
		String id = null;
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("UserSessionKey")) {
				id = mapper.checkUserSession(cookie.getValue());
			}
		}
		if (id == null) {
			Object sessionKey = request.getSession().getAttribute("UserSessionKey");
			if (sessionKey != null) {
				id = mapper.checkUserSession((String) sessionKey);
			}
		}
		return id;
	}

}
