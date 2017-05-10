package com.dms.parser.dataio;

import org.boxfox.dms.utilities.database.DataSaveable;

public abstract class Parser {
	public static final String URL_MEAL = "http://stu.dje.go.kr/sts_sci_md00_001.do?schulCode=G100000170&schulCrseScCode=4&schulKndScCode=04&schYm=?";
	public static final String URL_PLAN = "http://stu.dje.go.kr/sts_sci_sf01_001.do?schulCode=G100000170&schulCrseScCode=4&schulKndScCode=04&ay=?&mm=?";
	public static final String LINK = "http://dsmhs.djsch.kr/boardCnts/view.do";
	public static final String URL_BROAD = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54793&m=0201&s=dsmhs&page=?";
	public static final String URL_FAMILER = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54794&m=0202&s=dsmhs&page=?";
	public static final String URL_CHALLENGE = "http://dsmhs.djsch.kr/boardCnts/list.do?boardID=54795&m=0204&s=dsmhs&page=?";
	
	public static final String URL_BOARD_PARAMATER = "?boardID=?&boardSeq=?&lev=?&searchType=?&statusYN=?&page=?&opType=?&s=dsmhs&m=0201";
	protected String url;
	
	public abstract DataSaveable parse();
	public abstract DataSaveable[] parseAll();
	
}
