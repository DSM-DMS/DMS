package com.boxfox.dms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.boxfox.dms.board.dao.FacilityDAO;
import com.boxfox.dms.board.dao.FaqDAO;
import com.boxfox.dms.board.dao.NoticeDAO;
import com.boxfox.dms.board.dao.QnaDAO;
import com.boxfox.dms.board.dao.RuleDAO;
import com.boxfox.dms.board.dto.Comment;
import com.boxfox.dms.board.dto.FacilityReportContext;
import com.boxfox.dms.board.dto.PrimaryPostContext;
import com.boxfox.dms.board.dto.QnaPostContext;
import com.boxfox.dms.board.dto.WriterPostContext;
import com.boxfox.dms.users.dao.UserDAO;

public class PostController {

	public String primary(FaqDAO faqDAO, RuleDAO ruleDAO, HttpServletRequest request, Model model, int no) {
		PrimaryPostContext post;
		String page = "index";
		if (request.getRequestURL().toString().contains("faq")) {
			post = faqDAO.getPost(no);
		} else
			post = ruleDAO.getPost(no);
		if (post == null) {
			page = "null";
			model.addAttribute("number", "");
			model.addAttribute("title", "");
			model.addAttribute("content", "");
		} else {
			model.addAttribute("number", post.getNo());
			model.addAttribute("title", post.getTitle());
			model.addAttribute("content", post.getTitle());
		}
		return page;
	}

	public String qna(QnaDAO qnaDAO, UserDAO userDAO, HttpServletRequest request, Model model, int no) {
		QnaPostContext post = qnaDAO.getPost(no);
		String page;
		if (post == null) {
			page = "null";
			model.addAttribute("q_title", "");
			model.addAttribute("q_date", "");
			model.addAttribute("q_writer", "");
			model.addAttribute("q_content", "");
			model.addAttribute("a_date", "");
			model.addAttribute("a_content", "");
		} else if (!post.isPrivacy()
				|| (userDAO.checkUserSession(request) && userDAO.getIdBySession(request).equals(post.getWriter()))) {
			List<Comment> comments = qnaDAO.getComments(post.getNo());
			model.addAttribute("q_title", post.getTitle());
			model.addAttribute("q_date", post.getDate());
			model.addAttribute("q_writer", post.getWriter());
			model.addAttribute("q_content", post.getContent());
			model.addAttribute("a_date", post.getResultDate());
			model.addAttribute("a_content", post.getResult());
			model.addAttribute("a_comment", comments);
			page = "index";
		} else {
			page = "privacy";
		}
		return page;
	}

	public String facility(FacilityDAO facilityDAO, HttpServletRequest request, Model model, int no) {
		FacilityReportContext post = facilityDAO.getPost(no);
		String page;
		if (post == null) {
			page = "null";
			model.addAttribute("f_title", "");
			model.addAttribute("f_date", "");
			model.addAttribute("f_writer", "");
			model.addAttribute("f_content", "");
			model.addAttribute("f_room", "");
			model.addAttribute("a_date", "");
			model.addAttribute("a_content", "");
		} else {
			model.addAttribute("f_title", post.getTitle());
			model.addAttribute("f_date", post.getDate());
			model.addAttribute("f_writer", post.getWriter());
			model.addAttribute("f_content", post.getContent());
			model.addAttribute("f_room", post.getRoom());
			model.addAttribute("a_date", post.getResultDate());
			model.addAttribute("a_content", post.getResult());
			page = "index";
		}
		return page;
	}

	public String notice(NoticeDAO noticeDAO, HttpServletRequest request, Model model, int no) {
		WriterPostContext post = noticeDAO.getPost(no);
		String page;
		if (post == null) {
			page = "null";
			model.addAttribute("title", "");
			model.addAttribute("writer", "");
			model.addAttribute("content", "");
		} else {
			model.addAttribute("title", post.getTitle());
			model.addAttribute("writer", post.getWriter());
			model.addAttribute("content", post.getContent());
			page = "index";
		}
		return page;
	}

}
