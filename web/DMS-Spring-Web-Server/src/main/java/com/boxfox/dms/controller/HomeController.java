package com.boxfox.dms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxfox.dms.board.dao.FacilityDAOImpl;
import com.boxfox.dms.board.dao.FaqDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dao.RuleDAOImpl;
import com.boxfox.dms.users.dao.UserDAOImpl;

@RequestMapping(value = "/", produces = "text/plain;charset=UTF-8")
@Controller
public class HomeController extends PostController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private NoticeDAOImpl noticeDAO;
	@Autowired
	private FaqDAOImpl faqDAO;
	@Autowired
	private RuleDAOImpl ruleDAO;
	@Autowired
	private QnaDAOImpl qnaDAO;
	@Autowired
	private FacilityDAOImpl facilityDAO;
	@Autowired
	private UserDAOImpl userDAO;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		return "index";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		return "login";
	}

}
