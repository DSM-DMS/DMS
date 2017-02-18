package com.boxfox.dms.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boxfox.dms.board.dao.FacilityDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.mapper.UserMapper;
import com.boxfox.dms.meal.dao.MealDAOImpl;
import com.boxfox.dms.meal.dto.DayMealDTO;
import com.boxfox.dms.users.dao.UserDAOImpl;
import com.boxfox.dms.users.dto.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
    private NoticeDAOImpl noticeDAO;
	

	@Autowired
    private FacilityDAOImpl facilityDAO;
	
	@Autowired
	private MealDAOImpl mealDAO;

	@Autowired
	private QnaDAOImpl qnaDAO;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
}
