package com.boxfox.dms.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.boxfox.dms.response.JsonBuilder;
import com.boxfox.dms.users.dao.UserDAOImpl;

@RequestMapping(value = "/delete", produces = "text/plain;charset=UTF-8")
@Controller
public class PostDeleteController extends PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostDeleteController.class);

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

	@RequestMapping(value = { "/notice", "/faq", "/rule", "/qna", "/facility" }, params = {
			"no" }, method = RequestMethod.GET)
	@ResponseBody
	public String notice(HttpServletRequest request, Model model, @RequestParam("no") int no) {
		String url = request.getRequestURL().toString();
		int count = 0;
		if (url.contains("notice")) {
			count = noticeDAO.deletePost(no);
		} else if (url.contains("faq")) {
			count = faqDAO.deletePost(no);
		} else if (url.contains("rule")) {
			count = ruleDAO.deletePost(no);
		} else if (url.contains("qna")) {
			count = qnaDAO.deletePost(no);
		} else if (url.contains("facility")) {
			count = facilityDAO.deletePost(no);
		}
		int code = 200;
		if (count == 0) {
			code = 400;
		}
		return JsonBuilder.build(code, "").toString();
	}

}
