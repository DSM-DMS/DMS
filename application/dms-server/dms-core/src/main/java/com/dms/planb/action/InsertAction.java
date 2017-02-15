package com.dms.planb.action;

import java.sql.SQLException;

import org.boxfox.dms.utilities.actions.ActionRegister;
import org.boxfox.dms.utilities.actions.Actionable;
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
	public void action(int command, EasyJsonObject requestObject) throws SQLException {
		EasyJsonObject responseObject = new EasyJsonObject();
		
		DataBase database = DataBase.getInstance();
		
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
		
		ActionRegister.registerAction(Commands.REGISTER_STUDENT_ACC, new Actionable() {
			// Command 100
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				// account
				String id = requestObject.getString("id");
				String password = requestObject.getString("password");
				String sessionKey = requestObject.getString("session_key");
				int permission = requestObject.getInt("permission");
				
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
				
				int status = database.executeUpdate("INSERT INTO student_data(number, sex, status, name, phone, p_name, p_phone) VALUES(", studentNumber, ", ", studentSex, ", ", studentStatus, ", '", studentName, "', '", studentPhone, "', '", parentName, "', '", parentPhone, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.REGISTER_TEACHER_ACC, new Actionable() {
			// command 101
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
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
				String id = requestObject.getString("id");
				String password = requestObject.getString("password");
				String sessionKey = requestObject.getString("session_key");
				int permission = requestObject.getInt("permission");
				String teacherName = requestObject.getString("name");
				
				int status = database.executeUpdate("INSERT INTO teacher_account(id, password, session_key, permission, name) VALUES('", id, "', '", password, "', '", sessionKey, "', ", permission, ", '", teacherName, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_RULE, new Actionable() {
			// command 111
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Rules of dormitory
				 * 
				 * Table Name : rule
				 * 
				 * no INT(11) PK NN AI
				 * title VARCHAR(45) NN
				 * content VARCHAR(5000) NN
				 */
				String title = requestObject.getString("title");
				String content = requestObject.getString("content");
				
				int status = database.executeUpdate("INSERT INTO rule(title, content) VALUES('", title, "', '", content, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_QUESTION, new Actionable() {
			// command 112
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
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
				String title = requestObject.getString("title");
				String content = requestObject.getString("question_content");
				String writer = requestObject.getString("writer");
				
				int privacy = requestObject.getInt("privacy");
				
				int status = database.executeUpdate("INSERT INTO qna(title, question_content, question_date, writer, privacy) VALUES('", title, "', '", content, "', now(), '", writer, "', ", privacy, ")");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_ANSWER, new Actionable() {
			// command 113
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Answer in Q&A
				 * 
				 * Reference UPLOAD_QUESTION
				 * Upload answer based question no
				 */
				int no = requestObject.getInt("no");
				String content = requestObject.getString("answer_content");
				
				int status = database.executeUpdate("UPDATE qna SET answer_content='", content, "', answer_date= now()", " WHERE no=", no);
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_QNA_COMMENT, new Actionable() {
			// command 114
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
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
				int no = requestObject.getInt("no");
				String content = requestObject.getString("content");
				String writer = requestObject.getString("writer");
				
				int status = database.executeUpdate("INSERT INTO qna_comment(no, writer, comment_date, content) VALUES(", no, ", '", writer, "', now(), '", content, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_FAQ, new Actionable() {
			// command 115
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Frequently asked questions
				 * 
				 * Table Name : faq
				 * 
				 * no INT(11) PK NN AI
				 * title VARCHAR(45) NN
				 * content VARCHAR(5000) NN
				 */
				String title = requestObject.getString("title");
				String content = requestObject.getString("content");
				
				int status = database.executeUpdate("INSERT INTO faq(title, content) VALUES('", title, "', '", content, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_AFTERSCHOOL_ITEM, new Actionable() {
			// command 116
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
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
				int no = requestObject.getInt("no");
				String title = requestObject.getString("title");
				
				int target = requestObject.getInt("target");
				String place = requestObject.getString("place");
				boolean onMonday = requestObject.getBoolean("on_monday");
				boolean onTuesday = requestObject.getBoolean("on_tuesday");
				boolean onWednesday = requestObject.getBoolean("on_wednesday");
				boolean onSaturday = requestObject.getBoolean("on_saturday");
				String instructor = requestObject.getString("instructor");
				
				int status = database.executeUpdate("INSERT INTO afterschool_list(no, title, target, place, on_monday, on_tuesday, on_wednesday, on_saturday, instructor) VALUES(", no, ", '", title, "', ", target, ", '", place, "', ", onMonday, ", ", onTuesday, ", ", onWednesday, ", ", onSaturday, ", '", instructor, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_REPORT_FACILITY, new Actionable() {
			// command 117
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
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
				String title = requestObject.getString("title");
				String content = requestObject.getString("content");
				int no = requestObject.getInt("room");
				String writer = requestObject.getString("writer");
				
				int status = database.executeUpdate("INSERT INTO facility_report(title, content, room, write_date, writer) VALUES('", title, "', '", content, "', ", no, ", NOW(), '", writer, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_REPORT_RESULT, new Actionable() {
			// command 118
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Table Name : Reference UPLOAD_REPORT_FACILITY
				 * Upload result based report no
				 */
				int no = requestObject.getInt("no");
				String content = requestObject.getString("content");
				
				int status = database.executeUpdate("UPDATE facility_report SET result='", content, "', result_date=NOW() WHERE no=", no);
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_EXTENTION, new Actionable() {
			// command 131
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Table Name : extension_apply
				 * 
				 * id VARCHAR(20) PK NN
				 * class INT(1) NN
				 * seat INT(2) NN
				 */
				String id = requestObject.getString("id");
				int classId = requestObject.getInt("class");
				int seatId = requestObject.getInt("seat");
				
				int status = database.executeUpdate("INSERT INTO extension_apply(id, class, seat) VALUES('", id, "', ", classId, ", ", seatId, ")");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_STAY, new Actionable() {
			// command 132
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
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
				String id = requestObject.getString("id");
				int value = requestObject.getInt("value");
				String week = requestObject.getString("week");
				
				int status = database.executeUpdate("INSERT INTO stay_apply(id, value, date) VALUES('", id, "', ", value, ", '", week, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_GOINGOUT, new Actionable() {
			// command 133
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
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
				String id = requestObject.getString("id");
				String deptDate = requestObject.getString("dept_date");
				String reason = requestObject.getString("reason");
				
				int status = database.executeUpdate("INSERT INTO goingout_apply(id, dept_date, reason) VALUES('", id, "', '", deptDate, "', '", reason, "')");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_MERIT, new Actionable() {
			// command 134
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Apply merit - about target and content
				 * 
				 * Table Name : merit_apply
				 * 
				 * no INT(11) PK NN AI
				 * id VARCHAR(20) NN
				 * target VARCHAR(45) Default NULL
				 * content VARCHAR(500) NN
				 */
				String id = requestObject.getString("id");
				String content = requestObject.getString("content");
				
				int status;
				if(requestObject.containsKey("target")) {
					// Case that merit recommendation
					String recommendTarget = requestObject.getString("target");
					status = database.executeUpdate("INSERT INTO merit_apply(id, target, content) VALUES('", id, "', '", recommendTarget, "', '", content, "')");
				} else {
					status = database.executeUpdate("INSERT INTO merit_apply(id, content) VALUES('", id, "', '", content, "')");
				}
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_AFTERSCHOOL, new Actionable() {
			// command 135
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				/**
				 * Apply after school - target number 
				 * 
				 * Table Name : afterschool_apply
				 * 
				 * id VARCHAR(20) NN
				 * no INT(11) NN
				 * CONSTRAINT afterschool_apply_ibfk_1 FOREIGN KEY no REFERENCES afterschool_list(no)
				 */
				String id = requestObject.getString("id");
				int no = requestObject.getInt("no");
				
				int status = database.executeUpdate("INSERT INTO afterschool_apply(id, no) VALUES('", id, "', ", no, ")");
				
				responseObject.put("status", status);
				
				return responseObject;
			}
		});
	}
}
