package org.boxfox.dms.utilities.dataio;

public abstract class Parser {
	public static final String URL_MEAL = "http://dsm.hs.kr/segio/meal/meal.php?year=?&month=?&day=?";
	public static final String URL_PLAN = "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String URL_BROAD = "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String URL_FAMILER= "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String URL_MISSION= "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	public static final String URL_CHALLENGE= "http://dsm.hs.kr/segio/plan_v2/month_cont.php?lid=main&sdate_ms=?";
	protected String url;
	
}
