package com.boxfox.dms.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxfox.dms.board.dao.FacilityDAOImpl;
import com.boxfox.dms.board.dao.FaqDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dao.RuleDAOImpl;
import com.boxfox.dms.response.Guardian;
import com.boxfox.dms.response.JsonBuilder;
import com.boxfox.dms.users.dao.UserDAOImpl;

@RequestMapping(value = "/update", produces = "text/plain;charset=UTF-8")
@Controller
public class PostUpdateController {
	private static final String PUBLIC_WRITER = "»ç°¨ºÎ";

	private static final String MSG_PERMISSION_DENIED = "permission denied";
	private static final String MSG_INVALID_PARAMETER = "parameter invalid";
	private static final String MSG_SUCCESS_EDIT = "sucess edit post";
	private static final String MSG_SUCCESS_WRITE = "sucess write post";

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

	@RequestMapping(value = { "/notice" }, method = RequestMethod.POST)
	@ResponseBody
	public String notice(HttpServletRequest request, Model model) {
		JsonBuilder result;
		if (userDAO.checkAdminSession(request)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if (title == null || content == null) {
				result = JsonBuilder.build(400, MSG_INVALID_PARAMETER);
			} else if (request.getParameter("no") != null) {
				int no = Integer.valueOf(request.getParameter("no"));
				noticeDAO.editPost(no, title, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_EDIT);
			} else {
				noticeDAO.writePost(title, content, PUBLIC_WRITER);
				result = JsonBuilder.build(200, MSG_SUCCESS_WRITE);
			}
		} else {
			result = JsonBuilder.build(400, MSG_PERMISSION_DENIED);
		}
		return result.toString();
	}

	@RequestMapping(value = { "/rule" }, method = RequestMethod.POST)
	@ResponseBody
	public String rule(HttpServletRequest request, Model model) {
		JsonBuilder result;
		if (userDAO.checkAdminSession(request)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if (!Guardian.checkParameters(title, content)) {
				result = JsonBuilder.build(400, MSG_INVALID_PARAMETER);
			} else if (request.getParameter("no") != null) {
				int no = Integer.valueOf(request.getParameter("no"));
				ruleDAO.editPost(no, title, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_EDIT);
			} else {
				ruleDAO.writePost(title, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_WRITE);
			}
		} else {
			result = JsonBuilder.build(400, MSG_PERMISSION_DENIED);
		}
		return result.toString();
	}

	@RequestMapping(value = { "/facility" }, method = RequestMethod.POST)
	@ResponseBody
	public String Facility(HttpServletRequest request, Model model) {
		JsonBuilder result;
		if (userDAO.checkAdminSession(request)) {
			String content = request.getParameter("content");
			int no = Integer.valueOf(request.getParameter("no"));
			if (!Guardian.checkParameters(content, no)) {
				result = JsonBuilder.build(400, MSG_INVALID_PARAMETER);
			} else {
				facilityDAO.setResult(no, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_WRITE);
			}
		} else {
			result = JsonBuilder.build(400, MSG_PERMISSION_DENIED);
		}
		return result.toString();
	}

	@RequestMapping(value = { "/qna" }, method = RequestMethod.POST)
	@ResponseBody
	public String qna(HttpServletRequest request, Model model) {
		JsonBuilder result;
		if (userDAO.checkAdminSession(request)) {
			String content = request.getParameter("content");
			int no = Integer.valueOf(request.getParameter("no"));
			if (!Guardian.checkParameters(content, no)) {
				result = JsonBuilder.build(400, MSG_INVALID_PARAMETER);
			} else {
				qnaDAO.writeAnswer(no, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_WRITE);
			}
		} else {
			result = JsonBuilder.build(400, MSG_PERMISSION_DENIED);
		}
		return result.toString();
	}

	@RequestMapping(value = { "/faq" }, method = RequestMethod.POST)
	@ResponseBody
	public String faq(HttpServletRequest request, Model model) {
		JsonBuilder result;
		if (userDAO.checkAdminSession(request)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if (!Guardian.checkParameters(title, content)) {
				result = JsonBuilder.build(400, MSG_INVALID_PARAMETER);
			} else if (request.getParameter("no") != null) {
				int no = Integer.valueOf(request.getParameter("no"));
				faqDAO.editPost(no, title, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_EDIT);
			} else {
				faqDAO.writePost(title, content);
				result = JsonBuilder.build(200, MSG_SUCCESS_WRITE);
			}
		} else {
			result = JsonBuilder.build(400, MSG_PERMISSION_DENIED);
		}
		return result.toString();
	}

}
