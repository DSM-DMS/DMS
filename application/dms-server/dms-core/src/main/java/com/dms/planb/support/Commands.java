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
	public static final int INSERT											= 1; // prefix
	
	public static final int REGISTER_STUDENT_ACC			= 100;
	public static final int REGISTER_TEACHER_ACC			= 101;
	
	// notice, newsletter, competition : parsing
	public static final int UPLOAD_RULE								= 111;
	public static final int UPLOAD_QUESTION					= 112;
	public static final int UPLOAD_ANSWER						= 113;
	public static final int UPLOAD_QNA_COMMENT			= 114;
	public static final int UPLOAD_FAQ								= 115;
	public static final int UPLOAD_AFTERSCHOOL_ITEM	= 116;
	public static final int UPLOAD_REPORT_FACILITY		= 117;
	public static final int UPLOAD_REPORT_RESULT 			= 118;
	
	public static final int APPLY_EXTENTION						= 131;
	public static final int APPLY_STAY									= 132;
	public static final int APPLY_GOINGOUT						= 133;
	public static final int APPLY_MERIT								= 134;
	public static final int APPLY_AFTERSCHOOL					= 135;
	
/* --------------------------------------------------------------------- */
	
	public static final int UPDATE											= 2; // prefix
	
	public static final int MODIFY_PASSWORD					= 200;
	public static final int MODIFY_STUDENT_DATA			= 201;
	
	public static final int MODIFY_RULE								= 211;
	public static final int MODIFY_QUESTION						= 212;
	public static final int MODIFY_ANSWER						= 213;
	public static final int MODIFY_QNA_COMMENT			= 214;
	public static final int MODIFY_FAQ									= 215;
	public static final int MODIFY_REPORT_FACILITY		= 216;
	
	public static final int MODIFY_EXTENTION					= 231;
	public static final int MODIFY_STAY								= 232;
	public static final int MODIFY_STAY_DEFAULT				= 233;
	public static final int MODIFY_GOINGOUT					= 234;
	public static final int MODIFY_MERIT								= 235;
	public static final int MODIFY_AFTERSCHOOL				= 236;
	
/* --------------------------------------------------------------------- */
	
	public static final int DELETE											= 3; // prefix
	
	public static final int DELETE_ACCOUNT						= 300;
	
	public static final int DELETE_RULE								= 311;
	public static final int DELETE_QUESTION						= 312;
	public static final int DELETE_ANSWER							= 313;
	public static final int DELETE_QNA_COMMENT			= 314;
	public static final int DELETE_FAQ									= 315;
	public static final int DELETE_REPORT_FACILITY			= 316;
	
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
//	public static final int LOAD_TEACHER_MYPAGE				= 402;
	public static final int LOAD_ACCOUNT							= 403;
	public static final int LOAD_TEACHER_ACCOUNT		= 404;
	
	public static final int LOAD_NOTICE_LIST						= 411;
	public static final int LOAD_NEWSLETTER_LIST			= 412;
	public static final int LOAD_COMPETITION_LIST			= 413;
	public static final int LOAD_QNA_LIST							= 414;
	public static final int LOAD_REPORT_FACILITY_LIST	= 415;
	public static final int LOAD_AFTERSCHOOL_LIST			= 416;
	
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
	public static final int LOAD_STAY_DEFAULT					= 433;
	public static final int LOAD_GOINGOUT_STATUS		= 434;
	public static final int LOAD_MERIT_APPLY_STATUS	= 435;
	public static final int LOAD_AFTERSCHOOL_STATUS	= 436;
	public static final int LOAD_MEAL									= 437;
	public static final int LOAD_PLAN									= 438;
	public static final int LOAD_SCORE								= 439;
}