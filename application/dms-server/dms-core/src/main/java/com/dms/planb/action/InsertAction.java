package com.dms.planb.action;

import org.boxfox.dms.utilities.actions.ActionRegistration;
import org.boxfox.dms.utilities.actions.Actionable;
import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;

import com.dms.planb.support.Commands;

/**
 * @author JoMingyu
 */
public class InsertAction implements Actionable {
	/** (non-Javadoc)
	 * @see org.boxfox.dms.utilities.actions.Actionable#action(int, org.boxfox.dms.utilities.json.EasyJsonObject)
	 */
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		EasyJsonObject responseObject = new EasyJsonObject();
		
		DataBase database = DataBase.getInstance();
		
		// For register account
		String id = null;
		String password = null;
		String sessionKey = null;
		int permission;
		
		// For upload a post if have target
		int no;
		
		// For stay_apply
		int value;
		String week = null;
		
		String title = null;
		String content = null;
		String writer = null;
		
		// For status
		int status = 0;
		
		/**
		 * PK : Primary Key
		 * NN : Not Null
		 * UQ : Unique index
		 * B : Is binary column
		 * UN : Unsigned data type
		 * ZF : Fill up values for that column with 0's if it is numeric
		 * AI : Auto Incremental
		 * G : Generated column
		 */

		switch(command) {
		case Commands.REGISTER_STUDENT_ACC:
			// account
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			sessionKey = requestObject.getString("session_key");
			permission = requestObject.getInt("permission");
			
			// student data
			String studentName = requestObject.getString("name");
			int studentNumber = requestObject.getInt("number");
			int studentSex = requestObject.getInt("sex");
			int studentStatus = requestObject.getInt("status");
			
			String studentPhone = null;
			String parentName = null;
			String parentPhone = null;
			if(requestObject.containsKey("phone")) {
				studentPhone = requestObject.getString("phone");
			}
			if(requestObject.containsKey("p_name")) {
				parentName = requestObject.getString("p_name");
			}
			if(requestObject.containsKey("p_phone")) {
				parentPhone = requestObject.getString("p_phone");
			}
			
			/**
			 * Set stay apply default value when register account
			 * 
			 * Table Name : stay_apply_default
			 * 
			 * id VARCHAR(20) PK NN
			 * value INT(1) Default 4
			 */
			
			database.executeUpdate("INSERT INTO stay_apply_default(id, value) VALUES('", id, "', 4)");
			
			 /**
			  * Set id, password, .. to account table
			  * 
			  * Table Name : account
			  * 
			  * idx INT(11) PK NN AI
			  * id VARCHAR(20) NN UQ
			  * password VARCHAR(300) NN
			  * session_key VARCHAR(300) Default NULL
			  * permission TINYINT(1) NN
			  */
			
			database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ")");
			
			/**
			 * Set student's data to student_data table
			 * 
			 * Table Name : student_data
			 * 
			 * number INT(11) PK NN
			 * sex TINYINT(1) Default 1
			 * status INT(11) NN
			 * name VARCHAR(20) NN
			 * phone VARCHAR(20) Default NULL
			 * p_name VARCHAR(20) Default NULL
			 * p_phone VARCHAR(20) Default NULL
			 * merit INT(11) Default NULL
			 * demerit INT(11) Default NULL
			 */
			
			status = database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
			
			break;
		case Commands.REGISTER_TEACHER_ACC:
			/**
			 * Table Name : teacher_account
			 * 
			 * idx INT(11) PK NN AI
			 * id VARCHAR(20) NN UQ
			 * password VARCHAR(500) NN
			 * session_key VARCHAR(40) NN
			 * permission INT(11) NN Default 0
			 * name VARCHAR(20) NN
			 */
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			sessionKey = requestObject.getString("session_key");
			permission = requestObject.getInt("permission");
			String teacherName = requestObject.getString("name");
			
			status = database.executeUpdate("INSERT INTO teacher_account(id, password, session_key, permission, name) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ", '", teacherName, "')");
			
