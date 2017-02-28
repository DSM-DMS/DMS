package com.boxfox.dms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boxfox.dms.board.dao.BoardDAO;
import com.boxfox.dms.board.dao.FacilityDAOImpl;
import com.boxfox.dms.board.dao.FaqDAOImpl;
import com.boxfox.dms.board.dao.NoticeDAOImpl;
import com.boxfox.dms.board.dao.QnaDAOImpl;
import com.boxfox.dms.board.dao.RuleDAOImpl;
import com.boxfox.dms.board.dto.DatePostContext;
import com.boxfox.dms.mapper.UserMapper;
import com.boxfox.dms.users.dao.UserDAOImpl;
import com.boxfox.dms.users.dto.UserDTO;
import com.boxfox.dsm.xlsx.ResidualDownLoadDAOImpl;

/**
 * Handles requests for the application home page.
 */
@RequestMapping(value = "/admin", produces = "text/plain;charset=UTF-8")
@Controller
public class AdminController {
	private static final int TYPE_FACILITY_RESULT = 1;
	private static final int TYPE_FAQ_RESULT = 2;
	private static final int TYPE_QNA = 3;
	private static final int TYPE_RULE = 4;
	private static final int TYPE_NOTICE = 5;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


	@Autowired
	private UserDAOImpl userDAO;

	@Autowired
	private ResidualDownLoadDAOImpl residualDownload;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		if (userDAO.checkAdminSession(request)) {
			return "home";
		} else {
			return "null";
		}
	}

	@RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("file_name") String fileName) {
		if (userDAO.checkAdminSession(request)) {
			Matcher m = Pattern.compile("(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.]([1-4])").matcher(fileName);
			String date = null;
			if (m.find()) {
				date = m.group();
			}
			try {
				if (date == null || !fileName.endsWith("xlsx")) {
					response.sendError(400, "yyyy-mm-ww 형식의 이름이 포함되어야 하며, 확장자는 xlsx형식 이여야 합니다.");
				}
				IOUtils.copy(residualDownload.readExcel(date), response.getOutputStream());
				response.flushBuffer();
				try {
					response.sendRedirect("/admin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				response.sendError(404);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request, HttpServletResponse response, @RequestParam String id,
			@RequestParam String password, @RequestParam boolean autoLogin, @RequestParam String recapcha) {
		return userDAO.loginAdmin(request, response, id, password, autoLogin, recapcha);
	}
}
