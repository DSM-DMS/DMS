package com.dms.parser.dataio.meal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dms.parser.dataio.Parser;
import com.dms.parser.dataio.ParserUtils;
import com.dms.parser.datamodel.meals.DayMeal;
import com.dms.parser.datamodel.meals.Meal;
import com.dms.utilities.database.DataBase;
import com.dms.utilities.database.QueryUtils;

public class MealParser extends Parser {
	private int year, month, day;
	private String url;

	public MealParser(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		url = ParserUtils.getUrl(URL_MEAL, year + String.format("%02d", month));
		Document doc = ParserUtils.getDoc(url);
	}

	public DayMeal parse() {
		return parseAll()[day - 1];
	}

	@Override
	public DayMeal[] parseAll() {
		List<DayMeal> list = parse(ParserUtils.getDoc(url).getElementsByTag("table").get(0).html());
		for (DayMeal meal : list) {
			try {
				DataBase.getInstance().execute(meal);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list.toArray(new DayMeal[list.size()]);
	}

	private List<DayMeal> parse(String rawData) {

		List<DayMeal> monthlyMenu = new ArrayList<DayMeal>();
		rawData = rawData.replaceAll("\\s+", "");
		StringBuffer buffer = new StringBuffer();

		boolean inDiv = false;

		int count = 0;
		for (int i = 0; i < rawData.length(); i++) {
			if (rawData.charAt(i) == 'v') {
				if (inDiv) {
					buffer.delete(buffer.length() - 4, buffer.length());
					if (buffer.length() > 0) {
						monthlyMenu.add(parseDay(buffer.toString(), ++count));
					}
					buffer.setLength(0);
				} else {
					i++;
				}
				inDiv = !inDiv;
			} else if (inDiv) {
				buffer.append(rawData.charAt(i));
			}
		}
		return monthlyMenu;
	}

	private DayMeal parseDay(String rawData, int day) {
		Meal[] meals = new Meal[3];

		rawData = rawData.replace("(석식)", "");
		rawData = rawData.replace("(선)", "");

		String[] chunk = rawData.split("\\[(.*?)\\]");

		for (int j = 1; j < chunk.length; j++) {
			JSONArray menu = new JSONArray(), allergy = new JSONArray();
			String[] chunks = chunk[j].split("<br>");
			for (int i = 1; i < chunks.length; i++) {
				if (chunks[i].trim().length() < 1)
					continue;

				menu.add(chunks[i].split("\\*")[0]);
				if (chunks[i].split("\\*").length > 1) {
					String[] allergis = chunks[i].split("\\*")[1].split("\\.");
					for (String a : allergis) {
						if (!allergy.contains(a)) {
							allergy.add(a);
						}
					}
				}
			}
			meals[j - 1] = new Meal(menu, allergy);
		}
		for (int i = 0; i < meals.length; i++)
			if (meals[i] == null)
				meals[i] = new Meal(null, null);
		return new DayMeal(QueryUtils.queryCreateDate(year, month, day), meals);
	}

	private Meal cleanMenu(String menu) {
		Pattern p = Pattern.compile("\\(([0-9]{1,})\\)");
		String[] lines = menu.replaceAll("\\*", "").split("<br>");
		JSONArray food = null;
		JSONArray allergy = null;
		if (lines.length > 0) {
			food = new JSONArray();
			allergy = new JSONArray();
			JSONArray kcal = new JSONArray();
			for (int i = 2; i < lines.length - 2; i++) {
				food.add(clearHtml(lines[i].replaceAll("[(][0-9]{1,}[)]", "")));
				Matcher m = p.matcher(lines[i]);
				while (m.find()) {
					if (!allergy.contains(m.group(1))) {
						allergy.add(clearHtml(m.group(1)));
					}
				}
			}
			String[] kcals = lines[lines.length - 1].split("/");
			for (int i = 0; i < kcals.length; i++) {
				kcal.add(clearHtml(kcals[i]));
			}
		}
		return new Meal(food, allergy);
	}

	private String clearHtml(String str) {
		return Jsoup.parse(str).text();
	}

}
