package com.boxfox.dms.users.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

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
	public String login(String id, String password, String recapchResponse) {
		int code = ResponseCode.SUCCESS;
		String msg = SUCCESS_LOGIN;
		String result = null;
		if (!VerifyRecaptcha.verify(recapchResponse)) {
			code = ResponseCode.FAIL;
			msg = FAIL_RECAPCHA;
		} else {
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			UserDTO user = new UserDTO(id, password);
			List<String> results = userMapper.login(user);

			if (results.size() > 0)
				result = results.get(0);
			else {
				code = ResponseCode.FAIL;
				msg = FAIL_LOGIN;
				result = null;
			}
		}

		JsonBuilder builder = JsonBuilder.build(code, msg);
		if (result != null)
			builder.put("SessionKey", result);

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
			if (userMapper.login(user).size() == 0) {
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
			if (userMapper.login(user).size() == 0) {
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
	public boolean checkAdminSession(Cookie[] cookies) {
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("AdminSessionKey")) {
				cookie.getValue();
			}
		}
		return false;
	}

	@Override
	public boolean checkUserSession(Cookie[] cookies) {
		return false;
	}

}
