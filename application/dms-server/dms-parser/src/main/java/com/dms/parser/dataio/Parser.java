package com.dms.parser.dataio;

import org.boxfox.dms.utilities.database.DataSaveAble;

public abstract class Parser {
	public static final String URL_MEAL = "http://dsm.hs.kr/segio/meal/meal.php?year=?&month=?&day=?";
	public static final String URL_PLAN = "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String LINK = "http://dsmhs.djsch.kr/boardCnts/view.do";
	public static final String URL_BROAD = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54793&m=0201&s=dsmhs&page=?";
	public static final String URL_FAMILER = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54794&m=0202&s=dsmhs&page=?";
	public static final String URL_CHALLENGE = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54795&m=0204&s=dsmhs&page=?";
	
	public static final String URL_BOARD_PARAMATER = "?boardID=?&boardSeq=?&lev=?&searchType=?&statusYN=?&page=?&opType=?&s=dsmhs&m=0201";
	protected String url;
	
	public abstract DataSaveAble parse();
	public abstract DataSaveAble[] parseAll();
	
}
