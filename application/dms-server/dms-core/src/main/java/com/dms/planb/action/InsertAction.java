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
				
			}
		});
		
		ActionRegister.registerAction(Commands.REGISTER_TEACHER_ACC, new Actionable() {
			// command 101
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_RULE, new Actionable() {
			// command 111
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_QUESTION, new Actionable() {
			// command 112
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_ANSWER, new Actionable() {
			// command 113
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_QNA_COMMENT, new Actionable() {
			// command 114
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_FAQ, new Actionable() {
			// command 115
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_AFTERSCHOOL_ITEM, new Actionable() {
			// command 116
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_REPORT_FACILITY, new Actionable() {
			// command 117
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.UPLOAD_REPORT_RESULT, new Actionable() {
			// command 118
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_EXTENTION, new Actionable() {
			// command 131
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_STAY, new Actionable() {
			// command 132
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_GOINGOUT, new Actionable() {
			// command 133
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_MERIT, new Actionable() {
			// command 134
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
		
		ActionRegister.registerAction(Commands.APPLY_AFTERSCHOOL, new Actionable() {
			// command 135
			@Override
			public EasyJsonObject action(int command, EasyJsonObject requestObject) throws SQLException {
				
			}
		});
	}
}
