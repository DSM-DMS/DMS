package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.json.EasyJsonObject;
import org.json.simple.JSONObject;

import com.dms.planb.support.Commands;

public class InsertAction implements Actionable {
	@SuppressWarnings("unchecked")
	@Override
	public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
		JSONObject responseObject = new JSONObject();
		EasyJsonObject readOnlyResponseObject;
		
		DataBase database = DataBase.getInstance();
		
		// For register account
		String id = null;
		String password = null;
		String sessionKey = null;
		int permission;
		
		// For upload a post
		int no;
		int number;
		int category;
		
		String title = null;
		String content = null;
		String writer = null;
		String date = null;
		
		// For status
		int status = 0;
		
		/*
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
			 /*
			  * Table Name : account
			  * 
			  * idx INT(11) PK NN AI
			  * id VARCHAR(20) NN UQ
			  * password VARCHAR(300) NN
			  * session_key VARCHAR(300) Default NULL
			  * permission TINYINT(1) NN
			  */
			id = requestObject.getString("id");
			password = requestObject.getString("password");
			sessionKey = requestObject.getString("session_key");
			permission = requestObject.getInt("permission");
			
			status = database.executeUpdate("INSERT INTO account(id, password, session_key, permission) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ")");
			
			/*
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
			int studentNumber = requestObject.getInt("number");
			int studentSex = requestObject.getInt("sex");
			int studentStatus = requestObject.getInt("status");
			String studentName = requestObject.getString("name");
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
			
			status = database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
			break;
		case Commands.REGISTER_TEACHER_ACC:
			/*
			 * Table Name : teacher_account
			 * 
			 * idx INT(11) PK NN AI
			 * id VARCHAR(20) NN UQ
			 * password VARCHAR(300) NN
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
		case Commands.UPLOAD_NOTICE:
		case Commands.UPLOAD_NEWSLETTER:
		case Commands.UPLOAD_COMPETITION:
			/*
			 * Table Name : app_content
			 * 
			 * no INT(11) PK NN AI
			 * number INT(11) NN
			 * category INT(1) NN
			 * title VARCHAR(100) Default NULL
			 * content VARCHAR(5000) NN
			 * writer VARCHAR(10) NN
			 * date DATETIME NN
			 * 
			 * DATETIME format : YYYY-MM-DD hh:mm:ss
			 * 
			 ** diff between no & number : number is from school notice board
			 *
			 ** category
			 ** notice : 1
			 ** announcement : 2
			 ** competition : 3
			 */
			number = requestObject.getInt("number");
			category = requestObject.getInt("category");
			if(requestObject.containsKey("title")) {
				title = requestObject.getString("title");
			}
			content = requestObject.getString("content");
			writer = requestObject.getString("writer");
			date = requestObject.getString("date");
			
			status = database.executeUpdate("INSERT INTO app_content(number, category, title, content, writer, date) VALUES(", number, ", ", category, ", '", title, "', '", content, "', '", writer, "', '", date, "')");
			break;
		case Commands.UPLOAD_RULE:
			/*
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
			/*
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
			date = requestObject.getString("question_date");
			writer = requestObject.getString("questioner");
			
			int privacy = requestObject.getInt("privacy");
			
			status = database.executeUpdate("INSERT INTO qna(title, question_content, question_date, questioner, privacy) VALUES('", title, "', '", content, "', '", date, "', '", writer, "', ", privacy, ")");
			break;
		case Commands.UPLOAD_ANSWER:
			/*
			 * Reference UPLOAD_QUESTION
			 * Upload answer based question no
			 */
			no = requestObject.getInt("no");
			content = requestObject.getString("answer_content");
			date = requestObject.getString("answer_date");
			
