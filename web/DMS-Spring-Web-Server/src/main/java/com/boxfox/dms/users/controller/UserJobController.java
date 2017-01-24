package com.boxfox.dms.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxfox.dms.users.dao.UserDAOImpl;

@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserJobController {

	@Autowired
	private UserDAOImpl userDAO;

	@RequestMapping("/login")
	public @ResponseBody String login(@RequestParam String id, @RequestParam String password) {
		return userDAO.login(id,password);
	}
	
	@RequestMapping("/rename")
	public @ResponseBody String rename(@RequestParam String id, @RequestParam String newId, @RequestParam String password) {
		return userDAO.rename(id,newId,password);
	}
	
	@RequestMapping("/modifyPassword")
	public @ResponseBody String modifyPassword(@RequestParam String id, @RequestParam String password, @RequestParam String newPassword) {
		return userDAO.modifyPassword(id, password, newPassword);
	}
}
