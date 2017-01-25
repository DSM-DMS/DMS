package org.boxfox.dms.utilities.dataio.meal;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.boxfox.dms.utilities.database.DataBase;
import org.boxfox.dms.utilities.database.QueryUtils;
import org.boxfox.dms.utilities.dataio.Parser;
import org.boxfox.dms.utilities.dataio.ParserUtils;
import org.boxfox.dms.utilities.datamodel.meals.DayMeal;
import org.boxfox.dms.utilities.datamodel.meals.Meal;
import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MealParser extends Parser {
	private Elements mealTables;
	private int year, month, day;
	private String url;

	public MealParser(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
		url = ParserUtils.getUrl(URL_MEAL, year, month, day);
		Document doc = ParserUtils.getDoc(url);
		mealTables = doc.getElementsByClass("meal_table");
	}

	public DayMeal parse() {
		return parse(month, day);
	}

	public DayMeal[] parseAll() {
		DayMeal[] meals = new DayMeal[7];
		int month = parseFirstMonth();
		int day = parseFirstDay();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		int maxDayInMonth = calendar.getMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 0; i < 7; i++, day++) {
			if (day > maxDayInMonth) {
				day = 1;
				month++;
			}
			meals[i] = parse(month, day);
		}

		return meals;
	}

	public DayMeal parse(int month, int day) {
		DayMeal dayMeal = new DayMeal(QueryUtils.queryCreateDate(year, month, day),
				parseDoc(dateFormating(month, day)));
		try {
			DataBase.getInstance().executeUpdate(dayMeal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dayMeal;
	}

	private int parseFirstMonth() {
		return Integer.valueOf(parseFirstDayField().split("월")[0]);
	}

	private int parseFirstDay() {
		return Integer.valueOf(parseFirstDayField().split(" ")[1].replaceAll("일", ""));
	}

	private String parseFirstDayField() {
		return mealTables.get(0).getElementsByTag("tr").get(1).getElementsByTag("td").get(0).text();
	}

	private Meal[] parseDoc(String date) {
		Meal[] meals = new Meal[3];
		int targetDate;
		Elements daysTableData = mealTables.get(0).getElementsByTag("tr").get(1).getElementsByTag("td");
		for (targetDate = 0; targetDate < daysTableData.size(); targetDate++) {
			if (daysTableData.get(targetDate).text().equals(date)) {
				break;
			}
		}
		for (int i = 0; i < 3; i++) {
			String menu = mealTables.get(i).getElementsByTag("tr").get(2).getElementsByTag("td").get(targetDate).html();
			meals[i] = cleanMenu(menu);
		}
		return meals;
	}

	private String dateFormating(int month, int day) {
		return String.format("%d월 %d일", month, day);
	}

	private Meal cleanMenu(String menu) {
		Pattern p = Pattern.compile("\\(([0-9]{1,})\\)");
		String[] lines = menu.replaceAll("\\*", "").split("<br>");
		JSONArray food = null;
		JSONArray allergy = null;
		if(lines.length>0){
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
