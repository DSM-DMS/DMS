package com.dms.planb.support;

/*
 * Class that the CommandAnalyzer class refers to
 * 
 * Java final variable naming convention	: http://softwareengineering.stackexchange.com/questions/252243/naming-convention-final-fields-not-static
 * 			Field modifiers convention			: http://stackoverflow.com/questions/11219556/difference-between-final-static-and-static-final
 */

public class Commands {
	/*
	 * Notice, Newsletter, Competition, Plan, Meal will parsed in dms-data-utilities
	 */
	public static final int INSERT										= 1; // prefix
	
	public static final int REGISTER_STUDENT_ACC		= 100;
	public static final int REGISTER_TEACHER_ACC		= 101;
	
//	public static final int UPLOAD_NOTICE						= 111;
//	public static final int UPLOAD_NEWSLETTER			= 112;
//	public static final int UPLOAD_COMPETITION			= 113;
	public static final int UPLOAD_RULE							= 114;
	public static final int UPLOAD_QUESTION				= 115;
	public static final int UPLOAD_ANSWER					= 116;
	public static final int UPLOAD_QNA_COMMENT		= 117;
	public static final int UPLOAD_FAQ							= 118;
	public static final int UPLOAD_AFTERSCHOOL		= 119;
	public static final int UPLOAD_REPORT_FACILITY	= 120;
	public static final int UPLOAD_REPORT_RESULT 	= 121;
	
	public static final int APPLY_EXTENTION					= 141;
	public static final int APPLY_STAY								= 142;
	public static final int APPLY_GOINGOUT					= 143;
	public static final int APPLY_MERIT							= 144;
	public static final int APPLY_AFTERSCHOOL			= 145;
	
/* --------------------------------------------------------------------- */
	
	public static final int UPDATE											= 2; // prefix
	
	public static final int MODIFY_PASSWORD					= 200;
	public static final int MODIFY_STUDENT_DATA			= 201;
	
//	public static final int MODIFY_NOTICE							= 211;
//	public static final int MODIFY_NEWSLETTER				= 212;
//	public static final int MODIFY_COMPETITION				= 213;
	public static final int MODIFY_RULE								= 214;
	public static final int MODIFY_QUESTION					= 215;
	public static final int MODIFY_ANSWER						= 216;
	public static final int MODIFY_QNA_COMMENT			= 217;
	public static final int MODIFY_FAQ								= 218;
	public static final int MODIFY_REPORT_FACILITY		= 219;
	
	public static final int MODIFY_EXTENTION					= 231;
	public static final int MODIFY_STAY								= 232;
	public static final int MODIFY_GOINGOUT					= 233;
	public static final int MODIFY_MERIT							= 234;
	public static final int MODIFY_AFTERSCHOOL				= 235;
	
/* --------------------------------------------------------------------- */
	
	public static final int DELETE											= 3; // prefix
	
	public static final int DELETE_ACCOUNT						= 300;
	
//	public static final int DELETE_NOTICE							= 311;
//	public static final int DELETE_NEWSLETTER				= 312;
//	public static final int DELETE_COMPETITION				= 313;
	public static final int DELETE_RULE								= 314;
	public static final int DELETE_QUESTION						= 315;
	public static final int DELETE_ANSWER							= 316;
	public static final int DELETE_QNA_COMMENT			= 317;
	public static final int DELETE_FAQ									= 318;
	public static final int DELETE_REPORT_FACILITY		= 319;
	
	public static final int DEAPPLY_EXTENTION					= 331;
	public static final int DEAPPLY_GOINGOUT					= 332;
	public static final int DEAPPLY_MERIT							= 333;
	/*
	 * Deapply : 기피하다
	 * 철회하다 : Withdraw, Waive
	 */
	
/* --------------------------------------------------------------------- */
	
	public static final int SELECT											= 4; // prefix
	
	public static final int LOAD_MYPAGE								= 401;
//	public static final int LOAD_TEACHER_MYPAGE			= 402;
	public static final int LOAD_ACCOUNT							= 403;
	public static final int LOAD_TEACHER_ACCOUNT		= 404;
	
	public static final int LOAD_NOTICE_LIST						= 411;
	public static final int LOAD_NEWSLETTER_LIST			= 412;
	public static final int LOAD_COMPETITION_LIST		= 413;
	public static final int LOAD_RULE_LIST							= 414;
	public static final int LOAD_QNA_LIST							= 415;
	public static final int LOAD_FAQ_LIST							= 416;
	public static final int LOAD_REPORT_FACILITY_LIST	= 417;
	public static final int LOAD_AFTERSCHOOL_LIST		= 418;
	
	public static final int LOAD_NOTICE								= 421;
	public static final int LOAD_NEWSLETTER					= 422;
	public static final int LOAD_COMPETITION					= 423;
	public static final int LOAD_RULE									= 424;
	public static final int LOAD_QNA									= 425;
	public static final int LOAD_QNA_COMMENT				= 426;
	public static final int LOAD_FAQ										= 427;
	public static final int LOAD_REPORT_FACILITY			= 428;
	
	public static final int LOAD_EXTENTION_STATUS		= 431;
	public static final int LOAD_STAY_STATUS					= 432;
	public static final int LOAD_GOINGOUT_STATUS		= 433;
	public static final int LOAD_MERIT_APPLY_STATUS	= 434;
	public static final int LOAD_AFTERSCHOOL_STATUS	= 435;
	public static final int LOAD_MEAL									= 436;
	public static final int LOAD_PLAN									= 437;
	public static final int LOAD_SCORE								= 438;
}