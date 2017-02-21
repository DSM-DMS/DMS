package com.boxfox.dms.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boxfox.dms.board.dao.FaqDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dao.RuleDAOImpl;
import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.board.dto.PrimaryPostContext;
import com.boxfox.dms.board.dto.QnaPostContext;

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private NoticeDAOImpl noticeDAO;
	@Autowired
	private RuleDAOImpl ruleDAO;
	@Autowired
	private FaqDAOImpl faqDAO;
	@Autowired
	private QnaDAOImpl qnaDAO;

	@RequestMapping(value = "/rule", params = { "no" })
	public String ruleView(Locale locale, Model model, @RequestParam(value = "no") int no) {
		PrimaryPostContext post = ruleDAO.getPost(no);
		if (post != null) {
			model.addAttribute("title", post.getTitle());
			model.addAttribute("content", post.getContent());
			return "rule";
		} else {
			return "notfound";
		}
	}

	@RequestMapping(value = "/faq", params = { "no" })
	public String faqView(Locale locale, Model model, @RequestParam(value = "no") int no) {
		PrimaryPostContext post = faqDAO.getPost(no);
		if (post != null) {
			model.addAttribute("title", post.getTitle());
			model.addAttribute("content", post.getContent());
			return "faq";
		} else {
			return "notfound";
		}
	}

	@RequestMapping(value = "/qna", params = { "no" })
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
		} else {
			return "notfound";
		}
	}

}
