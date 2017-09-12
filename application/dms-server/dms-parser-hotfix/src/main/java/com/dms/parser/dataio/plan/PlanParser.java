package com.dms.parser.dataio.plan;

import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dms.parser.dataio.Parser;
import com.dms.parser.dataio.ParserUtils;
import com.dms.parser.datamodel.plan.MonthPlan;
import com.dms.parser.datamodel.plan.Plan;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.DataSaveable;

public class PlanParser extends Parser {
	public String lists = "";
	private int year, month;

	public PlanParser(int year, int month) {
		this.url = (ParserUtils.getUrl(URL_PLAN, year, String.format("%02d", month)));
		this.year = year;
		this.month = month;
	}

	@Override
	public DataSaveable parse() {
		return doParse();
	}

	@Override
	public DataSaveable[] parseAll() {
		MonthPlan[] plans = new MonthPlan[12];
		for (int i = 1; i < 13; i++) {
			plans[i - 1] = parse(year, i);
		}
		return plans;
	}

	public MonthPlan parse(int year, int month) {
		this.url = (ParserUtils.getUrl(URL_PLAN, year, String.format("%02d", month)));
		this.year = year;
		this.month = month;
		return doParse();
	}

	private MonthPlan doParse() {
		MonthPlan monthPlan = new MonthPlan(year, month);
		Element tbody = ParserUtils.getDoc(url).getElementsByTag("tbody").get(0);
		Elements items = tbody.getElementsByClass("textL");
		Element[] itemsArray = items.toArray(new Element[items.size()]);
		for (int i = 1; i < itemsArray.length; i++) {
			Element planElement = itemsArray[i];
			String dayStr = planElement.getElementsByTag("em").get(0).text();
			if (dayStr.equals("") || dayStr.length() == 0)
				continue;
			int day = Integer.valueOf(dayStr);
			Elements plans = itemsArray[i].getElementsByTag("strong");
			JSONArray plansJsonArray = new JSONArray();
			for (Element plan : plans)
				plansJsonArray.add(plan.text());
			Plan plan = new Plan(day, plansJsonArray);
			monthPlan.addPlan(plan);
		}
		try {
			DataBase.getInstance().execute(monthPlan);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monthPlan;
	}
}