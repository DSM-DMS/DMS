package org.boxfox.dms.utils.meals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.boxfox.dms.meal.dto.DayMealDTO;
import com.boxfox.dms.meal.dto.Meal;

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

	public DayMealDTO parse() {
		return parse(month, day);
	}

	public DayMealDTO parse(int month, int day) {
		DayMealDTO dayMeal = new DayMealDTO((year+"-"+month+"-"+day),
				parseDoc(dateFormating(month, day)));
		return dayMeal;
	}

	private int parseFirstMonth() {
		return Integer.valueOf(parseFirstDayField().split("�썡")[0]);
	}

	private int parseFirstDay() {
		return Integer.valueOf(parseFirstDayField().split(" ")[1].replaceAll("�씪", ""));
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
		return String.format("%d�썡 %d�씪", month, day);
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
