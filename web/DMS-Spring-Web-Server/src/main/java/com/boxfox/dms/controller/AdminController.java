package com.boxfox.dms.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.boxfox.dms.board.dao.BoardDAO;
import com.boxfox.dms.board.dao.FacilityDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.mapper.UserMapper;
import com.boxfox.dms.users.dao.UserDAOImpl;
import com.boxfox.dms.users.dto.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	private static final int TYPE_FACILITY_RESULT = 1;
	private static final int TYPE_FAQ_RESULT = 2;
	private static final int TYPE_QNA = 3;
	private static final int TYPE_RULE = 4;
	private static final int TYPE_NOTICE = 5;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
    private NoticeDAOImpl noticeDAO;

	@Autowired
    private FacilityDAOImpl facilityDAO;

	@Autowired
	private QnaDAOImpl qnaDAO;
	
	@RequestMapping(value = "/admin/write/", method = RequestMethod.POST)
	public void home(HttpServletRequest request) {
		int type = Integer.valueOf(request.getParameter("type"));
		BoardDAO targetDAO;
		switch(type) {
		}
	}
	

	@RequestMapping(value = "/admin/write/", method = RequestMethod.POST)
	public void home(HttpServletRequest request) {
	}
}