			status = database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date='", date, "' WHERE no=", no);
			break;
		case Commands.UPLOAD_QNA_COMMENT:
			/*
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
			writer = requestObject.getString("writer");
			content = requestObject.getString("content");
			
			status = database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(", no, ", '", writer, "', now(), '", content, "')");
			break;
		case Commands.UPLOAD_FAQ:
			/*
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
		case Commands.UPLOAD_AFTERSCHOOL:
			/*
			 * Table Name : afterschool_list
			 * 
			 * no INT(11) PK NN AI
			 * title VARCHAR(45) NN
			 * target INT(1) NN
			 * place VARCHAR(10) NN
			 * day INT(1) NN
			 * instructor VARCHAR(10)
			 */
			no = requestObject.getInt("no");
			title = requestObject.getString("title");
			
			int target = requestObject.getInt("target");
			String place = requestObject.getString("place");
			int day = requestObject.getInt("day");
			String instructor = requestObject.getString("instructor");
			
			status = database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, day, instructor) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", day, ", '", instructor, "')");
			break;
		case Commands.UPLOAD_REPORT_FACILITY:
			/*
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
			/*
			 * Reference UPLOAD_REPORT_FACILITY
			 * Upload result based report no
			 */
			no = requestObject.getInt("no");
			content = requestObject.getString("result");
			
			status = database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
			break;
//		case Commands.UPLOAD_MEAL:
//			/*
//			 * Table Name : meal
//			 * 
//			 * date DATE PK NN
//			 * breakfast VARCHAR(100) NN
//			 * lunch VARCHAR(100) NN
//			 * dinner VARCHAR(100) NN
//			 * breakfast_allergy VARCHAR(100) NN
//			 * lunch_allergy VARCHAR(100) NN
//			 * dinner_allergy VARCHAR(100) NN
//			 * 
//			 * DATE format : YYYY-MM-DD
//			 */
//			date = requestObject.getString("no");
//			String breakfast = requestObject.getString("breakfast");
//			String lunch = requestObject.getString("lunch");
//			String dinner = requestObject.getString("dinner");
//			String breakfast_allergy = requestObject.getString("breakfast_allergy");
//			String lunch_allergy = requestObject.getString("lunch_allergy");
//			String dinner_allergy = requestObject.getString("dinner_allergy");
//			
//			status = database.executeUpdate("INSERT INTO meal(date, breakfast, lunch, dinner, breakfast_allergy, lunch_allergy, dinner_allergy) VALUES('", date, "', '", breakfast, "', '", lunch, "', '", dinner, "', '", breakfast_allergy, "', '", lunch_allergy, "', '", dinner_allergy, "')");			
//			break;
//		case Commands.UPLOAD_PLAN:
//			/*
//			 * Table Name : plan
//			 * 
//			 * year INT(4) PK NN
//			 * month INT(2) PK NN
//			 * date VARCHAR(5000) NN
//			 */
//			
//			break;
		case Commands.APPLY_EXTENTION:
			/*
			 * Table Name : extension_apply
			 * 
			 * id INT(11) PK NN
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
			 * Table Name : stay_apply
			 * 
			 * id INT(11) PK NN
			 * value INT(1) NN Default 4
			 * date DATE NN Default 'all'
			 * 
			 * DATE format : YYYY-MM-DD
			 * Friday home coming : 1
			 * Saturday home coming : 2
			 * Saturday dormitory coming : 3
			 * Stay : 4
			 */
			id = requestObject.getString("id");
			int extensionValue = requestObject.getInt("value");
			date = requestObject.getString("date");
			
			status = database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES('", id, "', ", extensionValue, ", '", date, "')");
			break;
		case Commands.APPLY_GOINGOUT:
			/*
			 * Table Name : goingout_apply
			 * 
			 * id INT(11) PK NN
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
			 * Table Name : merit_apply
			 * 
			 * no INT(11) PK NN AI
			 * id INT(11) NN
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
			 * Table Name : afterschool_apply
			 * 
			 * id INT(11) NN
			 * no INT(11) NN
			 */
			id = requestObject.getString("id");
			no = requestObject.getInt("no");
			
			status = database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", id, "', ", no, ")");
			break;
		}
		
		responseObject.put("status", status);
		readOnlyResponseObject = new EasyJsonObject(responseObject);
		
		return readOnlyResponseObject;
	}
}
