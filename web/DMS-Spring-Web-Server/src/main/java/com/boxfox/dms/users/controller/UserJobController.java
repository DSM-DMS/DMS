package com.boxfox.dms.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxfox.dms.users.dao.UserDAO;
import com.boxfox.dms.users.dao.UserDAOImpl;

@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserJobController {

	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam String id, @RequestParam String password, @RequestParam String recapcha) {
		return userDAO.login(id,password, recapcha);
	}
	
	@RequestMapping(value = "/rename", method = RequestMethod.POST)
	public @ResponseBody String rename(@RequestParam String id, @RequestParam String newId, @RequestParam String password, @RequestParam String recapcha) {
		return userDAO.rename(id,newId,password, recapcha);
	}
	
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public @ResponseBody String modifyPassword(@RequestParam String id, @RequestParam String password, @RequestParam String newPassword, @RequestParam String recapcha) {
		return userDAO.modifyPassword(id, password, newPassword, recapcha);
	}
}
