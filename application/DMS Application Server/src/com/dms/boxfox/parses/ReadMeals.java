package com.dms.boxfox.parses;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dms.boxfox.database.DataBase;
import com.dms.boxfox.item.Meal;
import com.dms.boxfox.logging.Log;

public class ReadMeals {

	public void doParse(int year, int month, int day) {
		try {
			ArrayList<String[]> texts = new ArrayList<String[]>();
			String arrs[] = new String[7];
			int count = 27;
			HttpURLConnection httpConn = null;
			URL url = new URL(DataBase.getInstance().queryBuilder("http://www.dsm.hs.kr/segio/meal/meal.php?year=",
					year, "&month=", month, "&day=", day, "&shell=/index.shell:55"));
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setDoInput(true);
			InputStream is = httpConn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String line;
			StringBuffer sbs = new StringBuffer();
			String HTML_CONTENT;
			String VAR_POST;

			while ((line = rd.readLine()) != null) {
				sbs.append(line);
			}
			String sb = sbs.toString().replaceAll("today", "con");
			String date = sb.substring(sb.indexOf("<tr> <td class=\"con\">"), sb.indexOf("</tr>  <tr>"));
			date = date.replaceAll("<td class=\"today\">", "<td class=\"con\">");
			for (int i = 0; i < 7; i++) {
				arrs[i] = date.substring(date.indexOf("<td class=\"con\">") + "<td class=\"con\">".length(),
						date.indexOf("</td>"));
				date = date.substring(date.indexOf("</td>") + "</td>".length(), date.length());
			}
			int F = sb.indexOf("<caption>주간 조식 식단표</caption>") + ("<caption>주간 조식 식단표</caption>").length();
			int L = sb.indexOf("식단을엑셀파일로출력합니다");
			HTML_CONTENT = sb.toString().substring(F, L);
			HTML_CONTENT = HTML_CONTENT.replaceAll("<td class=\"today\">", "<td class=\"con\">");
			int Count = 0;
			int Date = 1;
			while (HTML_CONTENT.contains("<td class=\"con\">") || HTML_CONTENT.contains("&nbsp;")) {
				String check;
				F = HTML_CONTENT.indexOf("<td class=\"con\">");
				HTML_CONTENT = HTML_CONTENT.substring(F, HTML_CONTENT.length());
				L = HTML_CONTENT.indexOf("</td>");
				F = 0;
				check = HTML_CONTENT.substring(F, L);
				F += ("<td class=\"con\">").length();
				String text = HTML_CONTENT.substring(F, L);
				text = HTML_CONTENT.substring(F, L);
				HTML_CONTENT = HTML_CONTENT.substring(check.length(), HTML_CONTENT.length());
				if (text.contains("&nbsp;")) {
					texts.add(new String[] { "null" });
					continue;
				} else if (text.contains(month + "월") || text.contains(month - 1 + "월")
						|| (month != 12 && text.contains(month + 1 + "월")) || (month == 12 && text.contains(1 + "월"))) {
					continue;
				} else if (text.split("<br />").length == 1) {
					text = text.replace("<br />", "");
					String[] te = { text };
					texts.add(te);

				} else if (text.equals("<br />")) {
					texts.add(new String[] { "급식이 없습니다." });

				} else {
					String menus[] = text.split("<br />");
					texts.add(menus);
				}
			}
			Meal meals[] = new Meal[7];
			for (int i = 0, j = 0; i < 3; i++) {
				for (int k = 0; k < 7; k++, j++) {
					switch (i) {
					case 0:
						meals[k] = new Meal();
						meals[k].setBreakfast(texts.get(j));
						break;
					case 1:
						meals[k].setLunch(texts.get(j));
						break;
					case 2:
						meals[k].setDinner(texts.get(j));
						break;
					}
				}
			}
			ArrayList<JSONObject> arrss = new ArrayList<JSONObject>();
			for (int k = 0; k < 7; k++) {
				Meal m = meals[k];
				JSONObject obj = new JSONObject();
				JSONArray arr = new JSONArray();
				obj.put("Command", 719);
				for (int i = 0; i < m.getFirst().size(); i++)
					arr.put(m.getFirst().get(i));
				obj.put("First", arr);

				arr = new JSONArray();
				for (int i = 0; i < m.getLunch().size(); i++)
					arr.put(m.getLunch().get(i));
				obj.put("Lunch", arr);

				arr = new JSONArray();
				for (int i = 0; i < m.getDinner().size(); i++)
					arr.put(m.getDinner().get(i));
				obj.put("Dinner", arr);

				arr = new JSONArray();
				for (int i = 0; i < m.getAllergyFirst().size(); i++)
					arr.put(m.getAllergyFirst().get(i));
				obj.put("FirstAllergy", arr);

				arr = new JSONArray();
				for (int i = 0; i < m.getAllergyLunch().size(); i++)
					arr.put(m.getAllergyLunch().get(i));
				obj.put("LunchAllergy", arr);

				arr = new JSONArray();
				for (int i = 0; i < m.getAllergyDinner().size(); i++)
					arr.put(m.getAllergyDinner().get(i));
				obj.put("DinnerAllergy", arr);
				arrss.add(obj);
			}
			// insert.insertMeal(year, arrs, arrss);
		} catch (IOException e) {
			Log.e(e.toString());
		} catch (JSONException e) {
			Log.e(e.toString());
		}
	}
}
