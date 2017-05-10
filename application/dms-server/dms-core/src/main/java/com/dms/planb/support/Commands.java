package com.dms.planb.support;

/**
 * Class that the Action class refers to
 * 
 * @see
 * Java final variable naming convention	: http://softwareengineering.stackexchange.com/questions/252243/naming-convention-final-fields-not-static
 * 			Field modifiers convention			: http://stackoverflow.com/questions/11219556/difference-between-final-static-and-static-final
 */

/**
 * @author JoMingyu
 */
public class Commands {
	/*
	 * Notice, Newsletter, Competition, Plan, Meal will parsed in
	 * dms-data-utilities
	 */
	public static final int REGISTER_STUDENT_ACC = 100;
	public static final int REGISTER_TEACHER_ACC = 101;

	// notice, newsletter, competition : parsing
	public static final int UPLOAD_RULE = 111;
	public static final int UPLOAD_QNA_QUESTION = 112;
	public static final int UPLOAD_QNA_ANSWER = 113;
	public static final int UPLOAD_QNA_COMMENT = 114;
	public static final int UPLOAD_FAQ = 115;
	public static final int UPLOAD_AFTERSCHOOL_ITEM = 116;
	public static final int UPLOAD_REPORT_FACILITY = 117;
	public static final int UPLOAD_REPORT_RESULT = 118;

	public static final int APPLY_AFTERSCHOOL = 131;

	/* --------------------------------------------------------------------- */

	public static final int MODIFY_PASSWORD = 200;
	public static final int MODIFY_STUDENT_DATA = 201;

	public static final int MODIFY_RULE = 211;
	public static final int MODIFY_QNA_QUESTION = 212;
	public static final int MODIFY_QNA_ANSWER = 213;
	public static final int MODIFY_QNA_COMMENT = 214;
	public static final int MODIFY_FAQ = 215;
	public static final int MODIFY_REPORT_FACILITY = 216;

	public static final int MODIFY_AFTERSCHOOL_APPLY = 231;
	public static final int MODIFY_STAY_DEFAULT = 232;

	/* --------------------------------------------------------------------- */

	public static final int DELETE_ACCOUNT = 300;
	public static final int MODIFY_PROFILE_IMAGE = 301;

	public static final int DELETE_RULE = 311;
	public static final int DELETE_QNA_QUESTION = 312;
	public static final int DELETE_QNA_ANSWER = 313;
	public static final int DELETE_QNA_COMMENT = 314;
	public static final int DELETE_FAQ = 315;
	public static final int DELETE_REPORT_FACILITY = 316;

	public static final int WITHDRAW_EXTENSION_APPLY = 331;
	public static final int WITHDRAW_GOINGOUT_APPLY = 332;
	public static final int WITHDRAW_MERIT_APPLY = 333;
	public static final int WITHDRAW_AFTERSCHOOL_APPLY = 334;

	/* --------------------------------------------------------------------- */

	public static final int LOAD_MYPAGE = 401;
	// public static final int LOAD_TEACHER_MYPAGE = 402;
	public static final int LOGIN_STUDENT_REQUEST = 403;
	public static final int LOGIN_TEACHER_REQUEST = 404;

	public static final int LOAD_NOTICE_LIST = 411;
	public static final int LOAD_NEWSLETTER_LIST = 412;
	public static final int LOAD_COMPETITION_LIST = 413;
	// LOAD_RULE_LIST
	public static final int LOAD_QNA_LIST = 414;
	// LOAD_FAQ_LIST
	public static final int LOAD_REPORT_FACILITY_LIST = 415;
	public static final int LOAD_AFTERSCHOOL_ITEM_LIST = 416;

	public static final int LOAD_NOTICE = 421;
	public static final int LOAD_NEWSLETTER = 422;
	public static final int LOAD_COMPETITION = 423;
	public static final int LOAD_RULE = 424;
	public static final int LOAD_QNA = 425;
	public static final int LOAD_QNA_COMMENT = 426;
	public static final int LOAD_FAQ = 427;
	public static final int LOAD_REPORT_FACILITY = 428;

	public static final int LOAD_EXTENSION_APPLY_STATUS = 431;
	public static final int LOAD_EXTENSION_CLASS_STATUS = 432;
	public static final int LOAD_STAY_APPLY_STATUS = 433;
	public static final int LOAD_STAY_DEFAULT = 434;
	public static final int LOAD_GOINGOUT_APPLY_STATUS = 435;
	public static final int LOAD_MERIT_APPLY_STATUS = 436;
	public static final int LOAD_AFTERSCHOOL_APPLY_STATUS = 437;
	public static final int LOAD_MEAL = 438;
	public static final int LOAD_PLAN = 439;
	public static final int LOAD_SCORE = 440;

	/* --------------------------------------------------------------------- */

	public static final int APPLY_EXTENSION = 501;
	public static final int APPLY_STAY = 502;
	public static final int APPLY_GOINGOUT = 503;
	public static final int APPLY_MERIT = 504;
}