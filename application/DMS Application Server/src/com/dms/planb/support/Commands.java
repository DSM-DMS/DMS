package com.dms.planb.support;

/*
 * Class that the CommandAnalyzer class refers to
 * 
 * Java final variable naming convention	: http://softwareengineering.stackexchange.com/questions/252243/naming-convention-final-fields-not-static
 * 			Field modifiers convention			: http://stackoverflow.com/questions/11219556/difference-between-final-static-and-static-final
 */

public class Commands {
	static final int INSERT										= 1; // prefix
	
	static final int REGISTER_ACCOUNT				= 100;
	
	static final int UPLOAD_NOTICE						= 111;
	static final int UPLOAD_RULE							= 112;
	static final int UPLOAD_QNA							= 113;
	static final int UPLOAD_CONTACT					= 114;
	static final int UPLOAD_COMPETITION			= 115;
	static final int UPLOAD_REPORT_FACILITY	= 116;
	
	static final int UPLOAD_MEAL 						= 121;
	static final int UPLOAD_PLAN 						= 122;
//	static final int SAVE_ACADEMIC_CALENDAR	= 122;
	
	static final int APPLY_EXTENTION					= 131;
	static final int APPLY_STAY								= 132;
	static final int APPLY_GOINGOUT					= 133;
	static final int APPLY_MERIT							= 134;
	// Merit : 상점
	static final int APPLY_AFTERSCHOOL			= 135;
	
	/*
	 * [100] 회원가입
	 * 
	 * [111] 공지사항 업로드
	 * [112] 기숙사 규정 업로드
	 * [113] Q&A 업로드
	 * [114] 문의하기 업로드
	 * [115] 대회 정보 업로드
	 * [116] 시설 고장 신고 업로드
	 * 
	 * [121] 파싱된 급식 정보 저장
	 * [122] 파싱된 학사 일정 저장
	 * 
	 * [131] 연장학습 신청
	 * [132] 잔류 신청
	 * [133] 주말 외출 신청
	 * [134] 상점 신청
	 * [135] 방과후 신청
	 */
	
/* --------------------------------------------------------------- */
	
	static final int UPDATE											= 2; // prefix
	
	static final int MOFIFY_ACCOUNT						= 200;
	
	static final int MODIFY_NOTICE							= 211;
	static final int MODIFY_RULE								= 212;
	static final int MODIFY_QNA								= 213;
	static final int MODIFY_CONTACT						= 214;
	static final int MODIFY_COMPETITION				= 215;
	static final int MODIFY_REPORT_FACILITY		= 216;
	
	static final int MODIFY_EXTENTION					= 221;
	static final int MODIFY_STAY								= 222;
	static final int MODIFY_GOINGOUT					= 223;
	static final int MODIFY_MERIT							= 224;
	static final int MODIFY_AFTERSCHOOL				= 225;
	
	/*
	 * [200] 사용자 정보 수정
	 * 
	 * [211] 공지사항 수정
	 * [212] 기숙사 규정 수정
	 * [213] Q&A 수정
	 * [214] 문의하기 수정
	 * [215] 대회 정보 수정
	 * [216] 시설 고장 신고 수정
	 * 
	 * [221] 연장학습 신청 수정
	 * [222] 잔류 신청 수정
	 * [223] 주말 외출 신청 수정
	 * [224] 상점 신청 수정
	 * [225] 방과후 신청 수정
	 */
	
/* --------------------------------------------------------------- */
	
	static final int DELETE											= 3; // prefix
	
	static final int DELETE_ACCOUNT						= 300;
	
	static final int DELETE_NOTICE							= 311;
	static final int DELETE_RULE								= 312;
	static final int DELETE_QNA								= 313;
	static final int DELETE_CONTACT						= 314;
	static final int DELETE_COMPETITION				= 315;
	static final int DELETE_REPORT_FACILITY			= 316;
	
	static final int DEAPPLY_EXTENTION					= 321;
	static final int DEAPPLY_GOINGOUT					= 322;
	static final int DEAPPLY_MERIT							= 323;
	/*
	 * Deapply : 기피하다
	 * 철회하다 : Withdraw, Waive
	 */
	
	/*
	 * [300] 사용자 삭제
	 * 
	 * [311] 공지사항 삭제
	 * [312] 기숙사 규정 삭제
	 * [313] Q&A 삭제
	 * [314] 문의하기 삭제
	 * [315] 대회 정보 삭제
	 * [316] 시설 고장 신고 삭제
	 * 
	 * [321] 연장학습 신청 철회
	 * [322] 주말 외출 철회
	 * [323] 상점 신청 철회
	 */
	
/* --------------------------------------------------------------- */
	
	static final int SELECT											= 4; // prefix
	
	static final int LOAD_MYPAGE							= 400;
	static final int LOAD_ACCOUNT							= 401;
	
	static final int LOAD_POST_LIST							= 411;
	static final int LOAD_NOTICE_LIST						= 412;
	static final int LOAD_QNA_LIST							= 413;
	static final int LOAD_CONTACT_LIST					= 414;
	static final int LOAD_COMPETITION_LIST			= 415;
	static final int LOAD_REPORT_FACILITY_LIST	= 416;
	
	static final int LOAD_NOTICE								= 421;
	static final int LOAD_RULE									= 422;
	static final int LOAD_QNA									= 423;
	static final int LOAD_CONTACT							= 424;
	static final int LOAD_COMPETITION					= 425;
	static final int LOAD_REPORT_FACILITY			= 426;
	
	static final int LOAD_EXTENTION_STATUS		= 431;
	static final int LOAD_STAY_STATUS					= 432;
	static final int LOAD_GOINGOUT_STATUS		= 433;
	static final int LOAD_MERIT_APPLY_STATUS	= 434;
	static final int LOAD_AFTERSCHOOL_STATUS	= 435;
	static final int LOAD_MEAL									= 436;
	static final int LOAD_PLAN									= 437;
	static final int LOAD_SCORE								= 438;
	
	/*
	 * [400] 마이페이지
	 * [401] 로그인 시 정보 비교
	 * 
	 * [411] 공지사항 리스트 불러오기
	 * [412] 기숙사 규정 리스트 불러오기
	 * [413] Q&A 리스트 불러오기
	 * [414] 문의하기 리스트 불러오기
	 * [415] 대회 정보 리스트 불러오기
	 * [416] 시설 고장 신고 리스트 불러오기
	 * 
	 * 
	 * [421] 공지사항 게시글 열람
	 * [422] 기숙사 규정 게시글 열람
	 * [423] Q&A 게시글 열람
	 * [424] 문의하기 게시글 열람
	 * [425] 대회 정보 게시글 열람
	 * [426] 시설 고장 신고 게시글 열람
	 * 
	 * [431] 연장학습 신청 상태 조회
	 * [432] 잔류 신청 상태 조회
	 * [433] 주말 외출 신청 상태 조회
	 * [434] 상점 신청 상태 조회
	 * [435] 방과후 신청 상태 조회
	 * [436] 급식 열람
	 * [437] 학사일정 열람
	 * [438] 상∙벌점 조회
	 */
}
