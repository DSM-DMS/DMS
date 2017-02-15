package com.dms.parser.dataio;

import org.boxfox.dms.utilities.database.DataSaveAble;

public abstract class Parser {
	public static final String URL_MEAL = "http://dsm.hs.kr/segio/meal/meal.php?year=?&month=?&day=?";
	public static final String URL_PLAN = "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String LINK = "http://dsm.hs.kr";
	public static final String URL_BROAD = "http://dsm.hs.kr/notice.brd/0?..a2170aa?shell=/index.shell:210";
	public static final String URL_FAMILER = "http://dsm.hs.kr/school_3.brd/0?..594b50b?shell=/index.shell:209";
	public static final String URL_CHALLENGE = "http://dsm.hs.kr/school_6.brd/0?..604351c?shell=/index.shell:212";
	protected String url;
	
	public abstract DataSaveAble parse();
	public abstract DataSaveAble[] parseAll();
	
}