			break;
		case Commands.UPLOAD_RULE:
			/**
			 * Rules of dormitory
			 * 
			 * Table Name : rule
			 * 
			 * no INT(11) PK NN AI
			 * title VARCHAR(45) NN
			 * content VARCHAR(5000) NN
			 */
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			
			status = database.executeUpdate("INSERT INTO rule(title, content) VALUES('", title, "', '", content, "')");
			
			break;
		case Commands.UPLOAD_QUESTION:
			/**
			 * Question in Q&A
			 * 
			 * Table Name : qna
			 * 
			 * no INT(11) PK NN AI
			 * title VARCHAR(45) NN
			 * question_content VARCHAR(5000) NN
			 * question_date DATETIME NN
			 * writer VARCHAR(20) NN
			 * answer_content VARCHAR(5000) Default NULL
			 * answer_date DATETIME Defalt NULL
			 * privacy TINYINT(1) NN
			 * 
			 * DATETIME format : YYYY-MM-DD hh:mm:ss
			 */
			title = requestObject.getString("title");
			content = requestObject.getString("question_content");
			writer = requestObject.getString("writer");
			
			int privacy = requestObject.getInt("privacy");
			
			status = database.executeUpdate("INSERT INTO qna(title, question_content, question_date, writer, privacy) VALUES('", title, "', '", content, "', now(), '", writer, "', ", privacy, ")");
			
			break;
		case Commands.UPLOAD_ANSWER:
			/**
			 * Answer in Q&A
			 * 
			 * Reference UPLOAD_QUESTION
			 * Upload answer based question no
			 */
			no = requestObject.getInt("no");
			content = requestObject.getString("answer_content");
			
			status = database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date= now()", " WHERE no=", no);
			
			break;
		case Commands.UPLOAD_QNA_COMMENT:
			/**
			 * Q&A comment in Q&A
			 * 
			 * Table Name : qna_comment
			 * 
			 * idx INT(11) PK NN AI
			 * no INT(11) NN
			 * writer VARCHAR(10) NN
			 * comment_date DATETIME NN
			 * content VARCHAR(300) NN
			 * FOREIGN KEY no REFERENCES qna(no)
			 * ON DELETE/UPDATE CASCADE
			 * 
			 * DATETIME format : YYYY-MM-DD hh:mm:ss
			 * 
			 * Upload comment based qna no
			 */
			no = requestObject.getInt("no");
			content = requestObject.getString("content");
			writer = requestObject.getString("writer");
			
			status = database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(", no, ", '", writer, "', now(), '", content, "')");
			
			break;
		case Commands.UPLOAD_FAQ:
			/**
			 * Frequently asked questions
			 * 
			 * Table Name : faq
			 * 
			 * no INT(11) PK NN AI
			 * title VARCHAR(45) NN
			 * content VARCHAR(5000) NN
			 */
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			
			status = database.executeUpdate("INSERT INTO faq(title, content) VALUES('", title, "', '", content, "')");
			
			break;
		case Commands.UPLOAD_AFTERSCHOOL_ITEM:
			/**
			 * After school list
			 * 
			 * Table Name : afterschool_list
			 * 
			 * no INT(11) PK NN
			 * title VARCHAR(45) NN
			 * target INT(1) NN
			 * place VARCHAR(10) NN
			 * on_monday TINYINT(1) NN
			 * on_tuesday TINYINT(1) NN
			 * on_wednesday TINYINT(1) NN
			 * on_saturday TINYINT(1) NN
			 * instructor VARCHAR(10)
			 */
			no = requestObject.getInt("no");
			title = requestObject.getString("title");
			
			int target = requestObject.getInt("target");
			String place = requestObject.getString("place");
			boolean onMonday = requestObject.getBoolean("on_monday");
			boolean onTuesday = requestObject.getBoolean("on_tuesday");
			boolean onWednesday = requestObject.getBoolean("on_wednesday");
			boolean onSaturday = requestObject.getBoolean("on_saturday");
			String instructor = requestObject.getString("instructor");
			
