package com.boxfox.dms.controller;

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
import com.boxfox.dms.board.dao.RuleDAO;
import com.boxfox.dms.board.dao.RuleDAOImpl;
import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.board.dto.FacilityReportContext;
import com.boxfox.dms.board.dto.PrimaryPostContext;
import com.boxfox.dms.board.dto.QnaPostContext;
import com.boxfox.dms.users.dao.UserDAOImpl;

@RequestMapping(value = "/edit", produces = "text/plain;charset=UTF-8")
@Controller
public class PostEditController extends PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostEditController.class);

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

	@RequestMapping(value = { "/notice" }, params = { "no" }, method = RequestMethod.GET)
	public String notice(HttpServletRequest request, Model model, @RequestParam("no") int no) {
		if (userDAO.checkAdminSession(request)) {
			String page = super.notice(noticeDAO, request, model, no);
			if (page.equals("index"))
				page = "notice_edit";
			return page;
		}
		return null;
	}

	@RequestMapping(value = { "/faq", "/rule" }, params = { "no" }, method = RequestMethod.GET)
	public String primary(HttpServletRequest request, Model model, @RequestParam("no") int no) {
		if (userDAO.checkAdminSession(request)) {
			String page = super.primary(faqDAO, ruleDAO, request, model, no);
			if (page.equals("index"))
				page = "primary_edit";
			return page;
		}
		return null;
	}

	@RequestMapping(value = "/qna", params = { "no" })
	public String qnaView(HttpServletRequest request, Model model, @RequestParam(value = "no") int no) {
		String page = super.qna(qnaDAO, userDAO, request, model, no);
		if (page.equals("index")) {
			page = "qna_edit";
		}
		return page;
	}

	@RequestMapping(value = "/facility", params = { "no" })
	public String facilityView(HttpServletRequest request, Model model, @RequestParam(value = "no") int no) {
		String page = super.facility(facilityDAO, request, model, no);
		if (page.equals("index")) {
			page = "facility_edit";
		}
		return page;
	}

}
