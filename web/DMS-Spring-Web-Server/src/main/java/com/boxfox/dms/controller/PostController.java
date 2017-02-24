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

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

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

	@RequestMapping(value = { "/faq", "/rule" }, params = { "no" }, method = RequestMethod.GET)
	public String primary(HttpServletRequest request, Locale locale, Model model, @RequestParam("no") int no) {
		PrimaryPostContext post;
		String page = "primary_index";
		if (request.getRequestURL().toString().contains("faq")) {
			post = faqDAO.getPost(no);
		} else
			post = ruleDAO.getPost(no);
		if (post == null) {
			page = "null";
		} else {
			model.addAttribute("number", post.getNo());
			model.addAttribute("title", post.getTitle());
			model.addAttribute("content", post.getTitle());
		}
		return page;
	}

	@RequestMapping(value = "/qna", params = { "no" }, method = RequestMethod.GET)
	public String qna(HttpServletRequest request, Locale locale, Model model, @RequestParam("no") int no) {
		QnaPostContext post = qnaDAO.getPost(no);
		String page = "qna_index";
		if (post == null) {
			page = "null";
		} else {
			if (!post.isPrivacy()) {

			} else {
				model.addAttribute("number", post.getNo());
				model.addAttribute("title", post.getTitle());
				model.addAttribute("content", post.getTitle());
				model.addAttribute("writer", post.getWriter());
				model.addAttribute("resultDate", post.getResultDate());
				model.addAttribute("result", post.getResult());
				model.addAttribute("comments", qnaDAO.getComments(no));
			}
		}
		return page;
	}

	/*@RequestMapping(value = "/qna", params = { "no" })
	public String qnaView(Locale locale, Model model, @RequestParam(value = "no") int no) {
		QnaPostContext post = qnaDAO.getPost(no);
		if (post != null) {
		if (!post.isPrivacy() || 세션 체크) {
			List<Comment> comments = qnaDAO.getComments(post.getNo());
			model.addAttribute("q_title", post.getTitle());
			model.addAttribute("q_date", post.getDate());
			model.addAttribute("q_writer", post.getWriter());
			model.addAttribute("q_content", post.getContent());
			model.addAttribute("a_date", post.getResultDate());
			model.addAttribute("a_content", post.getResult());
			return "qna";
		}
		return page;
	}*/
	
	@RequestMapping(value = "/writePost", method = RequestMethod.POST)
	public String writePost(HttpServletRequest request, Locale locale, Model model, @RequestParam("no") int no) {
		DatePostContext post = noticeDAO.getPost(no);
		String page = "facility_index";
		if (post == null) {
			page = "null";
		} else {
			model.addAttribute("number", post.getNo());
			model.addAttribute("title", post.getTitle());
			model.addAttribute("content", post.getTitle());
			model.addAttribute("writer", post.getWriter());
			model.addAttribute("date", post.getDate());
		}
		return page;
	}

}
