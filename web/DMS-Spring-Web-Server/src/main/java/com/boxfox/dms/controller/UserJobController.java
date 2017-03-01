package com.boxfox.dms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxfox.dms.response.Guardian;
import com.boxfox.dms.response.JsonBuilder;
import com.boxfox.dms.users.dao.UserDAO;

@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserJobController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response) {
		String recapcha = request.getParameter("recapcha");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean autoLogin = Boolean.valueOf(request.getParameter("autoLogin"));
		if (Guardian.checkParameters(recapcha, id, password, autoLogin)) {
			return new JsonBuilder(400,"").toString();
		} else
			return userDAO.login(request, response, id, password, autoLogin, recapcha);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody String register(HttpServletRequest request, HttpServletResponse response) {
		String recapcha = request.getParameter("recapcha");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean autoLogin = Boolean.valueOf(request.getParameter("autoLogin"));
		if (Guardian.checkParameters(recapcha, id, password, autoLogin)) {
			return new JsonBuilder(400,"").toString();
		} else
			return userDAO.register(request, response, id, password, autoLogin, recapcha);
	}

	@RequestMapping(value = "/rename", method = RequestMethod.POST)
	public @ResponseBody String rename(HttpServletRequest request, HttpServletResponse response) {
		String recapcha = request.getParameter("g-recaptcha-response");
		String id = request.getParameter("id");
		String newId = request.getParameter("newId");
		String password = request.getParameter("password");
		boolean autoLogin = Boolean.valueOf(request.getParameter("autoLogin"));
		if (Guardian.checkParameters(recapcha, newId, id, password, autoLogin)) {
			return new JsonBuilder(400,"").toString();
		} else
			return userDAO.rename(id, newId, password, recapcha);
	}

	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public @ResponseBody String modifyPassword(HttpServletRequest request, HttpServletResponse response) {
		String recapcha = request.getParameter("g-recaptcha-response");
		String id = request.getParameter("id");
		String newPassword = request.getParameter("newPassword");
		String password = request.getParameter("password");
		boolean autoLogin = Boolean.valueOf(request.getParameter("autoLogin"));
		if (Guardian.checkParameters(recapcha, id, password, autoLogin)) {
			return new JsonBuilder(400,"").toString();
		} else
			return userDAO.modifyPassword(id, password, newPassword, recapcha);
	}
}
