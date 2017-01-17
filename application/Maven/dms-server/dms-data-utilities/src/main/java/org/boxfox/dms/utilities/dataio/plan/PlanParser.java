package org.boxfox.dms.utilities.dataio.plan;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.dataio.Parser;
import org.boxfox.dms.utilities.dataio.ParserUtills;
import org.boxfox.dms.utilities.datamodel.plan.MonthPlan;
import org.boxfox.dms.utilities.datamodel.plan.Plan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PlanParser extends Parser {
	public String lists = "";
	private int year, month;

	public PlanParser(int year, int month) {
		this.url = (ParserUtills.getUrl(URL_PLAN, getTimeStemp(year, month)));
		this.year = year;
		this.month = month;
	}

	private static long getTimeStemp(int year, int month) {
		Date currentDate = new Date(year, month, 15);
		Date standardDate = new Date(1970, 1, 1);
		long time = currentDate.getTime();
		long times = standardDate.getTime();
		Timestamp ts = new Timestamp(time);
		Timestamp ts2 = new Timestamp(times);
		long ResultData = (ts.getTime() - ts2.getTime()) / 1000;
		return ResultData;
	}

	public MonthPlan parse() {
		return doParse();
	}

	public MonthPlan[] parseAll() {
		MonthPlan[] plans = new MonthPlan[12];
		for (int i = 1; i < 13; i++) {
			plans[i - 1] = parse(year, i);
		}
		return plans;
	}

	public MonthPlan parse(int year, int month) {
		this.url = (ParserUtills.getUrl(URL_PLAN, getTimeStemp(year, month)));
		this.year = year;
		this.month = month;
		return doParse();
	}

	private MonthPlan doParse() {
		Element tbody = ParserUtills.getDoc(url).getElementsByClass("plist").get(0).getElementsByTag("tbody").get(0);
		Elements tds = tbody.getElementsByTag("td");
		boolean check = false;
		MonthPlan monthPlan = new MonthPlan(year, month);
		for (Element td : tds) {
			Plan plan = getDayPlan(td);
			if (plan == null)
				continue;
			if (plan.getDay() == 1) {
				if (check)
					break;
				else
					check = true;
			} else if (!check)
				continue;
			monthPlan.addPlan(plan);
		}
		try {
			DataBase.getInstance().execute(monthPlan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monthPlan;
	}

	private Plan getDayPlan(Element td) {
		Plan planObj = null;
		int day = Integer.valueOf(td.getElementsByClass("left").get(0).text());
		Elements elements = td.getElementsByClass("mday_pl");
		if (elements.size() - 1 > 0) {
			JSONArray plan = new JSONArray();
			for (int i = 0; i < elements.size() - 1; i++) {
				plan.add(elements.get(i).text());
			}
			planObj = new Plan(day, plan);
		}
		return planObj;
	}
}