			status = database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, on_monday, on_tuesday, on_wednesday, on_saturday, instructor) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", onMonday, ", ", onTuesday, ", ", onWednesday, ", ", onSaturday, ", '", instructor, "')");
			
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			/**
			 * Report facilities in dormitory
			 * 
			 * Table Name : facility_report
			 * 
			 * no INT(11) PK NN AI
			 * title VARCHAR(45) NN
			 * content VARCHAR(1000) NN
			 * room INT(11) NN
			 * write_date DATETIME NN
			 * writer VARCHAR(10) NN
			 * result VARCHAR(100) Default NULL
			 * result_date DATETIME Default NULL
			 * 
			 * DATETIME format : YYYY-MM-DD hh:mm:ss
			 */
			title = requestObject.getString("title");
			content = requestObject.getString("content");
			no = requestObject.getInt("room");
			writer = requestObject.getString("writer");
			
			status = database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", title, "', '", content, "', ", no, ", NOW(), '", writer, "')");
			
			break;
		case Commands.UPLOAD_REPORT_RESULT:
			/**
			 * Table Name : Reference UPLOAD_REPORT_FACILITY
			 * Upload result based report no
			 */
			no = requestObject.getInt("no");
			content = requestObject.getString("content");
			
			status = database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
			
			break;
		case Commands.APPLY_EXTENTION:
			/*
			 * Table Name : extension_apply
			 * 
			 * id VARCHAR(20) PK NN
			 * class INT(1) NN
			 * seat INT(2) NN
			 */
			id = requestObject.getString("id");
			int classId = requestObject.getInt("class");
			int seatId = requestObject.getInt("seat");
			
			status = database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES('", id, "', ", classId, ", ", seatId, ")");
			
			break;
		case Commands.APPLY_STAY:
			/*
			 * Apply stay - about value of date
			 * 
			 * Table Name : stay_apply
			 * 
			 * id VARCHAR(20) NN
			 * value INT(1) NN
			 * date DATE NN
			 * 
			 * DATE format : YYYY-MM-DD
			 * Friday home coming : 1
			 * Saturday home coming : 2
			 * Saturday dormitory coming : 3
			 * Stay : 4
			 */
			id = requestObject.getString("id");
			value = requestObject.getInt("value");
			week = requestObject.getString("week");
			
			status = database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES('", id, "', ", value, ", '", week, "')");
			
			break;
		case Commands.APPLY_GOINGOUT:
			/*
			 * Apply goingout - about departure date and reason
			 * 
			 * Table Name : goingout_apply
			 * 
			 * id VARCHAR(20) PK NN
			 * dept_date DATE NN
			 * reason VARCHAR(100) NN
			 * 
			 * DATE format : YYYY-MM-DD
			 */
			id = requestObject.getString("id");
			String deptDate = requestObject.getString("dept_date");
			String reason = requestObject.getString("reason");
			
			status = database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES('", id, "', '", deptDate, "', '", reason, "')");
			
			break;
		case Commands.APPLY_MERIT:
			/*
			 * Apply merit - about target and content
			 * 
			 * Table Name : merit_apply
			 * 
			 * no INT(11) PK NN AI
			 * id VARCHAR(20) NN
			 * target VARCHAR(45) Default NULL
			 * content VARCHAR(500) NN
			 */
			id = requestObject.getString("id");
			content = requestObject.getString("content");
			
			if(requestObject.containsKey("target")) {
				// Case that recommendation
				String recommendTarget = requestObject.getString("target");
				status = database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES('", id, "', '", recommendTarget, "', '", content, "')");
			} else {
				status = database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES('", id, "', '", content, "')");
			}
			
			break;
		case Commands.APPLY_AFTERSCHOOL:
			/*
			 * Apply after school - target number 
			 * 
			 * Table Name : afterschool_apply
			 * 
			 * id VARCHAR(20) NN
			 * no INT(11) NN
			 * CONSTRAINT afterschool_apply_ibfk_1 FOREIGN KEY no REFERENCES afterschool_list(no)
			 */
			id = requestObject.getString("id");
			no = requestObject.getInt("no");
			
			status = database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", id, "', ", no, ")");
			
			break;
		}
		
		responseObject.put("status", status);
		
		return responseObject;
	}
}
