package com.boxfox.dms.meal.dao;

import java.util.Calendar;

import org.apache.ibatis.session.SqlSession;
import org.boxfox.dms.utils.meals.MealParser;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.boxfox.dms.mapper.MealMapper;
import com.boxfox.dms.meal.dto.DayMealDTO;
import com.boxfox.dms.meal.dto.DayMealTest;
import com.boxfox.dms.meal.dto.Meal;

@Repository
public class MealDAOImpl implements MealDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public DayMealDTO getMeal(int year, int month, int day) {
		MealMapper mealMapper = sqlSession.getMapper(MealMapper.class);
		DayMealTest meal2 = new DayMealTest();
		mealMapper.insertMealAtDate(meal2);
		
		
		DayMealDTO meal = mealMapper.getMealAtDate(year+"-"+month+"-"+day);
		if(meal == null) {
			meal = new MealParser(year, month, day).parse();
			//mealMapper.insertMealAtDate(meal);
		}
		return meal;
	}
	
	@Override
	public DayMealDTO getMealToday() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return getMeal(year, month, day);
	}

}
