package com.boxfox.dms.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dto.DatePostContext;

@Controller
public class PostController {
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
    private NoticeDAOImpl noticeDAO;

	@RequestMapping(value = "/post")
	public String notice(Locale locale, Model model) {
		List<DatePostContext> posts = noticeDAO.getPostsAtPage(0);
		//model.addAttribute("serverTime", post.getTitle() );
		
		return "index";
	}
}
